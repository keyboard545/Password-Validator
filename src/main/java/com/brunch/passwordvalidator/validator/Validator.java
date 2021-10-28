package com.brunch.passwordvalidator.validator;

import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

public interface Validator {
	public void validate(ValidateVo vo) throws InvalidPasswordException;
}
