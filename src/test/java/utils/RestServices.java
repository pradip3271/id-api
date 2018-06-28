package utils;

import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServices {
	
	static Properties prop;
	
	static {
		
		prop = Helper.loadPropertyFile("./src/test/resources/endpoints.properties");
	}

	public static Response postAccount(RequestSpecification reqSpec, String payload) {
		
		return reqSpec.body(payload).log().all().when().post(prop.getProperty("CREATE-ACCT"));
	}

	public static Response postAuthenticate(RequestSpecification reqSpec, String payload) {

		return reqSpec.body(payload).when().post(prop.getProperty("AUTHENTICATE"));
	}

	public static Response getEmail(RequestSpecification reqSpec, String emailAddress) {
		
		String emailEndpoint = prop.getProperty("EMAIL") + "/"+ emailAddress;
		return reqSpec.when().get(emailEndpoint);
	}

	public static Response getUser(RequestSpecification reqSpec, String token, int userId) {

		String userIdEndpoint = prop.getProperty("CREATE-ACCT") + "/"+ userId;
		return reqSpec.header("Authorization",token).when().get(userIdEndpoint);
	}

	public static String postAuthenticateAndRetrieveToken(RequestSpecification reqSpec, String payload) {
		
		Response res = reqSpec.body(payload).when().post(prop.getProperty("AUTHENTICATE"));
		JsonPath jp = new JsonPath(res.asString());
		String payl = jp.get("payload");
		String protect = jp.get("protected");
		String signature = jp.get("signature");
		String jwt_token = "Bearer " + protect + "." + payl + "." + signature;
		return jwt_token;
	}

}
