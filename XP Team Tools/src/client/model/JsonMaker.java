package client.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * this class handles the two possible requests which will be sent to the server
 * (1 = chat, 2 = timer)
 * it creates also jsons for the communication with the second server
 * 
 * @author Alberto,Nicola
 */
public class JsonMaker {
	
	public static final String CHAT = "1";
	public static final String TIMER = "2";
	public static final String REQ = "request";
	public static final String TEAM_NAME = "team name";
	public static final String MESSAGE = "message";
	public static final String MINUTES = "minutes";
	public static final String SECONDS = "seconds";
	
	public static final String EVENT_ACTION = "action";
	public static final String EVENT_NAME = "event_name";
	public static final String PARTICIPANTS = "participants";
	
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
	
	@SuppressWarnings("unchecked")
	/**
	 * creates an event json to communicate to the second server
	 * @param eventName
	 * @param participants
	 * @return
	 */
	public static String eventCommunication(String eventAction, String eventName, ArrayList<String> participants) {
		
		JSONObject json = new JSONObject();
		
		json.put(EVENT_ACTION, eventAction);
		json.put(EVENT_NAME, eventName);
				
		JSONArray array = new JSONArray();
		array.addAll(participants);
		json.put(PARTICIPANTS, array);
		
		return json.toString();
	}

}
