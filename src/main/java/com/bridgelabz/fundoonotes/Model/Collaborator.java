package com.bridgelabz.fundoonotes.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Component
public class Collaborator {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long coalbId;
	private String email;


	public long getCoalbId() {
		return coalbId;
	}

	public void setCoalbId(long coalbId) {
		this.coalbId = coalbId;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public Note getNoteColab() {
		return noteColab;
	}



	public void setNoteColab(Note noteColab) {
		this.noteColab = noteColab;
	}



	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="noteId")
	private Note noteColab;
	

}


