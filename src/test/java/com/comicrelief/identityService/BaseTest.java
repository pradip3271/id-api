package com.comicrelief.identityService;

import java.util.Properties;

import org.testng.annotations.BeforeClass;

import fixture.PayLoads;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.Helper;

public class BaseTest {
	
	protected RequestSpecification reqSpec;
	protected PayLoads pl;
	
	@BeforeClass
	public void testSetUp() {
		
		pl = new PayLoads();
		
		Properties prop = Helper.loadPropertyFile("./src/test/resources/env.properties");
		
		RestAssured.baseURI = prop.getProperty("URI");
		reqSpec = RestAssured.given();
		
	}

}
