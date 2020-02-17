package com.bridgelabz.fundoonotes.DTO;

import lombok.Data;

@Data
public class CollaboratorDto {
	 private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
