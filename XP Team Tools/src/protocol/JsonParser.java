package protocol;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import client.model.ClientDetails;

/**
 * It parse the requests sent to the server
 * 
 * @author alberto
 *
 */
public class JsonParser {

	public static final int CHAT = 1;
	public static final int TIMER = 2;
	public static final String REQ = "request";
	public static final String TEAM_NAME = "team name";
	public static final String MESSAGE = "message";
	public static final String MINUTES = "minutes";
	public static final String SECONDS = "seconds";

	public static ClientDetails[] parseNewChatRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		int size = json.size()-1;
		ClientDetails[] vet = new ClientDetails[size];
		for (int i = 0; i < size; i++) {
			JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT+i);
			vet[i] = new ClientDetails((String)idList.get(0), (String)idList.get(1));
		}
		
		return vet;
	}
	
	public static ClientDetails parseConnectToServerRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT);
		ClientDetails det = new ClientDetails((String)idList.get(0), (String)idList.get(1));
		
		return det;
	}
	
	public static int parseChatIndexRequest(String s) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt((String)json.get(JsonMaker.INDEX));
	}
	
	public static boolean parseConfirm(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return json.get(JsonMaker.CONFIRM_STRING).equals(JsonMaker.CONFIRM_STRING);
	}
	
	
	/**
	 * It builds a chat's request
	 * 
	 * @param s
	 *            the string to be parsed
	 * @return an array containing TeamName and the Message
	 * @throws ParseException
	 */
	public static String[] parseChatRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		String[] newChatVet = new String[2];
		newChatVet[0] = (String) json.get(JsonMaker.CHAT_INDEX);
		newChatVet[1] = (String) json.get(JsonMaker.MESSAGE);
		return newChatVet;
	}

	

	/**
	 * It builds a timer's request
	 * 
	 * @param s
	 *            the string to be parsed
	 * @return an array containing TeamName, minutes and seconds
	 * @throws ParseException
	 */
	public static String[] parseTimerRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		String[] timerVet = new String[3];
		timerVet[0] = (String) json.get(JsonMaker.CHAT_INDEX);
		timerVet[1] = json.get(MINUTES).toString();
		timerVet[2] = json.get(SECONDS).toString();
		return timerVet;
	}
	
	
	public static String[] parseNewTeamRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		String[] vett = new String[2];
		vett[0] = json.get(JsonMaker.TEAM_NAME).toString();
		vett[1] = json.get(JsonMaker.ATTENDANT).toString();
		return vett;
	}
	
	public static ClientDetails parseAddTeamMembRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT);
		ClientDetails det = new ClientDetails((String)idList.get(0), (String)idList.get(1));
		
		return det;
	}
	
	public static String[] parseTeamMembsRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT);
		int size = idList.size();
		String[] membs = new String[size];
		for (int i = 0; i < size; i++) {
			membs[i] = (String) idList.get(i);
		}
		return membs;
	}
	
	public static int getRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return Integer.parseInt((String) json.get(REQ));
	}

}
