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

public class GetAllCompanies {
	
	static String hostname;
	
	@BeforeClass
	@Parameters({"hostname"})
	public static void setup(String host)
	{
		hostname = CommonUtil.assignSysProp("SERVER", host);
		
	}
		
	@Test
	public static void getActiveClusters(){
		Response response = RestAssured.get("http://"+hostname+":8080/plugandplay/api/v1/companies/all");
		System.out.println("Get all companies status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("List of companies.");
		JSONArray companies = new JSONArray(response.getBody().asString());
		
		for (int i = 0; i < companies.length(); i++) {
			JSONObject company = companies.getJSONObject(i);
			System.out.println("Id: "+company.getInt("id"));
			System.out.println("Name: "+company.getString("companyname"));
		}
	}
	
}
