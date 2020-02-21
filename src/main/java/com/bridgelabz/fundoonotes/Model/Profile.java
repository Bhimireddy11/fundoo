package com.bridgelabz.fundoonotes.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long prfilrId;
	 private String profilePicName;
	 public Profile(String profilePicName,UserDemo user) {
		 super();
		 this.profilePicName=profilePicName;
		 this.userLabel=user;
		
		 
	 }
	 
	public Profile() {
	}
	public UserDemo getUserLabel() {
		return userLabel;
	}
	public void setUserLabel(UserDemo userLabel) {
		this.userLabel = userLabel;
	}
	public String getProfilePicName() {
		return profilePicName;
	}
	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}
	
	
	
	
	 @JsonIgnore
	 @ManyToOne
	 @JoinColumn(name="userId")
	 private UserDemo userLabel;

	 }
	 
	  
	

