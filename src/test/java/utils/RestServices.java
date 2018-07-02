package utils;

import java.util.Properties;

import fixture.PayLoads;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestServices {
	
	static Properties prop;
	
	static {
		
		prop = Helper.loadPropertyFile("./src/test/resources/endpoints.properties");
	}
	
	// Create new account
	public static Response postAccount(RequestSpecification reqSpec, String payload) {
				
		return given().spec(reqSpec).body(payload).when().post(prop.getProperty("CREATE-ACCT"));
	}
	
	// Authenticate user
	public static Response postAuthenticate(RequestSpecification reqSpec, String payload) {

		return given().spec(reqSpec).body(payload).when().post(prop.getProperty("AUTHENTICATE"));
	}
	
	// Check email exists or not
	public static Response getEmail(RequestSpecification reqSpec, String emailAddress) {
		
		return given().spec(reqSpec).pathParam("email-id", emailAddress).when().get(prop.getProperty("EMAIL-ID"));
	}
	
	// Get user data with user id and token
	public static Response getUser(RequestSpecification reqSpec, String token, int userId) {

		return given().spec(reqSpec).header("Authorization",token).pathParam("user-id", userId).when().get( prop.getProperty("USER-ID"));
	}
	
	// Generate JWS token using existing email and password
	public static String generateJWSToken(RequestSpecification reqSpec, String email, String password) {
		
		PayLoads pl = new PayLoads();
		pl.setEmailAndPasswordPayload(email, password);
		String payload = pl.getEmailAndPasswordPayload();
		
		Response res = given().spec(reqSpec).body(payload).when().post(prop.getProperty("AUTHENTICATE"));
		JsonPath jp = new JsonPath(res.asString());
		String payl = jp.get("payload");
		String protect = jp.get("protected");
		String signature = jp.get("signature");
		String jws_token = "Bearer " + protect + "." + payl + "." + signature;
		return jws_token;
	}

}
