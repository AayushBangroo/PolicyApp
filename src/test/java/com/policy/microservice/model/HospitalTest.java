package com.policy.microservice.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HospitalTest {
	

	Hospital hospitalObj = new Hospital();

	@Test
	@DisplayName("Checking if [Hospital] is loading or not.")
	public void processingRequestIsLoadedOrNot() {
	      assertNotNull(hospitalObj);
	}

	@Test
	public void testGetHospitalId() {
		String hid = "H11";
		Hospital h = new Hospital();
		h.setHospitalId(hid);
		
		assertEquals(hid,h.getHospitalId());
	}

	@Test
	public void testGetName() {
		String hname = "H11";
		Hospital h = new Hospital();
		h.setName(hname);
		assertEquals(hname,h.getName());
	}

	@Test
	public void testGetLocation() {
		String hloc = "H11";
		Hospital h = new Hospital();
		h.setLocation(hloc);
		assertEquals(hloc,h.getLocation());
		
	}

	@DisplayName("Checking if Hospital class is responding correctly or not.")
	@Test
	public void testHospital1(){
	  	hospitalObj = new Hospital("H111","Apollo","Delhi");
	    	
	 	assertEquals("H111",hospitalObj.getHospitalId());
	  	assertEquals("Apollo",hospitalObj.getName());
	  	assertEquals("Delhi",hospitalObj.getLocation());
	}
	
	@Test
	public void testHospital2(){
	  	hospitalObj = new Hospital();
	    	
	  	hospitalObj.setHospitalId("H11");
	  	hospitalObj.setName("Fortis");
	  	hospitalObj.setLocation("Mumbai");
	  	
	 	assertEquals("H11",hospitalObj.getHospitalId());
	  	assertEquals("Fortis",hospitalObj.getName());
	  	assertEquals("Mumbai",hospitalObj.getLocation());
	}

}
