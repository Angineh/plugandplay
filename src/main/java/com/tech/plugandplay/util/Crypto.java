package com.tech.plugandplay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
	
    public static final String HASH_ALGORITHM = "SHA-256";
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String hash = getHash("test123");
        System.out.println(hash + " | length: " + hash.length() );
    }
 
    public static String getHash(String value) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            // TODO: log this properly
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        byte[] byteData = md.digest(value.getBytes());
 
        // convert byte[] to hex string
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
 
        return sb.toString();
    }
 
}
