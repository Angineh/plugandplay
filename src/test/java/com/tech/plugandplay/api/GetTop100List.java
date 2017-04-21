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

public class GetTop100List {
	
	static String hostname = "localhost";
	
/*	@BeforeClass
	@Parameters({"hostname"})
	public static void setup(String host)
	{
		hostname = CommonUtil.assignSysProp("SERVER", host);
		
	}*/
		

	
	@Test
	public static void getTop100List(){
		Response response = RestAssured.get("http://"+hostname+":8080/plugandplay/api/v1/top100/lists");
		System.out.println("Get top 100 list status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Top 100 lists.");
		JSONArray array = new JSONArray(response.getBody().asString());
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject top100list = array.getJSONObject(i);
			System.out.println("Id: "+top100list.getInt("id"));
			System.out.println("listName: "+top100list.getString("listName"));
			System.out.println("Archive: "+top100list.getBoolean("archive"));
		}
	}
	
}
