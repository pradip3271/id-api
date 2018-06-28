package fixture;

public class PayLoads {
	
	private String emailAndPasswordPayload;
	private String emailAndPasswordStatusPendingPayload;

	public String getNewAcctPayLoad(String fName, String lName, String pw, String email, String pEmail, String junior, String terms) {

		return "{\n" + "  \"title\": {\n" + "    \"id\": 1\n" + "  },\n" + "  \"first_name\": \""
				+ fName + "\",\n" + "  \"last_name\": \"" + lName + "\",\n"
				+ "  \"password\": \"" + pw + "\",\n" + "  \"email_address\": \"" + email
				+ "\",\n" + "  \"parent_email_address\": \"" + pEmail + "\",\n"
				+ "  \"junior\": "+ junior +",\n" + "  \"contact_preferences\": [\n" + "    {\n"
				+ "      \"post_preference\": false,\n" + "      \"email_preference\": true\n" + "    }\n" + "  ],\n"
				+ "  \"terms\": "+ terms +"\n" + "}\n" + "";
	}

	public String getEmailAndPasswordPayload() {
		
		return emailAndPasswordPayload;
		
	}
	
	public String getEmailAndPasswordStatusPendingPayload() {
		
		return emailAndPasswordStatusPendingPayload;
		
	}

	public void setEmailAndPasswordPayload(String email, String password) {
		
		emailAndPasswordPayload = "{\n" + 
				"  \"identifier\": {\n" + 
				"    \"type\": \"email\",\n" + 
				"    \"value\": \""+ email +"\"\n" + 
				"  },\n" + 
				"  \"password\": \""+ password +"\"\n" + 
				"}\n" + 
				"";
		
	}

	public void setEmailAndPasswordStatusPendingPayload(String email, String password) {
		
		emailAndPasswordStatusPendingPayload = "{\n" + 
				"  \"identifier\": {\n" + 
				"    \"type\": \"email\",\n" + 
				"    \"value\": \""+ email +"\"\n" + 
				"  },\n" + 
				"  \"password\": \""+ password +"\",\n" + 
				"  \"status\": [\"pending\"]\n" + 
				"  \n" + 
				"}";
		
	}

}
