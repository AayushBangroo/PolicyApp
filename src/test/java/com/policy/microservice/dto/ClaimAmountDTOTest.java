package com.policy.microservice.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimAmountDTOTest {
	
	ClaimAmountDTO claimAmountDTO;
	
	@Test
	@DisplayName("Testing if [ClaimAmountDTO] is loading or not")
	public void processingRequestIsLoadedOrNot() {
		
		claimAmountDTO = new ClaimAmountDTO();
		
		assertNotNull(claimAmountDTO);
		
	}
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	@DisplayName("Testing if [ClaimAmountDTO] is running or not")
	public void testClaimAmountDTO() {
		
		claimAmountDTO = new ClaimAmountDTO();
		claimAmountDTO.setEligibleAmount(1000.0);
		
		assertEquals(1000.00,claimAmountDTO.getEligibleAmount(),0.1);
	}
	
	@Test
	public void testClaimAmountDTOConstructor() {
		
		claimAmountDTO = new ClaimAmountDTO(2000.0);
		
		assertEquals(2000.0,claimAmountDTO.getEligibleAmount(),0.1);
	}

}
