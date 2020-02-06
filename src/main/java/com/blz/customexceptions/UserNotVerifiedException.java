package com.blz.customexceptions;

public class UserNotVerifiedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public UserNotVerifiedException(String message) {
		super(message);
	}

}
