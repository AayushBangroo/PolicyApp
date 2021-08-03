package com.policy.microservice.exception;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpiredPolicyExceptionTest {

	ExpiredPolicyException expiredPolicyException = new ExpiredPolicyException("Exception");

	@Test
	@DisplayName("Testing if [ExpiredPolicyException] is running or not")
	public void testExpiredPolicyException() {
		
		assertNotNull(expiredPolicyException);
	}

}
