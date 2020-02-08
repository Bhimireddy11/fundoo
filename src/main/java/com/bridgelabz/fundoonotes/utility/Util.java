package com.bridgelabz.fundoonotes.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

	
	public static String dateTime() {
		
		// will give us the current time and date 
	    LocalDateTime current = LocalDateTime.now(); 
	    System.out.println("current date and time : "+ 
	                        current); 
	   
	   
	    // to print in a particular format 
	    DateTimeFormatter format =  
	      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");   
	    
	    String formatedDateTime = current.format(format);   
	     
	    System.out.println("in foramatted manner "+ 
	                        formatedDateTime); 
		return formatedDateTime;
	}
	
	
	public static String passwordEncoder(String message) {

		 //Creating the MessageDigest object  
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

		    //Passing data to the created MessageDigest Object
		    md.update(message.getBytes());
		    
		    //Compute the message digest
		    byte[] digest = md.digest();      
		    System.out.println(digest);
		   
		    //Converting the byte array in to HexString format
		    StringBuffer hexString = new StringBuffer();
		    
		    for (int i = 0;i<digest.length;i++) {
		       hexString.append(Integer.toHexString(0xFF & digest[i]));
		    }
		    System.out.println("Hex format : " + hexString.toString());
		    return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
 
			e.printStackTrace();
		}
		return null;

	}

	public static boolean matches(String dbPswd, String userPswd) {
		
		if(dbPswd.equals(userPswd)) {
			return true;
		}
		return false;
	}

}
