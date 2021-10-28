package com.brunch.passwordvalidator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.core.ValidateType;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.service.impl.ValidateService;
import com.brunch.passwordvalidator.vo.ValidateVo;

// Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
// Must be between 5 and 12 characters in length.
// Must not contain any sequence of characters immediately followed by the same sequence.

@RestController
@RequestMapping(path = "/validate")
public class ValidationController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ValidateService validateService;

	@RequestMapping(path = "/password/{password}", method = RequestMethod.GET)
	public ResponseEntity<Object> validatePassword(@PathVariable("password") String password) {
		logger.info("Validate password");

		ValidateVo vo = new ValidateVo();
		vo.setPassword(password);
		try { 
			if (null == password || password.isEmpty()) {
				throw new InvalidPasswordException(ErrorMessage.EMPTY_INPUT);
			}
			validateService.executeValidation(ValidateType.VALIDATE_PASSWORD_ONLY, vo);
		} catch (Exception ex) {
			logger.error("Password verify failed.");
			return new ResponseEntity<>("Password verify failed. Error message = [" + ex.getMessage() + "]",
					HttpStatus.BAD_REQUEST);
		}
		logger.info("Password verified.");
		return ResponseEntity.ok("Password verified.");
	}
}
