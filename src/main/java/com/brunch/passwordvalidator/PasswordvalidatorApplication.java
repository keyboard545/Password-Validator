package com.brunch.passwordvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class PasswordvalidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordvalidatorApplication.class, args);
	}
}
 