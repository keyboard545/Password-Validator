package com.brunch.passwordvalidator.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import com.brunch.passwordvalidator.core.ValidateType;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.validator.LengthValidator;
import com.brunch.passwordvalidator.validator.LettersAndDigitsValidator;
import com.brunch.passwordvalidator.validator.SequenceValidator;
import com.brunch.passwordvalidator.vo.ValidateVo;

@ExtendWith(MockitoExtension.class)
@Service
public class ValidateServiceTest {

	@Mock
	private LengthValidator lengthValidator;

	@Mock
	private LettersAndDigitsValidator lettersAndDigitsValidator;

	@Mock
	private SequenceValidator sequenceValidator;

	@InjectMocks
	private ValidateService validateService;

	@Test
	public void validate() throws InvalidPasswordException, Exception {
		Assertions.assertDoesNotThrow(() -> validateService.executeValidation(ValidateType.VALIDATE_PASSWORD_ONLY,
				new ValidateVo("abcd1234")));
	}

	@Test
	public void testExecuteValidationNoValidateRule() throws InvalidPasswordException, Exception {
		Exception thrown = assertThrows(Exception.class,
				() -> validateService.executeValidation(null, new ValidateVo("abcd1234")),
				"Expected executeValidation() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains("ValidateType is null."));
	}

	@Test
	public void testGetPasswordValidatetorListNoValidateRule() throws InvalidPasswordException, Exception {
		assertThrows(NullPointerException.class, () -> validateService.getPasswordValidatetorList(null),
				"Expected getPasswordValidatetorList() to throw, but it didn't");
	}
}
