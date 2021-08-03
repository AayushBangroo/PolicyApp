package com.policy.microservice.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BenefitsTest {

	Benefits benefitsObj = new Benefits();

    @Test
    @DisplayName("Checking if [Benefits] is loading or not.")
    public void processingRequestIsLoadedOrNot() {
        assertNotNull(benefitsObj);
    }
    
    @DisplayName("Checking if Benefits class is responding correctly or not.")
    @Test
    public void testBenefits1(){
    	benefitsObj = new Benefits("B111","Covers upto 4 Adults and 2 children");
    	
    	assertEquals("B111",benefitsObj.getBenefitId());
    	assertEquals("Covers upto 4 Adults and 2 children",benefitsObj.getBenefitName());
    }
    
    @Test
    public void testBenefits2(){
    	benefitsObj = new Benefits();
    	
    	benefitsObj.setBenefitId("B20");
    	benefitsObj.setBenefitName("Ambulance charges covered");
    	
    	assertEquals("B20",benefitsObj.getBenefitId());
    	assertEquals("Ambulance charges covered",benefitsObj.getBenefitName());
    }
}
