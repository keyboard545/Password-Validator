package com.brunch.passwordvalidator.integrationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.WebApplicationContext;

import com.brunch.passwordvalidator.PasswordvalidatorApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PasswordvalidatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PasswordvalidatorIntegrationTests {

	@Autowired
	WebApplicationContext wac;

	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}

	@Test
	public void testLettersDigitsPassVerify() throws Exception {
		mvc.perform(get("/validate/password/abcde12345").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
	}
	
	@Test
	public void testDigitsLettersPassVerify() throws Exception {
		mvc.perform(get("/validate/password/12345abcde").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
	}

	@Test
	public void testSizeTooShort() throws Exception {
		mvc.perform(get("/validate/password/abc1").contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testSizeTooLong() throws Exception {
		mvc.perform(get("/validate/password/abcdefghi123456").contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testDigitsOnly() throws Exception {
		mvc.perform(get("/validate/password/1234567890").contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testUpperCaseLettersOnly() throws Exception {
		mvc.perform(get("/validate/password/ABCDEFGH").contentType(MediaType.TEXT_PLAIN)).andExpect(status().is4xxClientError());
	}

	@Test
	public void testLowerCaseLettersOnly() throws Exception {
		mvc.perform(get("/validate/password/abcdefgh").contentType(MediaType.TEXT_PLAIN)).andExpect(status().is4xxClientError());
	}

	@Test
	public void testImmediateRepeatSequence() throws Exception {
		mvc.perform(get("/validate/password/abcdabcd123123").contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testUpperLowerCaseWithDigits() throws Exception {
		mvc.perform(get("/validate/password/ABCdef123").contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().is4xxClientError());
	}
}
