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
public class Collaborator {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long colabId;
		
		private String email;
		
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name="noteId")
		private Note noteColab;

	}


