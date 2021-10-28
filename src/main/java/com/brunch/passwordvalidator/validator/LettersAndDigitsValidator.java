package com.brunch.passwordvalidator.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

@Component
public class LettersAndDigitsValidator implements Validator {
	private static final Pattern LETTERS_AND_DIGITS_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])[a-z0-9]*$");

	@Override
	public void validate(ValidateVo vo) throws InvalidPasswordException {
		Matcher matcher = LETTERS_AND_DIGITS_PATTERN.matcher(vo.getPassword());
		if (!matcher.matches()) {
			throw new InvalidPasswordException(ErrorMessage.INVALID_LETTERS_AND_DIGITS);
		}
	}
}
