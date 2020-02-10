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
	
	private String firstName;
	
	private String lastName;

	private long phone;

	private String pswd;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
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
		return "UserDemo [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", pswd=" + pswd + ", email=" + email + ", isVerified=" + isVerified + ", createdAt=" + createdAt
				+ "]";
	}

}
