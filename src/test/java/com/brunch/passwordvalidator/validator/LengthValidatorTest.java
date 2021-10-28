package com.brunch.passwordvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

public class LengthValidatorTest {
	private static final String errorMessage = ErrorMessage.INVALID_LENGTH.getMessage();
	private Validator validator;

	@BeforeEach
	public void setUp() {
		validator = new LengthValidator();
	}

	@Test
	public void testValidate() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcdefghi")));
	}
 
	@Test
	public void testSizeEquals5() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcde")));
	}

	@Test
	public void testSizeLessThan5() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("abcd")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testSizeEquals12() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcdefghijkl")));
	}

	@Test
	public void testSizeMoreThan12() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("ABCDEFGHIJKLM")),
				"Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}
}
