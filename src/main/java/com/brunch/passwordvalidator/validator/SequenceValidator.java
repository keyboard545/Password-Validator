package com.brunch.passwordvalidator.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

@Component
public class SequenceValidator implements Validator {
	private static final Pattern INVALID_CHARACTERS_SEQUENCE = Pattern.compile("(?!(.+?)\\1).*");

	@Override
	public void validate(ValidateVo vo) throws InvalidPasswordException {
		Matcher matcher = INVALID_CHARACTERS_SEQUENCE.matcher(vo.getPassword());
		if (!matcher.matches()) {
			throw new InvalidPasswordException(ErrorMessage.REPEATED_CHARACTERS_SEQUENCE);
		}
	}
}
