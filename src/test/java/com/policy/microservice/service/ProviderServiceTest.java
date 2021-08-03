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

import com.policy.microservice.dto.ProviderDTO;
import com.policy.microservice.exception.InvalidPolicyId;
import com.policy.microservice.model.Hospital;
import com.policy.microservice.model.Policy;
import com.policy.microservice.repository.PolicyRepo;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProviderServiceTest {
	
	@InjectMocks
	ProviderService providerService;
	
	@Mock
	PolicyRepo policyRepo;
	
	@Test
    @DisplayName("Checking if [Provider Service] is loading or not.")
    public void policyServiceIsLoaded(){
        assertNotNull(providerService);
    }	

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("Testing get providers method")
	public void getProviders() {
		
		Set<Hospital> hospitals = new HashSet<>();
		hospitals.add(new Hospital("H11","Apollo","Chennai"));
		
		Policy policy = new Policy("P1001","Health",2300000.0,12000.0);
		policy.setHospitals(hospitals);
		
		when(policyRepo.findById(anyString())).thenReturn(Optional.of(policy));
		
		ProviderDTO providerDTO = providerService.getProviders("P1001");
		
		assertEquals(hospitals,providerDTO.getProviders());
	}
	
	@Test(expected=InvalidPolicyId.class)
	@DisplayName("Testing Exceptions")
	public void getProvidersInvalidPolicyException() {
		
		assertThrows(InvalidPolicyId.class, () -> Optional
				  .ofNullable(policyRepo.findById(anyString()).orElseThrow(() -> new InvalidPolicyId("Invalid Policy Id"))));
		
		providerService.getProviders("P1001");
	}

}
