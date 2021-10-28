package com.brunch.passwordvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

public class LettersAndDigitsValidatorTest {
	private static final String errorMessage = ErrorMessage.INVALID_LETTERS_AND_DIGITS.getMessage();
	private Validator validator;

	@BeforeEach
	public void setUp() {
		validator = new LettersAndDigitsValidator();
	}

	@Test
	public void testValidate() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcde12345")));
	}

	@Test
	public void testUpperCaseLettersOnly() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("ABCDE")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testLowerCaseLettersOnly() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("abcde")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testDigitsOnly() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("12345")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testSpecialLettersOnly() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("!@#$%")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testSpecialLettersWithDigits() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("!@#$%12345")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testUpperCaseWithDigits() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("ABCDE123")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testUpperLowerCaseWithDigits() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("ABCdef123")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testLowerUpperCaseWithDigits() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("abcDEF123")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

}
