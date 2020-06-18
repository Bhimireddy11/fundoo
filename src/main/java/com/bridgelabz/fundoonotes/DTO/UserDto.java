package com.bridgelabz.fundoonotes.DTO;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {
	@NotBlank
	private String name;
//	@NotBlank
//	private String lastName;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String password;
	@NotBlank
	@Email
	private String email;
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhone(String phone) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPswd(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}