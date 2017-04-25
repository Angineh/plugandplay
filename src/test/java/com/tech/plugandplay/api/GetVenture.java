package com.tech.plugandplay.api;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.tech.plugandplay.util.CommonUtil;

public class GetVenture {
	
	static String id = "40";
	static String hostname = "localhost";
	
/*	@BeforeClass
	@Parameters({"id","hostname"})
	public static void setup(String companyId, String host)
	{
		id = CommonUtil.assignSysProp("ID", companyId);
		hostname = CommonUtil.assignSysProp("SERVER", host);
		
	}*/
		
	@Test
	public static void getCompany(){
		Response response = RestAssured.get("http://"+hostname+":8080/plugandplay/api/v1/ventures/"+id);
		System.out.println("Get company status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.getBody().asString());
	}

	
}
