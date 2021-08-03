package com.policy.microservice.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatingDTOTest {
	
	ValidatingDTO validatingDTO;

	@Test
	public void testValidatingDTO() {
		
		validatingDTO = new ValidatingDTO();
		assertThat(validatingDTO).isNotNull();
	}

	@Test
	public void testIsValidStatus() {
		validatingDTO = new ValidatingDTO();
		validatingDTO.setValidStatus(false);
		assertEquals(false,validatingDTO.isValidStatus());
	}

	@Test
	public void testSetValidStatus() {
		validatingDTO = new ValidatingDTO();
		validatingDTO.setValidStatus(true);
		assertEquals(true,validatingDTO.isValidStatus());
	}

}
