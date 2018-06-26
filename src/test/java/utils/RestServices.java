package utils;

import java.util.Properties;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServices {
	
	static Properties prop;
	
	static {
		
		prop = Helper.loadPropertyFile("./src/test/resources/endpoints.properties");
	}

	public static Response createAccount(RequestSpecification reqSpec, String payload) {
		
		return reqSpec.body(payload).when().post(prop.getProperty("CREATE-ACCT"));
	}

	public static Response userAuthenticate(RequestSpecification reqSpec, String payload) {
		
		return reqSpec.body(payload).when().post(prop.getProperty("AUTHENTICATE"));
	}

}
