package com.tech.plugandplay.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.tech.plugandplay.model.Top100List;
import com.tech.plugandplay.util.CommonUtil;

public class GetTop20 {
	
	//static String hostname = "54.145.172.103";
	static String hostname = "localhost";
	static String name = "Food & Beverage";
	
	/*	@BeforeClass
	@Parameters({"hostname"})
	public static void setup(String host)
	{
		hostname = CommonUtil.assignSysProp("SERVER", host);
		
	}*/

		
	
	@Test
	public static void getTop100(){
		String content = "{\"listName\":\""+name+"\"}";
		Response response = RestAssured.given().body(content).post("http://"+hostname+":8080/plugandplay/api/v1/top20/all");
		System.out.println("Get top 20 status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("List of top 20 ventures.");
		JSONArray companies = new JSONArray(response.getBody().asString());
		
		for (int i = 0; i < companies.length(); i++) {
			JSONObject company = companies.getJSONObject(i);
			System.out.println("Venture: "+company.toString(4));
			/*System.out.println("Id: "+company.getInt("id"));
			System.out.println("Name: "+company.getString("companyName"));
			System.out.println("Top 100 order: "+company.getJSONObject("top100").getInt("order"));*/
		}
	}
	
}
