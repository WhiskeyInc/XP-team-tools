package server.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * a class that builds the requests sent to the server
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
	
	public static String[] parseChatRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		String[] chatVet = new String[2]; 
		chatVet[0] = (String) json.get(TEAM_NAME);
		chatVet[1] = (String) json.get(MESSAGE);
		return chatVet;
	}
	
	public static String[] parseTimerRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		String[] timerVet = new String[3]; 
		timerVet[0] = (String) json.get(TEAM_NAME);
		timerVet[1] = json.get(MINUTES).toString();
		timerVet[2] = json.get(SECONDS).toString();
		return timerVet;
	}
	
	public static int getRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return Integer.parseInt((String)json.get(REQ));
	}

}
