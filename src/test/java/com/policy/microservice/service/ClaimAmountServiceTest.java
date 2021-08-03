package com.policy.microservice.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.policy.microservice.dto.ClaimAmountDTO;
import com.policy.microservice.exception.ExpiredPolicyException;
import com.policy.microservice.exception.InvalidMemberIdException;
import com.policy.microservice.exception.InvalidPolicyId;
import com.policy.microservice.model.Policy;
import com.policy.microservice.repository.MemberPolicyRepo;
import com.policy.microservice.repository.PolicyRepo;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ClaimAmountServiceTest {
	
	@InjectMocks
	ClaimAmountService claimAmountService;
	
	@Mock
	PolicyRepo policyRepo;
	
	@Mock
	MemberPolicyService memberPolicyService;
	
	@Mock
	MemberPolicyRepo memberPolicyRepo;

	@Test
    @DisplayName("Checking if [Claim Amount Service] is loading or not.")
    public void claimAmountServiceIsLoaded(){
        assertNotNull(claimAmountService);
    }
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	@DisplayName("Testing get claim amount method")
	public void getClaimAmount() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
				
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		when(memberPolicyService.isValidMember("MX1")).thenReturn(true);
		
		when(memberPolicyService.isPremiumPaid("MX1")).thenReturn(true);
		
		ClaimAmountDTO claimAmountDTO = claimAmountService.getClaimAmount("P1001","MX1");
		
		assertEquals(2300000.0,claimAmountDTO.getEligibleAmount(),0.1);
	}
	
	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing get claim amount method")
	public void getClaimAmountInvalidPolicyIdException() {
		
		assertThrows(InvalidPolicyId.class, () -> Optional
			      .ofNullable(policyRepo.findById(anyString()).orElseThrow(() -> new InvalidPolicyId("Invalid Policy Id"))));
		
		claimAmountService.getClaimAmount("P1001","MX1");
		
	}
	
	@Test(expected=InvalidMemberIdException.class)
	@DisplayName("Testing get claim amount method")
	public void getClaimAmountInvalidMemberId() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
				
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		when(memberPolicyService.isValidMember(anyString())).thenReturn(false);
		
		claimAmountService.getClaimAmount("P1001","MX1");
		
		throw new InvalidMemberIdException("Imvalid member id...");
		
	}
	
	@Test(expected=ExpiredPolicyException.class)
	@DisplayName("Testing get claim amount method")
	public void getClaimAmountExpiredPremiumException() {
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
				
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));

		when(memberPolicyService.isValidMember(anyString())).thenReturn(true);
		
		when(memberPolicyService.isPremiumPaid(anyString())).thenReturn(false);
		
		claimAmountService.getClaimAmount("P1001","MX1");
		
		throw new ExpiredPolicyException("Premium not paid...");
		
	}

}
