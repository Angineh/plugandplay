package com.tech.plugandplay.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class DeleteFromTop20 {
	
	
	@Test
	public static void test() throws IOException {
		String name = "Food & Beverage";
		String content = "{\"id\":50,\"listName\":\""+name+"\"}";
		//for(int i = 50; i < 51; i ++){

			Response response = RestAssured.given().contentType(ContentType.JSON).body(content).post("http://localhost:8080/plugandplay/api/v1/top20/delete");
			
			System.out.println(response.getStatusLine());
			System.out.println(response.getBody().asString());
			
		//}
		
	}
	
	
	
	

}
