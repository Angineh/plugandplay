package com.arbitrage.api;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.tech.plugandplay.util.CommonUtil;

public class GetBTCHistory {
	
	@Test
	public static void getCompany() throws NoSuchAlgorithmException, InvalidKeyException, ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2010-12-20");
		Date endDate = formatter.parse("2010-12-26");
		
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
		    // Do your job here with `date`.
		    System.out.println(date.toEpochDay());
		}
		//https://poloniex.com/public?command=returnTradeHistory&currencyPair=USDT_BTC&start=1504224000&end=1506816000
		String url = "https://api.coinbase.com/v2/prices/BTC-USD/spot";
		long now = Instant.now().toEpochMilli();
		String secret = "wTHXJMw4dBBG0grDQDguPi72hEl3yuu5";
	    String message = String.valueOf(now)+"GET"+"Message";

	    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	    sha256_HMAC.init(secret_key);

	    String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
	    //System.out.println(hash);
		
		Header key = new Header("CB-ACCESS-KEY","vEtUSVJlvFjPcPKo");
		Header sign = new Header("CB-ACCESS-SIGN", hash);		
		Header time = new Header("CB-ACCESS-TIMESTAMP", String.valueOf(now));
		Headers hdrs = new Headers(key,sign,time);
		Response response = RestAssured.given().contentType(ContentType.JSON).headers(hdrs).get(url);
		System.out.println("Status code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.getBody().asString());
	}

	
}
