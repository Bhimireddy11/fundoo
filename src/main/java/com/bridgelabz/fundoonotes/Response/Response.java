
package com.bridgelabz.fundoonotes.Response;

import com.bridgelabz.fundoonotes.Model.UserDemo;

public class Response {
	private String message;
	private int statusCode;
	private Object obj;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	
	public  Response(String message,Object obj) {
		this.message=message;
		
		this.obj=obj;
	}
}
