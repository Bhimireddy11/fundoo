package com.blz.customexceptions;

public class MyExceptionHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public  MyExceptionHandler(String message) {
		super(message);
	}
	
 
}
