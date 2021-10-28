package com.brunch.passwordvalidator.exception;

import com.brunch.passwordvalidator.core.ErrorMessage;

public class InvalidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
	
	}
	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(ErrorMessage message) {
		super(message.getMessage());
	}
}
