package com.policy.microservice.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberPolicyServiceTest {
	
	
	MemberPolicyService memberPolicyService = new MemberPolicyService();


	@Test
    @DisplayName("Checking if [Member Policy Service] is loading or not.")
	public void policyServiceIsLoaded(){
        assertNotNull(memberPolicyService);
    }

}
