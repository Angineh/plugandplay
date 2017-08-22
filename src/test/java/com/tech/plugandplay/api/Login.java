package com.tech.plugandplay.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class Login {
	
	
	@Test
	public static void test() throws IOException {
		String content = "{\"email\":\"raj.d.desai@gmail.com\",\"password\":\"temp4now\"}";
		/*String hostname = "54.145.172.103";*/
		String hostname = "playbook.pnptc.com";
		/*String hostname = "localhost";*/
		//String content = new String(Files.readAllBytes(Paths.get("src/main/resources/json/typeform/bangk.json")), StandardCharsets.UTF_8);
		Response response = null;
		//for(int i = 0; i < 100; i ++){
		response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).body(content).post("http://"+hostname+":/rest/plugandplay/api/v1/login");	
		//}
		
		
		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());
	}
	
	
	
	

}
