package com.brunch.passwordvalidator.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

@Component
public class LengthValidator implements Validator {
	private static final Pattern lENGTH_PATTERN = Pattern.compile(".{5,12}");

	@Override
	public void validate(ValidateVo vo) throws InvalidPasswordException {
		Matcher matcher = lENGTH_PATTERN.matcher(vo.getPassword());
		if (!matcher.matches()) {
			throw new InvalidPasswordException(ErrorMessage.INVALID_LENGTH);
		}
	}
}
