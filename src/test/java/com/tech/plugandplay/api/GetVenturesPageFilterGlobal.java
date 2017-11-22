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

public class GetVenturesPageFilterGlobal {
	
	static String hostname = "localhost";
	//static String hostname = "54.145.172.103";
	static String verticals = "Insurtech";
	static String tags = "Social";
	static String stage = "Seed";
	static String blurb = "social";
	static String location = "Europe";
	static String pnpOffices = "Plug and Play China,FinTech - New York";
	
	@Test
	public static void getActiveClusters(){ //verticals="+verticals+"&
		//+"&tags="+tags+"&stage="+stage+"&blurb="+blurb+"&location="+location
		Response response = RestAssured.get("http://"+hostname+":8080/plugandplay/api/v1/ventures/filter/1?pnpOffices="+pnpOffices); //+"&stage="+stage+"&tags="+tags
		System.out.println("Get ventures page status code: "+response.getStatusCode());
		//Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("List of ventures.");
		System.out.println("Body: " +response.getBody().asString());
		System.out.println(new JSONObject(response.getBody().asString()).toString(4));
		System.out.println(new JSONObject(response.getBody().asString()).getInt("count"));
		JSONArray companies = new JSONObject(response.getBody().asString()).getJSONArray("data");
		
		for (int i = 0; i < companies.length(); i++) {
			JSONObject company = companies.getJSONObject(i);
			System.out.println("Id: "+company.getInt("id"));
			System.out.println("Name: "+company.getString("companyName"));
		}
	}
	
}
