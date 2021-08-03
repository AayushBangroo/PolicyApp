package com.policy.microservice.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.policy.microservice.model.Benefits;

@SpringBootTest
public class BenefitsDTOTest {
	
	BenefitsDTO benefitsDTO;
	
	@Test
	@DisplayName("Testing if [BenefitsDTO] is loading or not")
	public void processingRequestIsLoadedOrNot() {
		
		benefitsDTO = new BenefitsDTO();
		
		assertNotNull(benefitsDTO);
		
	}
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	@DisplayName("Checking whether [BenefitsDTO] is runnin or not")
	public void testBenefitsDTO() {
		benefitsDTO = new BenefitsDTO();
		Set<Benefits> benefits = new HashSet<>();
		benefits.add(new Benefits("B111","4 Adults and 2 children covered"));
		
		Iterator<Benefits> itr = benefits.iterator();
		Benefits resultSet = itr.next();
		assertEquals("B111",resultSet.getBenefitId());
		assertEquals("4 Adults and 2 children covered",resultSet.getBenefitName());	
	}

}
