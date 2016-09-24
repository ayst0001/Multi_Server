//
// functions of server-client interaction
package main;

import org.json.simple.JSONObject;

public class Server {

	public static JSONObject respond(String clientMsg) {
		// Marshal the incoming string into JSON
		JSONObject jsonMsg = Tools.marshal(clientMsg);
		
		if (isNewIdentity(jsonMsg)){
			//Extract the identity
			String identity = (String)jsonMsg.get("identity");
			if (newIdentityLegal(identity)){
				System.out.println("checking with other servers to set up your ID");
				if (allServersAgreed()){
					System.out.println("Request approved");
					return newIDApproval(true);
				}
				else{
					return newIDApproval(false);
				}
			}
			else{
				System.out.println("The ID supplied is not legal");
				System.out.println("Please ensure it starts with a letter"
						+ " and is between 3 and 16 characters long");
			}
		}
		else if (isCommand(clientMsg)){
			return null;
			//Code to handle each command
		}
		else {
			return null;
			// code to handle regular message
		};
		return null;
		
	}

	private static boolean allServersAgreed() {
		// TODO Auto-generated method stub
		return true;
	}

	// put together a message for approval
	@SuppressWarnings("unchecked")
	private static JSONObject newIDApproval(Boolean approve) {
		JSONObject newIDApproval = new JSONObject();
		newIDApproval.put("type","newidentity");
		if (approve){
			newIDApproval.put("approved","true");
		}
		else{
			newIDApproval.put("approved","false");
		}
		return newIDApproval;
	}

	// return true id the message received is a new identity request
	private static boolean isNewIdentity(JSONObject jsonMsg) {
		boolean isNewIdentity = false;
		String type = (String)jsonMsg.get("type");
		if (type.equals("newidentity")){
			System.out.println("new identity request recieved");
			isNewIdentity = true;
		}
		return isNewIdentity;
	}
	
	// new ID has to start with letter and between 3-16 char long
	private static boolean newIdentityLegal(String identity) {
		boolean newIdentityLegal = false;
		if (identity.length() >=3 && identity.length()<=16){
			char c = identity.charAt(0);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
				newIdentityLegal = true;
			}
		}
		return newIdentityLegal;
	}


	private static boolean isCommand(String clientMsg) {
		//return true if start with #
		//return false by default
		return false;
	}



}
