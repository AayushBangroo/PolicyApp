package com.policy.microservice.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
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

import com.policy.microservice.dto.BenefitsDTO;
import com.policy.microservice.exception.ExpiredPolicyException;
import com.policy.microservice.exception.InvalidMemberIdException;
import com.policy.microservice.exception.InvalidPolicyId;
import com.policy.microservice.model.Benefits;
import com.policy.microservice.model.Policy;
import com.policy.microservice.repository.MemberPolicyRepo;
import com.policy.microservice.repository.PolicyRepo;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BenefitsServiceTest {

	@InjectMocks
	BenefitsService benefitsService;
	
	@Mock
	PolicyRepo policyRepo;
	
	@Mock
	MemberPolicyService memberPolicyService;
	
	@Mock
	MemberPolicyRepo memberPolicyRepo;
	
	@Test
    @DisplayName("Checking if [Benefits Service] is loading or not.")
	public void policyServiceIsLoaded(){
        assertNotNull(benefitsService);
    }
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("Testing get claim amount method")
	public void getBenefits() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
		
		Set<Benefits> benefits = new HashSet<>();
		benefits.add(new Benefits("B111","Coverage for COVID"));
		
		policy.setBenefits(benefits);
		
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		when(memberPolicyService.isValidMember(anyString())).thenReturn(true);
		
		when(memberPolicyService.isPremiumPaid(anyString())).thenReturn(true);
		
		BenefitsDTO benefitsDTO = benefitsService.getBenefits("P1001","MX1");
		
		assertEquals(benefits,benefitsDTO.getBenefits());
	}
	
	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing get claim amount method")
	public void getBenefitsInvalidPolicyIdException() {
		
		assertThrows(InvalidPolicyId.class, () -> Optional
				  .ofNullable(policyRepo.findById(anyString()).orElseThrow(() -> new InvalidPolicyId("Invalid Policy Id"))));
		
		benefitsService.getBenefits("P1001","MX1");
		
	}
	
	@Test(expected=InvalidMemberIdException.class)
	@DisplayName("Testing get claim amount method")
	public void getBenefitsInvalidMemberId() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
				
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		when(memberPolicyService.isValidMember(anyString())).thenReturn(false);
		
		benefitsService.getBenefits("P1001","MX1");
		
		throw new InvalidMemberIdException("Invalid member id...");
		
	}
	
	@Test(expected=ExpiredPolicyException.class)
	@DisplayName("Testing get claim amount method")
	public void getBenefitsExpiredPolicyException() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
				
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		when(memberPolicyService.isValidMember(anyString())).thenReturn(true);
		
		when(memberPolicyService.isPremiumPaid(anyString())).thenReturn(false);
		
		benefitsService.getBenefits("P1001","MX1");
		
		throw new ExpiredPolicyException("Premium not paid");
				
	}

}
