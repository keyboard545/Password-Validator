package com.brunch.passwordvalidator.core;

public enum ValidateType {
	VALIDATE_PASSWORD_ONLY("validatePasswordOnly");

	private final String validateType;

	ValidateType(String validateType) {
		this.validateType = validateType;
	}

	public String validateType() {
		return this.validateType;
	}

}
