package com.policy.microservice.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PolicyTest {

	Policy policyObj = new Policy();
	
	 @Test
	 @DisplayName("Checking if [Policy] is loading or not.")
	 public void processingRequestIsLoadedOrNot() {
	      assertNotNull(policyObj);
	}
	
	@DisplayName("Checking if Policy class is responding correctly or not.")
    @Test
    public void testPolicy(){
    	
    	policyObj = new Policy("P111","Health Care",1200000.0,23000.0);
    	
    	assertEquals("P111",policyObj.getPolicyId());
    	assertEquals("Health Care",policyObj.getPolicyType());
    	assertEquals(23000.0,policyObj.getPremium(),0.1);
    	assertEquals(1200000.0,policyObj.getSumInsured(),0.1);
    }
	
	@Test
    public void testPolicy2(){
    	
		policyObj = new Policy();
		Set<Hospital> hospitals = new HashSet<>();
		hospitals.add(new Hospital("H11","Apollo","Chennai"));
		Set<Benefits> benefits = new HashSet<>();
		benefits.add(new Benefits("B101","Coverage for COVID"));
    	policyObj.setPolicyId("P1005");
    	policyObj.setPolicyType("Health Plus");
    	policyObj.setPremium(50000.0);
    	policyObj.setSumInsured(10000000.0);
    	policyObj.setHospitals(hospitals);
    	policyObj.setBenefits(benefits);
    	
    	assertEquals("P1005",policyObj.getPolicyId());
    	assertEquals("Health Plus",policyObj.getPolicyType());
    	assertEquals(50000.0,policyObj.getPremium(),0.1);
    	assertEquals(10000000.0,policyObj.getSumInsured(),0.1);
    	assertEquals(benefits,policyObj.getBenefits());
    	assertEquals(hospitals,policyObj.getHospitals());
    }

	
}
