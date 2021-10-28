package com.brunch.passwordvalidator.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

@Component
public class LengthValidator implements Validator {
	private int minLength;
	private int maxLength;
	
	@Autowired
	public void configure(@Value("${password.validation.min.length}") int minLength,
			@Value("${password.validation.max.length}") int maxLength) {
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	
	public LengthValidator() {
		
	}

	public LengthValidator(int minLength, int maxLength) {
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	
	@Override
	public void validate(ValidateVo vo) throws InvalidPasswordException {
		String regex = String.format(".{%d,%d}", minLength, maxLength);
		Pattern LENGTH_PATTERN = Pattern.compile(regex);
		Matcher matcher = LENGTH_PATTERN.matcher(vo.getPassword());
		if (!matcher.matches()) {
			throw new InvalidPasswordException(ErrorMessage.getInvalidLength(this.minLength, this.maxLength));
		}
	}
}
