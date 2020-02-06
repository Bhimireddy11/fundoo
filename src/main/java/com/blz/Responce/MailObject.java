package com.blz.Responce;

import java.io.Serializable;


import org.springframework.stereotype.Component;

@Component
public class MailObject implements Serializable {

	private static final long serialVersionUID = 1L;
	 String email;
	 String subject;
	 String message;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObject() {
		return subject;
	}
	public void setObject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSubject(String string) {
		
		
	}
	 

}
