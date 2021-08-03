package com.policy.microservice.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberPolicyTest {
	
	MemberPolicy memberPolicyObj = new MemberPolicy();
	
	@Test
    @DisplayName("Checking if [MemberPolicy] is loading or not.")
    public void processingRequestIsLoadedOrNot() {
        assertNotNull(memberPolicyObj);
    }

	@DisplayName("Checking if MemberPolicy class is responding correctly or not.")
    @Test
    public void testingBenefits(){
    	memberPolicyObj = new MemberPolicy("M111","P111",22,"10/08/2018","21/02/2001");
    	
    	assertEquals("M111",memberPolicyObj.getMemberId());
    	assertEquals("P111",memberPolicyObj.getPolicyId());
    	assertEquals(22,memberPolicyObj.getTenure());
    	assertEquals("10/08/2018",memberPolicyObj.getPremiumLastDate());
    	assertEquals("21/02/2001",memberPolicyObj.getSubscriptionDate());
    }
	
	@DisplayName("Checking if MemberPolicy class is responding correctly or not.")
    @Test
    public void testingBenefits1(){
		
    	memberPolicyObj = new MemberPolicy();
    	memberPolicyObj.setMemberId("MX1");
    	memberPolicyObj.setPolicyId("P1002");
    	memberPolicyObj.setPremiumLastDate("21/02/2000");
    	memberPolicyObj.setSubscriptionDate("10/01/2020");
    	memberPolicyObj.setTenure(2);
    	
    	assertEquals("MX1",memberPolicyObj.getMemberId());
    	assertEquals("P1002",memberPolicyObj.getPolicyId());
    	assertEquals(2,memberPolicyObj.getTenure());
    	assertEquals("21/02/2000",memberPolicyObj.getPremiumLastDate());
    	assertEquals("10/01/2020",memberPolicyObj.getSubscriptionDate());
    }

}
