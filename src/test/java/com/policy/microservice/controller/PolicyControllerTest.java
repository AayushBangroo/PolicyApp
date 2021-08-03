package com.policy.microservice.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.policy.microservice.controller.PolicyMicroserviceController;

import com.policy.microservice.dto.BenefitsDTO;
import com.policy.microservice.dto.ClaimAmountDTO;
import com.policy.microservice.dto.ProviderDTO;
import com.policy.microservice.dto.ValidatingDTO;
import com.policy.microservice.exception.ExpiredPolicyException;
import com.policy.microservice.exception.InvalidMemberIdException;
import com.policy.microservice.exception.InvalidPolicyId;
import com.policy.microservice.exception.InvalidTokenException;
import com.policy.microservice.feign.AuthClient;
import com.policy.microservice.model.Benefits;
import com.policy.microservice.model.Hospital;
import com.policy.microservice.model.Policy;
import com.policy.microservice.service.BenefitsService;
import com.policy.microservice.service.ClaimAmountService;
import com.policy.microservice.service.ProviderService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PolicyControllerTest {
	
	@InjectMocks
	PolicyMicroserviceController policyController;
	
	@Mock
	ProviderService providerService;
	
	@Mock
	BenefitsService benefitsService;
	
	@Mock
	ClaimAmountService claimAmountService;
	
	@Mock
	AuthClient authClient;
	
	@Test
	@DisplayName("Testing if [PolicyMicroserviceController] is running or not")
	public void processingRequestIsLoadedOrNot() {
		
		policyController = new PolicyMicroserviceController();
		
		assertThat(policyController).isNotNull();
		
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Testing get chain of providers is working correctly or not")
	public void testGetChainOfProviders() {
		Set<Hospital> hospitals = new HashSet<>();
		hospitals.add(new Hospital("H11","Apollo","Chennai"));
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
		policy.setHospitals(hospitals);
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(providerService.getProviders("P1001")).thenReturn(new ProviderDTO(hospitals));
			
		ResponseEntity<ProviderDTO> response = policyController.getChainOfProviders("P1001","token");
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(hospitals,((ProviderDTO) response.getBody()).getProviders());
	}
	
	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing get chain of providers invalid policy id exception")
	public void testGetChainOfProviders_Fails1() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(providerService.getProviders(anyString())).thenThrow(InvalidPolicyId.class);
		
		policyController.getChainOfProviders("P1001","token");
		
		throw new InvalidPolicyId("Invalid policy id...");
		
	}
	
	@Test(expected=InvalidTokenException.class)
	@DisplayName("Testing get chain of providers feign exception")
	public void testGetChainOfProviders_Fails2() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		
		policyController.getChainOfProviders("P1001","token");
		
		throw new InvalidTokenException("Invalid token exception..."); 
	}

	@Test
	@DisplayName("Testing get eligible benefits is working or not")
	public void testGetEligibleBenefits() {
		Set<Benefits> benefits = new HashSet<>();
		benefits.add(new Benefits("B111","Coverage for COVID"));
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
		policy.setBenefits(benefits);
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(benefitsService.getBenefits(anyString(),anyString())).thenReturn(new BenefitsDTO(benefits));
		
		ResponseEntity<BenefitsDTO> response = policyController.getEligibleBenefits("P1001","M101","token");
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(benefits,((BenefitsDTO) response.getBody()).getBenefits());	
	}

	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing get eligible benefits invalid policy id  excpetion")
	public void testGetEligibleBenefits_Fails1() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(benefitsService.getBenefits(anyString(),anyString())).thenThrow(InvalidPolicyId.class);
		
		policyController.getEligibleBenefits("P1002","M1","token");
		
		throw new InvalidPolicyId("Invalid policy id...");
		
	}
	
	@Test(expected=InvalidMemberIdException.class)
	@DisplayName("Testing get eligible benefits invalid member id exception")
	public void testGetEligibleBenefits_Fails2() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(benefitsService.getBenefits(anyString(),anyString())).thenThrow(InvalidMemberIdException.class);
		
		policyController.getEligibleBenefits("P1002","MX1","token");
		
		throw new InvalidMemberIdException("Invalid member id...");
	}
	
	@Test(expected=ExpiredPolicyException.class)
	@DisplayName("Testing get eligible benefits expired premium exception")
	public void testGetEligibleBenefits_Fails3() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(benefitsService.getBenefits(anyString(),anyString())).thenThrow(ExpiredPolicyException.class);
		
		policyController.getEligibleBenefits("P1002","M1","token");
		
		throw new ExpiredPolicyException("Premium not paid...");
	}
	
	@Test(expected=InvalidTokenException.class)
	@DisplayName("Testing get chain of providers feign exception")
	public void testEligibleBenefits_Fails4() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		
		policyController.getEligibleBenefits("P1002","M1","token");
		
		throw new InvalidTokenException("Invalid token exception");
		
	}

	@Test
	@DisplayName("Testing get eligible benefits is working or not")
	public void testGetEligibleAmount() {
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimAmountService.getClaimAmount(anyString(),anyString())).thenReturn(new ClaimAmountDTO(policy.getSumInsured()));
		
		
		ResponseEntity<ClaimAmountDTO> response = policyController.getEligibleAmount("P1001","M101","token");
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(policy.getSumInsured(),((ClaimAmountDTO) response.getBody()).getEligibleAmount(),0.1);
	}
	
	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing get eligible benefits invalid policy id  exception")
	public void testGetEligibleAmount_Fails1() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimAmountService.getClaimAmount(anyString(),anyString())).thenThrow(InvalidPolicyId.class);
		
		policyController.getEligibleAmount("P1002","M1","token");
		
		throw new InvalidPolicyId("Invalid policy id...");
		
	}
	
	@Test(expected=InvalidMemberIdException.class)
	@DisplayName("Testing get eligible benefits invalid member id exception")
	public void testGetEligibleAmount_Fails2() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimAmountService.getClaimAmount(anyString(),anyString())).thenThrow(InvalidMemberIdException.class);
		
		policyController.getEligibleAmount("P1002","MX1","token");
		
		throw new InvalidMemberIdException("Invalid member id...");
	}
	
	@Test(expected=ExpiredPolicyException.class)
	@DisplayName("Testing get eligible benefits expired premium exception")
	public void testGetEligibleAmount_Fails3() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(claimAmountService.getClaimAmount(anyString(),anyString())).thenThrow(ExpiredPolicyException.class);
		
		policyController.getEligibleAmount("P1002","M1","token");
		
		throw new ExpiredPolicyException("Premium not paid...");
	}
	
	@Test(expected=InvalidTokenException.class)
	@DisplayName("Testing get claim amount feign exception")
	public void testGetEligibleAmount_Fails4() {
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		
		policyController.getEligibleAmount("P1002","M1","token");
		
		throw new InvalidTokenException("Invalid token exception...");
		
	}

}
