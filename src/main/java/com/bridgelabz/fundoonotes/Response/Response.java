
package com.bridgelabz.fundoonotes.Response;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.DTO.UserDto;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Model.UserDemo;

public class Response {

	
	String message;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	int statusCode;
	Object data;
	private Object obj;
	
	public Response(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public Response(Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}
	
	public Response(String message) {
		super();
		this.message = message;
		
	}
	public Response(String message, int statusCode, Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		
		this.data = data;
	}
	
	
	



	
//	public int getStatusCode() {
//		return statusCode;
//	}
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}
	
	
	
}
