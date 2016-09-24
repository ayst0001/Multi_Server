package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Tools {

	public static JSONObject marshal(String clientMsg) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonMsg = (JSONObject)parser.parse(clientMsg);
			return jsonMsg;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
