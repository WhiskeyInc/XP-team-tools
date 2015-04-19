package client.model;

import org.json.simple.JSONObject;

/**
 * this class handles the two possible requests which will be sent to the server
 * (1 = chat, 2 = timer)
 * 
 * @author alberto
 *
 */
public class JsonMaker {
	

	public static final String CHAT = "1";
	public static final String TIMER = "2";
	public static final String REQ = "request";
	public static final String TEAM_NAME = "team name";
	public static final String MESSAGE = "message";
	public static final String MINUTES = "minutes";
	public static final String SECONDS = "seconds";
	
	
	
	
	@SuppressWarnings("unchecked")
	
	/**
	 * creates a chat request for the server
	 * @param teamName
	 * @param message
	 */
	public static String chatRequest(String teamName, String message) {
		
		JSONObject json = new JSONObject();
		json.put(REQ, CHAT);
		json.put(TEAM_NAME, teamName);
		json.put(MESSAGE, message);
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	
	/**
	 * creates a timer request for the server
	 * @param teamName
	 * @param minutes
	 * @param seconds
	 */
	public static String timerRequest(String teamName, int minutes, int seconds) {
		
		JSONObject json = new JSONObject();
		json.put(REQ, TIMER);
		json.put(TEAM_NAME, teamName);
		json.put(MINUTES, minutes);
		json.put(SECONDS, seconds);
		return json.toString();
	}

}
