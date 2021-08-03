package com.policy.microservice.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ErrorMessageTest {

	ErrorMessage errorMessage;
	
	@Test
	public void test() {
		errorMessage = new ErrorMessage();
		assertThat(errorMessage).isNotNull();
	}
	
	@Test
	public void getterSetterTest() {
		Date date = new Date();
		errorMessage = new ErrorMessage();
		errorMessage.setStatusCode(200);
		errorMessage.setTimestamp(date);
		errorMessage.setMessage("OK");
		errorMessage.setDescription("Application is working fine. No error message to show.");
		
		assertEquals(200,errorMessage.getStatusCode());
		assertEquals(date,errorMessage.getTimestamp());
		assertEquals("OK",errorMessage.getMessage());
		assertEquals("Application is working fine. No error message to show.",errorMessage.getDescription());
		
	}
	
	@Test
	public void getterSetterTest2() {
		
		Date date = new Date();
		errorMessage = new ErrorMessage();
		errorMessage.setStatusCode(404);
		errorMessage.setTimestamp(date);
		errorMessage.setMessage("NOT FOUND");
		errorMessage.setDescription("Application has errors. View stack trace to show errors.");
		
		assertEquals(404,errorMessage.getStatusCode());
		assertEquals(date,errorMessage.getTimestamp());
		assertEquals("NOT FOUND",errorMessage.getMessage());
		assertEquals("Application has errors. View stack trace to show errors.",errorMessage.getDescription());
		
	}

	
}
