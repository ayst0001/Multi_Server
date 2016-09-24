package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {

	public static void respond(String clientMsg) {
		if (isNewIdentity(clientMsg)){
			if (newIdentityLegal(clientMsg)){
				System.out.println("responding client request");
				// 3~16 char, starts with letter
			}
			else{
				System.out.println("The ID supplied is not legal");
				System.out.println("Please ensure it starts with a letter"
						+ "and is between 3 and 16 characters long");
			}
			//talk to all other servers
			//Code to handle each request
		}
		else if (isCommand(clientMsg)){
			//Code to handle each command
		}
		else {
			// code to handle regular message
		};
		
	}

	private static boolean isNewIdentity(String clientMsg) {
		boolean isNewIdentity = false;
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonMsg = (JSONObject)parser.parse(clientMsg);
			String type = (String)jsonMsg.get("type");
			if (type.equals("newidentity")){
				System.out.println("new identity request recieved");
				isNewIdentity = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//return false by default
		return isNewIdentity;
	}
	
	private static boolean newIdentityLegal(String clientMsg) {
		// TODO Auto-generated method stub
		return true;
	}


	private static boolean isCommand(String clientMsg) {
		//return true if start with #
		//return false by default
		return false;
	}

}
