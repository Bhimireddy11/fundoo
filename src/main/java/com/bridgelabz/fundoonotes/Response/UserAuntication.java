package com.bridgelabz.fundoonotes.Response;

import com.bridgelabz.fundoonotes.DTO.LoginDetails;

public class UserAuntication {
	private String token;  
	
	private  Object obj;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
//	public int getStatusCode() {
//		return statusCode;
//	}
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public UserAuntication(String token,Object obj) {
		this.token=token;
		//this .statusCode=statusCode;
		this.obj=obj;
				
	}

	
	

}
