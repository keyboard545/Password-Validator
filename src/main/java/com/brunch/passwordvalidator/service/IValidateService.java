package com.brunch.passwordvalidator.service;

import com.brunch.passwordvalidator.core.ValidateType;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

public interface IValidateService {

	public void executeValidation(ValidateType validateRule, ValidateVo vo) throws InvalidPasswordException, Exception;

}
