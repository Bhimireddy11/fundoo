package com.blz.Responce;

import org.springframework.stereotype.Component;

@Component
public class Responce {
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
	public Responce(String message,int statusCode) {
		this.message=message;
		this.statusCode=statusCode;
	}
	public  Responce(String message,int statusCode,Object obj) {
		this.message=message;
		this.statusCode=statusCode;
		this.obj=obj;
	}
}
