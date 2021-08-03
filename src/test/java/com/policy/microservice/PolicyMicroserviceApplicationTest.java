package com.policy.microservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PolicyMicroserviceApplicationTest {

	PolicyMicroserviceApplication policyMicroserviceApplication;
	
	@Test
	public void testMain() {
		policyMicroserviceApplication = new PolicyMicroserviceApplication();
		assertThat(policyMicroserviceApplication).isNotNull();
	}

}
