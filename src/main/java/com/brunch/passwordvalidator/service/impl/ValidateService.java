package com.brunch.passwordvalidator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunch.passwordvalidator.core.ValidateType;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.service.IValidateService;
import com.brunch.passwordvalidator.validator.LengthValidator;
import com.brunch.passwordvalidator.validator.LettersAndDigitsValidator;
import com.brunch.passwordvalidator.validator.SequenceValidator;
import com.brunch.passwordvalidator.validator.Validator;
import com.brunch.passwordvalidator.vo.ValidateVo;

@Service
public class ValidateService implements IValidateService {

	@Autowired
	private LengthValidator lengthValidator;

	@Autowired
	private LettersAndDigitsValidator lettersAndDigitsValidator;

	@Autowired
	private SequenceValidator sequenceValidator;

	public void executeValidation(ValidateType validateRule, ValidateVo vo) throws InvalidPasswordException, Exception {
		if (validateRule == null) {
			throw new Exception("ValidateType is null.");
		}
		for (Validator validator : getPasswordValidatetorList(validateRule)) {
			validator.validate(vo);
		} 
	}

	public List<Validator> getPasswordValidatetorList(ValidateType validateRule) {
		List<Validator> validatorList = new ArrayList<Validator>();

		switch (validateRule) {
		case VALIDATE_PASSWORD_ONLY:
			validatorList.add(this.lengthValidator);
			validatorList.add(this.lettersAndDigitsValidator);
			validatorList.add(this.sequenceValidator);
			return validatorList;
		default:
			return null;
		}
	}
}
