package com.comicrelief.identityService;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import utils.RestServices;

public class CreateAccountTest extends BaseTest {

	// Test empty data supplied and verify response
	@Test
	public void createAccountNoData() {

		String payload = "";

		Response res = RestServices.createAccount(reqSpec, payload);

		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("junior")).and()
				.body("errors[0].message", equalTo("This value should not be blank.")).and()
				.body("errors[1].field", equalTo("password")).and()
				.body("errors[1].message", equalTo("This value should not be blank.")).and()
				.body("errors[2].field", equalTo("emailAddress")).and()
				.body("errors[2].message", equalTo("This value should not be blank."));

	}

	// Create Under16 account if there is valid data in the request
	@Test
	public void createU16Account() {

		String payload = pl.getU16AcctPayLoad();

		Response res = RestServices.createAccount(reqSpec, payload);

		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
	}

	// Create Over16 account if there is valid data in the request
	@Test
	public void createO16Account() {

		String payload = pl.getO16AcctPayLoad();

		Response res = RestServices.createAccount(reqSpec, payload);

		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
	}

	// POST to create account with existing email id and expect status code 400
	@Test
	public void createAccountWithExistingEmail() {

		// Create new O16 account
		String payload = pl.getO16AcctPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Request with existing email id
		res = RestServices.createAccount(reqSpec, payload);

		// Verify response code error message
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("email_address")).and()
				.body("errors[0].message", equalTo("Email address already exists"));

	}

	// POST to create account with empty password and expect status code 400
	@Test
	public void createAccountWithEmptyPassword() {

		// Create account
		String payload = pl.getO16AcctEmptyPasswordPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("password")).and()
				.body("errors[0].message", equalTo("This value should not be blank."));
	}

	// POST to create account with empty email id and expect status code 400
	@Test
	public void createAccountWithEmptyEmail() {

		// Create account
		String payload = pl.getO16AcctEmptyEmailPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[1].field", equalTo("contact_preferences[0].email_address")).and()
				.body("errors[1].message", equalTo("This value should not be blank."));

	}

	// POST to create account with empty last name and expect 200 but pending
	@Test
	public void createAccountWithEmptyLastName() {

		// Create account
		String payload = pl.getO16AcctEmptyLastNamePayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));

	}

	// POST to create account with empty first name and expect 200 but pending
	@Test
	public void createAccountWithEmptyFirstName() {

		// Create account
		String payload = pl.getO16AcctEmptyFirstNamePayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));

	}

	// POST to create account with empty terms and expect 400
	@Test
	public void createAccountWithEmptyTerms() {

		// Create account
		String payload = pl.getO16AcctEmptyTermsPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("terms")).and()
				.body("errors[0].message", equalTo("This value should not be blank."));

	}

	// POST to create account with terms set to false and expect 400
	@Test
	public void createAccountWithFalseTerms() {

		// Create account
		String payload = pl.getO16AcctFalseTermsPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[1].field", equalTo("terms")).and()
				.body("errors[1].message", equalTo("This value should be true."));

	}

	// POST request should fail if there is a password less than 8 characters
	@Test
	public void createAccountWithInvalidPassword() {

		// Create account
		String payload = pl.getO16AcctInvalidPasswordPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("password")).and()
				.body("errors[0].message", equalTo("Please create a password that is 8 characters or more"));

	}

	// POST request should fail if there is an invalid email format
	@Test
	public void createAccountWithInvalidEmail() {

		// Create account
		String payload = pl.getO16AcctInvalidEmailPayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);

		// Verify response
		res.then().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("emailAddress")).and()
				.body("errors[0].message", equalTo("This value is not a valid email address."));

	}

	// Pending user should be cancelled after I create a complete user with the same
	// email address
	@Test
	public void createAccountForPendingUser() {

		// Create account with empty first name
		String payload = pl.getO16AcctEmptyFirstNamePayLoad();
		Response res = RestServices.createAccount(reqSpec, payload);
		
		System.out.println(res.asString());
		
		// Verify status pending
		res.then().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));
		
		// Retrieve email address from response
		JsonPath jp = new JsonPath(res.asString());
		String email = jp.get("email_address");
		String password = "frostuser";
		
		// Create payload with same email address and password
		pl.setEmailAndPasswordPayload(email, password);
		payload = pl.getEmailAndPasswordPayload();
		
		System.out.println(payload);
		
		// User Authenticate and verify HTTP status code 200
		res = RestServices.userAuthenticate(reqSpec, payload);
//		res.then().statusCode(200);
		
		
	}

}
