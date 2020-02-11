package com.bridgelabz.fundoonotes.customexceptions;

public class NoteIdNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public NoteIdNotFoundException(String message) {
	super(message);

}
}