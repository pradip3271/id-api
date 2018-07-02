package com.comicrelief.identityService;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RestServices;

public class EmailExistsTest extends BaseMethods {
	
	// Check email exists without campaign
	@Test
	public void checkEmailExist() {
		
		generateNewFakerAccountData();
		
		// Create new O16 account
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);
		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
		
		// Retrieve email address from response
		JsonPath jp = new JsonPath(res.asString());
		String emailAddress = jp.getString("email_address");
		System.out.println(emailAddress);
		
		// Check if email address exists
		res = RestServices.getEmail(requestSpec, emailAddress);
		res.then().statusCode(200);
		
		//Check if new email address not exists and status code 404
		res = RestServices.getEmail(requestSpec, "test123456@testmail.com");
		res.then().statusCode(404);
	}

}
