package com.comicrelief.identityService;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RestServices;

public class UserDataTest extends BaseTest {
	
	// Fetch user data with valid token
	@Test
	public void userDataWithValidToken() {
		
		generateNewFakerAccountData();
		
		// Create O16 new account
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(reqSpec, payload);
		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
		
		// Extract user id and email address from response
		JsonPath jp = new JsonPath(res.asString());
		int userId = jp.get("id");
		String email = jp.get("email_address");
		
		// Create payload for given email address and password
		pl.setEmailAndPasswordPayload(email, password);
		payload = pl.getEmailAndPasswordPayload();
		
		// Authenticate user and retrieve token from response
		String token = RestServices.postAuthenticateAndRetrieveToken(reqSpec, payload);

		// Get user data and verify status code, id and email address
		res = RestServices.getUser(reqSpec, token, userId);
		res.then().statusCode(200).and().
		body("id", equalTo(userId)).and(). 
		body("email_address", equalTo(email));
		
		
	}

}
