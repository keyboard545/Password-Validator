package com.brunch.passwordvalidator.core;

public enum ErrorMessage {
	EMPTY_INPUT("Password must not be empty."),
	INVALID_LENGTH("Password must be between 5 and 12 characters in length."),
	INVALID_LETTERS_AND_DIGITS("Password must consist of a mixture of lowercase letters and numerical digits only, with at least one of each."),
	REPEATED_CHARACTERS_SEQUENCE("Must not contain any sequence of characters immediately followed by the same sequence.");
	
	String message;
	
	ErrorMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
