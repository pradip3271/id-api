package com.comicrelief.identityService;

import java.util.Locale;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import fixture.PayLoads;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Helper;

public class BaseMethods {
	
	protected RequestSpecification requestSpec;
	protected ResponseSpecification resSpec;
	protected PayLoads pl;
	protected Faker faker;
	
	protected String fName;
	protected String lName;
	protected String password;
	protected String email;
	protected String parentEmail;
	protected String junior;
	protected String terms;
	
	@BeforeClass
	public void testSetUp() {
		
		pl = new PayLoads();
		
		Properties prop = Helper.loadPropertyFile("./src/test/resources/env.properties");
		
		RestAssured.baseURI = prop.getProperty("URI");
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addHeader("Content-Type", "application/json");
		requestSpec = builder.build();
		
	}
	
	// Generate fake test data
	public void generateNewFakerAccountData() {
		
		faker = new Faker(Locale.UK);
		
		fName = faker.name().firstName();
		lName = faker.name().lastName();
		password = faker.internet().password(8,12);
		email = faker.internet().emailAddress();
		parentEmail = faker.internet().emailAddress();
		junior = "false";
		terms = "true";
		
	}

}
