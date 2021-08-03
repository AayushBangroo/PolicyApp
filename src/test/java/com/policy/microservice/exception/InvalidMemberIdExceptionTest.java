package com.policy.microservice.exception;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidMemberIdExceptionTest {
	
	InvalidMemberIdException invalidMemberIdException = new InvalidMemberIdException("Exception");

	@Test
	@DisplayName("Testing if [InvalidMemberIdException] is working or not")
	public void testInvalidMemberIdException() {
		
		assertNotNull(invalidMemberIdException);
	}


}
