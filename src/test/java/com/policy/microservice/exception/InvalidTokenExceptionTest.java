package com.policy.microservice.exception;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidTokenExceptionTest {

	InvalidTokenException invalidTokenException = new InvalidTokenException("Exception");
	
	@Test
	@DisplayName("Testing is [InvalidPolicyIdException] is running or not")
	public void testInvalidPolicyIdException() {
		
		assertNotNull(invalidTokenException);
	}

}
