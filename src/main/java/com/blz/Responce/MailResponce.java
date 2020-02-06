package com.blz.Responce;

import org.springframework.stereotype.Component;

@Component
public class MailResponce {
	public String formMessage(String url,String token) {
		return url+"/"+token;

}
}