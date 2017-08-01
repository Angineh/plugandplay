package com.tech.plugandplay.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class CommonUtil {
	
	public static String assignSysProp(String SysProperty, String SysValue) {

		if (System.getProperty(SysProperty) != null) {
			SysValue=System.getProperty(SysProperty);
		}
		return SysValue;
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}
	
	public static String generateRequest(int requestid, String cluster_prefix, String instancetype) throws JSONException, IOException{
		JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/main/resources/json/"+instancetype+"/request.json")), StandardCharsets.UTF_8));
		body.put("requestId", requestid);
		JSONArray json = body.getJSONArray("nodes");
		for (int i = 0; i < json.length(); i++) {
			JSONObject obj = json.getJSONObject(i);
			String host = json.getJSONObject(i).getString("hostname");
			host = host.replace("cluster_prefix", cluster_prefix);
			obj.put("hostname", host);
			json.put(i, obj);
		}
		body.put("nodes", json);
		
		System.out.println(body.toString());
		
		return body.toString();
	}
	
	public static File readURLWriteFile(URL url){
		String fileName = "/tmp/tmpimage";
		File file = new File(fileName);
		try {
		URLConnection conn = url.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(
                           new InputStreamReader(conn.getInputStream()));

		String inputLine;

		if (!file.exists()) {
			file.createNewFile();
		}

		//use FileWriter to write file
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		while ((inputLine = br.readLine()) != null) {
			bw.write(inputLine);
		}

		bw.close();
		br.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	public static String getImageType(File file){
	
	String imageType = null;
	ImageInputStream iis = null;
	try {
		iis = ImageIO.createImageInputStream(file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  // get all currently registered readers that recognize the image format

	  Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

	  if (!iter.hasNext()) {

	throw new RuntimeException("No readers found!");

	  }

	  // get the first reader

	  ImageReader reader = iter.next();

	  try {
		System.out.println("Format: " + reader.getFormatName());
		imageType = reader.getFormatName();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  // close stream

	  try {
		iis.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return imageType;

	}
	
	public static String createJWT(String name){
		Key key = MacProvider.generateKey();

		String compactJws = Jwts.builder()
		  .setSubject(name)
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		return compactJws;
	}
}
