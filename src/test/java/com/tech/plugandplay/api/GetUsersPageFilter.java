package com.tech.plugandplay.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.tech.plugandplay.util.CommonUtil;

public class GetUsersPageFilter {
	
	static String hostname = "localhost";
	//static String hostname = "54.145.172.103";
	static String name = "";
	static String email = "";
	static String role = "admin";
	
	@Test
	public static void getActiveClusters(){ //verticals="+verticals+"&
		//+"&tags="+tags+"&stage="+stage+"&blurb="+blurb+"&location="+location
		Response response = RestAssured.get("http://"+hostname+":8080/plugandplay/api/v1/users/filter/1?name="+name+"&email="+email+"&role="+role);
		System.out.println("Get users page status code: "+response.getStatusCode());
		//Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("List of users.");
		System.out.println("Body: " +response.getBody().asString());
		System.out.println(new JSONObject(response.getBody().asString()).toString(4));
		System.out.println(new JSONObject(response.getBody().asString()).getInt("count"));
		JSONArray companies = new JSONObject(response.getBody().asString()).getJSONArray("data");
		
		for (int i = 0; i < companies.length(); i++) {
			JSONObject company = companies.getJSONObject(i);
			System.out.println("Name: "+company.getString("name"));
			System.out.println("Email: "+company.getString("email"));
			System.out.println("Role: "+company.getString("role"));
		}
	}
	
}
