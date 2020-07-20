package com.bridgelabz.fundoonotes.customexceptions;

import org.springframework.http.HttpStatus;

import com.bridgelabz.fundoonotes.Model.Note;

public class NoteIdNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public NoteIdNotFoundException(String message) {
	super(message);

}
	
}