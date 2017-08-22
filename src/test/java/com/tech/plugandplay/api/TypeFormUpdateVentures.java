package com.tech.plugandplay.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TypeFormUpdateVentures {
	
	
	@Test
	public static void test() throws IOException {
		
		//String hostname = "54.145.172.103";
		String hostname = "playbook.pnptc.com";
		//String hostname = "localhost";
		
		//FileInputStream fstream = new FileInputStream("src/main/resources/json/typeform/missing.json");
		FileInputStream fstream = new FileInputStream("src/main/resources/json/typeform/homesidekick.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		int i = 0;
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  System.out.println(i+": "+strLine);
		  Response response = null;
		  response = RestAssured.given().redirects().follow(true).contentType(ContentType.JSON).body(strLine).post("http://"+hostname+":8080/plugandplay/api/v1/ventures/newupdate");	
		  //response = RestAssured.given().redirects().follow(true).contentType(ContentType.JSON).body(strLine).post("http://"+hostname+":8080/plugandplay/api/v1/ventures/new");	
			
		  System.out.println(response.getStatusLine());
		  System.out.println(response.getBody().asString());
		  
/*		  if(i == 100){
			  break;
		  }*/
		  i++;
		  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		//Close the input stream
		br.close();
		
		
		
	}
	
	
	
	

}
