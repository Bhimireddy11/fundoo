package com.bridgelabz.fundoonotes.DTO;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String phone;
	@NotBlank
	private String pswd;
	@NotBlank
	@Email
	private String email;
	public String getEmail() {

		return email;
	}
	public CharSequence getPswd() {

		return pswd;
	}
	
	
}