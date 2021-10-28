package com.brunch.passwordvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.brunch.passwordvalidator.core.ErrorMessage;
import com.brunch.passwordvalidator.exception.InvalidPasswordException;
import com.brunch.passwordvalidator.vo.ValidateVo;

public class SequenceValidatorTest {
	private static final String errorMessage = ErrorMessage.REPEATED_CHARACTERS_SEQUENCE.getMessage();
	private Validator validator;

	@BeforeEach
	public void setUp() {
		validator = new SequenceValidator();
	}

	@Test
	public void testValidate() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcde123")));
	}

	@Test
	public void testImmediateRepeatSequence() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("abcabc")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testNotImmediateRepeatSequence() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("abcdabc")));
	}

	@Test
	public void testImmediateRepeatLetter() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("aa")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testNotImmediateRepeatLetter() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("ab")));
	}

	@Test
	public void testImmediateRepeatDigit() {
		InvalidPasswordException thrown = assertThrows(InvalidPasswordException.class,
				() -> validator.validate(new ValidateVo("11")), "Expected validate() to throw, but it didn't");
		assertTrue(thrown.getMessage().contains(errorMessage));
	}

	@Test
	public void testNotImmediateRepeatDigit() {
		Assertions.assertDoesNotThrow(() -> validator.validate(new ValidateVo("12")));
	}
}
