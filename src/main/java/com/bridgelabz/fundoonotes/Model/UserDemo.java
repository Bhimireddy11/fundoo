package com.bridgelabz.fundoonotes.Model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name="userregister")
public class UserDemo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String name;
	
//	private String lastName;

	private long phoneNumber;

	private String password;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(columnDefinition = "boolean default false",nullable = false)
	private boolean isVerified;
	
	private String createdAt;
	
	public String getCreatedAt() {
		return createdAt;
	}

	

	public void setCreatedAt(String string) {
		this.createdAt = string;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getname() {
		return name;
	}

	public void setFirstName(String firstName) {
		this.name = name;
	}

//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "UserDemo [userId=" + userId + ", name=" + name + ", phone=" + phoneNumber
				+ ", pswd=" + password + ", email=" + email + ", isVerified=" + isVerified + ", createdAt=" + createdAt
				+ "]";
	}
//	 lastName=" + lastName + ",
}
