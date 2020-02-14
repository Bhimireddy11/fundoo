package com.bridgelabz.fundoonotes.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Component
public class Collaborator {
	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long coalbId;
	private String email;
	

	@JsonIgnore
	@ManyToMany 
	
	@JoinColumn(name="noteId")
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	
	public void setNoteColab(Note noteColab) {
		
	}
		
	
	
/*
 * @Override public String toString() { return "collaborator[colabId=" + coalbId
 * + ", email=" + email+ "]"; } }
 */
	}


