package com.tech.plugandplay.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.tech.plugandplay.util.Crypto;

public class Passtest {
	
	
	@Test
	public static void test() throws IOException {
		String pass = "bc002dac0a8ce8d410933fcd60bc648068d15f967af9504e5292ffc995b27d3b";
		
		System.out.println(Crypto.getHash("pnp4life!"));
		System.out.println(pass);
	}
	
	
	
	

}
