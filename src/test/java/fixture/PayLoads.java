package fixture;

import java.util.Locale;

import com.github.javafaker.Faker;

public class PayLoads {
	
	private String emailAndPasswordPayload;

	Faker faker = new Faker(Locale.UK);

	public String getU16AcctPayLoad() {
		
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";
		String parentEmail = faker.internet().emailAddress();

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"parent_email_address\": \"" + parentEmail + "\",\n"
				+ "  \"junior\": true,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": true\n" + "}\n" + "";
	}

	public String getO16AcctPayLoad() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": true\n" + "}\n" + "";
	}
	
	public String getO16AcctEmptyPasswordPayLoad() {
		
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": true\n" + "}\n" + "";
	}
	
public String getO16AcctEmptyEmailPayLoad() {
		
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = "";
		String password = "frostuser";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": true\n" + "}\n" + "";
	}

	public String getO16AcctEmptyLastNamePayLoad() {

		String firstName = faker.name().firstName();
		String lastName = "";
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \"" + firstName + "\",\n"
				+ "  \"last_name\": \"" + lastName + "\",\n" + "  \"password\": \"" + password + "\",\n"
				+ "  \"email_address\": \"" + emailAddress + "\",\n" + "  \"junior\": false,\n"
				+ "  \"contact_preferences\": [\n" + "    {\n" + "      \"post_preference\": false,\n"
				+ "      \"email_preference\": true\n" + "    }\n" + "  ],\n" + "  \"terms\": true\n" + "}\n" + "";
	}

	public String getO16AcctEmptyFirstNamePayLoad() {

		String firstName = "";
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \"" + firstName + "\",\n"
				+ "  \"last_name\": \"" + lastName + "\",\n" + "  \"password\": \"" + password + "\",\n"
				+ "  \"email_address\": \"" + emailAddress + "\",\n" + "  \"junior\": false,\n"
				+ "  \"contact_preferences\": [\n" + "    {\n" + "      \"post_preference\": false,\n"
				+ "      \"email_preference\": true\n" + "    }\n" + "  ],\n" + "  \"terms\": true\n" + "}\n" + "";
	}
	
	public String getO16AcctEmptyTermsPayLoad() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";
		String terms = null;

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": "+terms+"\n" + "}\n" + "";
	}
	
	public String getO16AcctFalseTermsPayLoad() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frostuser";
		String terms = "false";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": "+terms+"\n" + "}\n" + "";
	}
	
	public String getO16AcctInvalidPasswordPayLoad() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = faker.internet().emailAddress();
		String password = "frost";
		String terms = "true";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": "+terms+"\n" + "}\n" + "";
	}
	
	public String getO16AcctInvalidEmailPayLoad() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String emailAddress = "testmail.com";
		String password = "frostuser";
		String terms = "true";

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ firstName + "\",\n" + "  \"last_name\": \"" + lastName + "\",\n"
				+ "  \"password\": \"" + password + "\",\n" + "  \"email_address\": \"" + emailAddress
				+ "\",\n" + "  \"junior\": false,\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": "+terms+"\n" + "}\n" + "";
	}
	
	public String getEmailAndPasswordPayload() {
		
		return emailAndPasswordPayload;
		
	}

	public void setEmailAndPasswordPayload(String email, String password) {
		
		emailAndPasswordPayload = "{\n" + 
				"  \"identifier\": {\n" + 
				"    \"type\": \"email\",\n" + 
				"    \"value\": \""+ email +"\"\n" + 
				"  },\n" + 
				"  \"password\": \""+ password +"\"\n" + 
				"}";
		
	}

}
