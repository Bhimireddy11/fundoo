package com.blz.Responce;

import org.springframework.stereotype.Component;

@Component
public class UserAuntication {
	private String token;  
	private  int statusCode;
	private  Object obj;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public UserAuntication(String token,int statusCode,Object obj) {
		this.token=token;
		this .statusCode=statusCode;
		this.obj=obj;
				
	}

}
