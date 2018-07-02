package com.comicrelief.identityService;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import utils.RestServices;

public class CreateAccountTest extends BaseMethods {

	// Create account with empty data supplied and verify response
	@Test
	public void createAccountNoData() {
		
		// Empty payload
		String payload = ""; 

		Response res = RestServices.postAccount(requestSpec, payload);

		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
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
		
		generateNewFakerAccountData();
		
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior = "true", terms);

		Response res = RestServices.postAccount(requestSpec, payload);
		
		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
	}

	// Create Over16 account if there is valid data in the request
	@Test
	public void createO16Account() {
		
		generateNewFakerAccountData();

		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms);

		Response res = RestServices.postAccount(requestSpec, payload);

		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
	}

	// POST to create account with existing email id and expect status code 400
	@Test
	public void createAccountWithExistingEmailAddress() {
		
		generateNewFakerAccountData();
		
		// Create new O16 account
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);
		res.then().log().ifError().statusCode(200).and().contentType("application/json");
		
		// Extract email id from response
		JsonPath jp = new JsonPath(res.asString());
		email = jp.get("email_address");
		
		// Create Account with above email id
		payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms);
		res = RestServices.postAccount(requestSpec, payload);
		
		// Verify response code error message
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("email_address")).and()
				.body("errors[0].message", equalTo("Email address already exists"));

	}

	// POST to create account with empty password and expect status code 400
	@Test
	public void createAccountWithEmptyPassword() {
		
		generateNewFakerAccountData();

		// Create new O16 account with empty password
		String payload = pl.getNewAcctPayLoad(fName, lName, password = "", email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("password")).and()
				.body("errors[0].message", equalTo("This value should not be blank."));
	}

	// POST to create account with empty email id and expect status code 400
	@Test
	public void createAccountWithEmptyEmail() {
		
		generateNewFakerAccountData();

		// Create new O16 account with empty email id
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email = "", parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[1].field", equalTo("contact_preferences[0].email_address")).and()
				.body("errors[1].message", equalTo("This value should not be blank."));

	}

	// POST to create account with empty last name and expect 200 but pending
	@Test
	public void createAccountWithEmptyLastName() {
		
		generateNewFakerAccountData();
		
		// Create new O16 account with empty last name
		String payload = pl.getNewAcctPayLoad(fName, lName = "", password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));

	}

	// POST to create account with empty first name and expect 200 but pending
	@Test
	public void createAccountWithEmptyFirstName() {

		generateNewFakerAccountData();
		
		// Create new O16 account with empty first name
		String payload = pl.getNewAcctPayLoad(fName = "", lName, password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));

	}

	// POST to create account with empty terms and expect 400
	@Test
	public void createAccountWithEmptyTerms() {
		
		generateNewFakerAccountData();

		// Create new O16 account with empty terms
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms = null);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("terms")).and()
				.body("errors[0].message", equalTo("This value should not be blank."));

	}

	// POST to create account with terms set to false and expect 400
	@Test
	public void createAccountWithFalseTerms() {
		
		generateNewFakerAccountData();

		// Create new O16 account with terms set to false
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email, parentEmail, junior, terms = "false");
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[1].field", equalTo("terms")).and()
				.body("errors[1].message", equalTo("This value should be true."));

	}

	// POST request should fail if there is a password less than 8 characters
	@Test
	public void createAccountWithInvalidPassword() {
		
		generateNewFakerAccountData();

		// Create new O16 account with password less than 8 characters
		String payload = pl.getNewAcctPayLoad(fName, lName, password = "test12", email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);

		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("password")).and()
				.body("errors[0].message", equalTo("Please create a password that is 8 characters or more"));

	}

	// POST request should fail if there is an invalid email format
	@Test
	public void createAccountWithInvalidEmail() {
		
		generateNewFakerAccountData();

		// Create new O16 account with invalid email id
		String payload = pl.getNewAcctPayLoad(fName, lName, password, email = "@gmail.com", parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);
		
		// Verify response
		res.then().log().ifError().statusCode(400).and().header("Content-Type", equalTo("application/json")).and()
				.body("errors[0].field", equalTo("emailAddress")).and()
				.body("errors[0].message", equalTo("This value is not a valid email address."));

	}

	// Pending user should be cancelled after I create a complete user with the same email address
	@Test
	public void createAccountForPendingUser() {
		
		generateNewFakerAccountData();
		
		// Create partial O16 account with empty first name
		String payload = pl.getNewAcctPayLoad(fName = "", lName, password, email, parentEmail, junior, terms);
		Response res = RestServices.postAccount(requestSpec, payload);
		
		// Verify status pending
		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("pending"));
		
		// Retrieve pending email address and password from response
		JsonPath jp = new JsonPath(res.asString());
		email = jp.get("email_address");
		
		// Create payload with above email address, password and set status pending
		pl.setEmailAndPasswordStatusPendingPayload(email, password);
		payload = pl.getEmailAndPasswordStatusPendingPayload();
		
		// Authenticate user and verify HTTP status code 200
		res = RestServices.postAuthenticate(requestSpec, payload);
		res.then().statusCode(200);
		
		// Next I create a full account with the same email address, which cancels my previous partial account
		payload = pl.getNewAcctPayLoad(fName = "Test first name", lName, password, email, parentEmail, junior, terms);
		res = RestServices.postAccount(requestSpec, payload);
		res.then().log().ifError().statusCode(200).and().header("Content-Type", equalTo("application/json")).and().body("status",
				equalTo("complete"));
		
	}

}
