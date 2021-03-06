package com.brunch.passwordvalidator.core;

public enum ErrorMessage {
	EMPTY_INPUT("Must not be empty."),
	INVALID_LENGTH("Must be between %d and %d characters in length."),
	INVALID_LETTERS_AND_DIGITS("Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each."),
	REPEATED_CHARACTERS_SEQUENCE("Must not contain any sequence of characters immediately followed by the same sequence.");
	
	String message;
	
	ErrorMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static String getInvalidLength(int minLength, int maxLength) {
		String invalidLength = INVALID_LENGTH.getMessage();
		return String.format(invalidLength, minLength, maxLength);
		
	}
}
