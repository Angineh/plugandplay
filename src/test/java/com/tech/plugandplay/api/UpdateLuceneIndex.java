package com.tech.plugandplay.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class UpdateLuceneIndex {
	
	/*static String hostname = "54.145.172.103";*/
	static String hostname = "playbook.pnptc.com";
	
	@Test
	public static void updateLuceneIndex(){
		Response response = RestAssured.post("http://"+hostname+":8080/plugandplay/api/v1/lucene");
		System.out.println("Get ventures page status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
