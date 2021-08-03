package com.policy.microservice.dto;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.policy.microservice.model.Hospital;

@SpringBootTest
public class ProviderDTOTest {
	
	
	ProviderDTO providerDTO;
	
	@Test
	@DisplayName("Testing if [ProviderDTO] is loading or not")
	public void processingRequestIsLoadedOrNot() {
		
		providerDTO = new ProviderDTO();
		
		assertNotNull(providerDTO);
		
	}
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Testing [ProviderDTO] is running correctly or not")
	public void testProviderDTO() {
		providerDTO = new ProviderDTO();
		Set<Hospital> providers = new HashSet<>();
		Hospital hospital = new Hospital("H11","Apollo","Chennai");
		providers.add(hospital);
		providerDTO.setProviders(providers);
		
		Iterator<Hospital> itr = providerDTO.getProviders().iterator();
		
		Hospital resultSet = itr.next();
		assertEquals("H11",resultSet.getHospitalId());
		assertEquals("Apollo",resultSet.getName());
		assertEquals("Chennai",resultSet.getLocation());
	}

}
