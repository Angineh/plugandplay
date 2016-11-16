package com.tech.plugandplay.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonUtil {
	
	public static String assignSysProp(String SysProperty, String SysValue) {

		if (System.getProperty(SysProperty) != null) {
			SysValue=System.getProperty(SysProperty);
		}
		return SysValue;
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}
	
	public static String generateRequest(int requestid, String cluster_prefix, String instancetype) throws JSONException, IOException{
		JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/main/resources/json/"+instancetype+"/request.json")), StandardCharsets.UTF_8));
		body.put("requestId", requestid);
		JSONArray json = body.getJSONArray("nodes");
		for (int i = 0; i < json.length(); i++) {
			JSONObject obj = json.getJSONObject(i);
			String host = json.getJSONObject(i).getString("hostname");
			host = host.replace("cluster_prefix", cluster_prefix);
			obj.put("hostname", host);
			json.put(i, obj);
		}
		body.put("nodes", json);
		
		System.out.println(body.toString());
		
		return body.toString();
	}

}
