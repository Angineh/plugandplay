package com.ibm.bicloud.fyre;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class FyreRestHelper {
	
	private static Log log = LogFactory.getLog(FyreRestHelper.class);
	
	public static String createCluster(String content, String username, String apikey) throws IOException{
		
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).auth().preemptive().basic(username, apikey).queryParam("operation","build").body(content).post("https://9.30.166.9/rest/v1/");
		log.info("Response: "+response.getBody().asString()); //.queryParam("json", content)
		log.info("Status Code: "+response.getStatusCode());
		//TODO add assert statement for response status code
		JSONObject obj = new JSONObject(response.getBody().asString());
		
		/*String status = obj.getString("status");
		String request_id = obj.getString("request_id");*/
		String details = obj.getString("details");
		
		return details;
		
	}
	
	public static String getStatus(String url, String username, String apikey){
		
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.URLENC).auth().preemptive().basic(username, apikey).get(url);
		System.out.println("Response: "+response.getBody().asString());
		System.out.println("Status Code: "+response.getStatusCode());
		//TODO assert status
		JSONObject obj = new JSONObject(response.getBody().asString());
		
		return obj.getJSONArray("request").getJSONObject(0).getString("status");
		
	}
	
	public static JSONObject showClusters(String username, String apikey){
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.URLENC).auth().preemptive().basic(username, apikey).get("https://9.30.166.9/rest/v1/?operation=query&request=showclusters");
		System.out.println("Response: "+response.getBody().asString());
		System.out.println("Status Code: "+response.getStatusCode());
		
		return new JSONObject(response.getBody().asString());
	}
	
	@SuppressWarnings("static-access")
	public static JSONObject getClusterInfo(String cluster_prefix, String username, String apikey){
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.URLENC).auth().preemptive().basic(username, apikey).get("https://9.30.166.9/rest/v1/?operation=query&request=showclusterdetails&cluster_name="+cluster_prefix);
		System.out.println("Response: "+response.getBody().asString());
		System.out.println("Status Code: "+response.getStatusCode());
		if(response.getStatusCode() != 200){
			System.out.println("Could not find cluster!");
			return (JSONObject) new JSONObject().NULL;
		}else{
			return new JSONObject(response.getBody().asString());
		}
		
	}
	
	public static int getClusterInfoStatusCode(String cluster_prefix, String username, String apikey){		
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.URLENC).auth().preemptive().basic(username, apikey).get("https://9.30.166.9/rest/v1/?operation=query&request=showclusterdetails&cluster_name="+cluster_prefix);
		System.out.println("Response: "+response.getBody().asString());
		System.out.println("Status Code: "+response.getStatusCode());
		if(response.getStatusCode() == 200){
			 String status = new JSONObject(response.getBody().asString()).getJSONArray(cluster_prefix).getJSONObject(0).getString("status");
			 if(status.equalsIgnoreCase("deleted")){
					return 404;
			}
		}
		return response.getStatusCode();
	}
	
	public static String reloadCluster(String cluster_prefix, String username, String apikey){
		
		String content = "{\"cluster_name\" : \""+cluster_prefix+"\"}";
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).auth().preemptive().basic(username, apikey).queryParam("operation","reload").body(content).post("https://9.30.166.9/rest/v1/");
		System.out.println("Response: "+response.getBody().asString()); //.queryParam("json", content)
		System.out.println("Status Code: "+response.getStatusCode());
		//TODO add assert statement for response status code
		JSONObject obj = new JSONObject(response.getBody().asString());
		
		/*String status = obj.getString("status");
		String request_id = obj.getString("request_id");*/
		String details = obj.getString("details");
		
		return details;
		
	}
	
	public static String deleteCluster(String cluster_prefix, String username, String apikey){
		String content = "{\"cluster_name\" : \""+cluster_prefix+"\"}";
		Response response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).auth().preemptive().basic(username, apikey).queryParam("operation","delete").body(content).post("https://9.30.166.9/rest/v1/");
		System.out.println("Response: "+response.getBody().asString()); //.queryParam("json", content)
		System.out.println("Status Code: "+response.getStatusCode());
		//TODO add assert statement for response status code
		JSONObject obj = new JSONObject(response.getBody().asString());
		
		/*String status = obj.getString("status");
		String request_id = obj.getString("request_id");*/
		String details = obj.getString("details");
		
		return details;
	
	}
	
	public static String[] getAllHosts(String cluster_prefix, String username, String apikey){
	
		JSONObject clusters = FyreRestHelper.getClusterInfo(cluster_prefix, username, apikey);
				
		JSONArray array = clusters.getJSONArray(cluster_prefix);
		String[] hostnames = new String[array.length()];

		for (int i = 0; i < array.length(); i++) {
		    JSONObject obj = array.getJSONObject(i);
		    String hostname = obj.get("node").toString();
		    System.out.println(hostname);
		    hostnames[i] = hostname;
		    
		}
		return hostnames;
	}
	
	public static String getBackendIP(String cluster_prefix, String node, String username, String apikey){
		
		JSONObject clusters = FyreRestHelper.getClusterInfo(cluster_prefix, username, apikey);
		
		JSONArray array = clusters.getJSONArray(cluster_prefix);

		for (int i = 0; i < array.length(); i++) {
		    JSONObject obj = array.getJSONObject(i);
		    if(obj.get("node").toString().equalsIgnoreCase(node)){
		    	return obj.getString("privateip");
		    }
		}
		return null;
	}
	
	public static String getPrimaryIP(String cluster_prefix, String node, String username, String apikey){
		
		JSONObject clusters = FyreRestHelper.getClusterInfo(cluster_prefix, username, apikey);
		
		JSONArray array = clusters.getJSONArray(cluster_prefix);

		for (int i = 0; i < array.length(); i++) {
		    JSONObject obj = array.getJSONObject(i);
		    if(obj.get("node").toString().equalsIgnoreCase(node)){
		    	return obj.getString("publicip");
		    }
		}
		return null;
	}
	

}
