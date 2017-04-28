package com.tech.plugandplay.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class DeleteTop100 {
	
	
	@Test
	public static void test() throws IOException {
		String name = "Travel";
		
		for(int i = 50; i < 51; i ++){

			Response response = RestAssured.given().contentType(ContentType.JSON).delete("http://localhost:8080/plugandplay/api/v1/top100/delete/"+i+"?listName="+name);
			
			System.out.println(response.getStatusLine());
			System.out.println(response.getBody().asString());
			
		}
		
	}
	
	
	
	

}
