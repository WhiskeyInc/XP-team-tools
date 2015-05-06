package protocol;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import client.model.ClientDetails;

/**
 * 
 * 
 * @author alberto
 *
 */
public class JsonMaker {

	public static final String NEW_CHAT = "0";
	public static final String CHAT = "1";
	public static final String TIMER = "2";
	public static final String LIST_OF_CHAT = "3";
	public static final String NEW_TEAM = "4";
	public static final String ADD_TEAM_MEMB = "5";
	public static final String CHAT_INDEX = "6";
	public static final String CONNECT_SERVER = "7";
	public static final String DISCONNECT = "8";
	public static final String TEAM_MEMBS = "9";
	public static final String NEW_UI = "10";
	public static final String EVENT = "11";
	
	public static final String REQ = "request";
	public static final String TEAM_NAME = "team name";
	public static final String MESSAGE = "message";
	public static final String ATTENDANT = "attendant";
	public static final String INDEX = "index";
	public static final String CHAT_ID = "id";
	public static final String CHAT_IDS = "ids";
	public static final String TEAM_MEMB = "team memb";
	public static final String MINUTES = "minutes";
	public static final String SECONDS = "seconds";
	public static final String CONFIRM_STRING = "ok";
	
	public static final String EVENT_ACTION = "action";
	public static final String EVENT_NAME = "event_name";
	public static final String PARTICIPANTS = "participants";
	public static final String EVENT_DATE = "date";
	public static final String ADD_EVENT = "addEvent"; // La servlet riconosce solo questa!
	public static final String ADD_AUTOMATIC_EVENT = "addAutomaticEvent"; // Non ancora implementato!
	

	@SuppressWarnings("unchecked")
	/**
	 * creates a chat request for the server
	 * @param teamName
	 * @param message
	 */
	public static String newChatRequest(ClientDetails... details) {

		JSONObject json = new JSONObject();
		json.put(REQ, NEW_CHAT);
		JSONArray detArray;
		
		for (int i = 0; i < details.length; i++) {
			detArray = new JSONArray();
			detArray.add(details[i].getNickname());
			detArray.add(details[i].getTeamName());
			json.put(ATTENDANT+i, detArray);
		}
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Api for the first connection to the Server
	 * @param details
	 * @return
	 */
	public static String connectToServerRequest(ClientDetails details) {

		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		detArray.add(details.getNickname());
		detArray.add(details.getTeamName());
		detArray.add(details.getPwd());
		json.put(REQ, CONNECT_SERVER);
		json.put(ATTENDANT, detArray);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Protocol's Api to require to the server the index of the chat
	 * @param chatIndex
	 * @return
	 * 
	 */
	public static String chatIndexRequest(int chatIndex) {
		JSONObject json = new JSONObject();
		json.put(REQ, CHAT_INDEX);
		json.put(INDEX, String.valueOf(chatIndex));
		return json.toString();
	}


	@SuppressWarnings("unchecked")
	public static String chatRequest(String message, String chatId) {

		JSONObject json = new JSONObject();
		json.put(REQ, CHAT);
		json.put(CHAT_INDEX, chatId);
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
	public static String timerRequest(String chatIndex, int minutes, int seconds) {

		JSONObject json = new JSONObject();
		json.put(REQ, TIMER);
		json.put(CHAT_INDEX, chatIndex);
		json.put(MINUTES, minutes);
		json.put(SECONDS, seconds);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String newTeamRequest(String teamName, String teamCreatorNick) {
		JSONObject json = new JSONObject();
		json.put(REQ, NEW_TEAM);
		json.put(ATTENDANT, teamCreatorNick);
		json.put(TEAM_NAME, teamName);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String addTeamMemb(ClientDetails details) {

		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		detArray.add(details.getNickname());
		detArray.add(details.getTeamName());
		json.put(REQ, ADD_TEAM_MEMB);
		json.put(ATTENDANT, detArray);
		return json.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public static String disconnect(ClientDetails det) {
		JSONObject json = new JSONObject();
		json.put(REQ, DISCONNECT);
		JSONArray detArray = new JSONArray();
		detArray.add(det.getNickname());
		detArray.add(det.getTeamName());
		json.put(ATTENDANT, detArray);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String teamMembsRequest(String[] nicks) {
		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		for (String nick : nicks) {
			detArray.add(nick);
		}
		json.put(REQ, TEAM_MEMBS);
		json.put(ATTENDANT, detArray);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String createNewUiRequest(int chatIndex) {
		JSONObject json = new JSONObject();
		json.put(REQ, NEW_UI);
		json.put(INDEX, String.valueOf(chatIndex));
		return json.toString();
	}
	
	
	
	@SuppressWarnings("unchecked")
	/**
	 * creates an event json of a manual event to communicate it to the second server
	 * @param eventName
	 * @param participants
	 * @return
	 */
	public static String manualEventCommunication(String eventName, String date, ArrayList<String> participants) {
		
		JSONObject json = new JSONObject();
		
		json.put(REQ, EVENT);

		json.put(EVENT_ACTION, ADD_EVENT);
		json.put(EVENT_NAME, eventName);
		json.put(EVENT_DATE, date);
		
		if(participants != null){
			JSONArray array = new JSONArray();
			array.addAll(participants);
			json.put(PARTICIPANTS, array);
		}	
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * creates an event json of an automatic event to communicate it to the second server
	 * @param eventName
	 * @param participants
	 * @return
	 */
	public static String automaticEventCommunication(String eventName, ArrayList<String> participants) {
		
		JSONObject json = new JSONObject();
		
		json.put(REQ, EVENT);
		
		json.put(EVENT_ACTION, ADD_AUTOMATIC_EVENT);
		json.put(EVENT_NAME, eventName);
		
		if(participants != null){
			JSONArray array = new JSONArray();
			array.addAll(participants);
			json.put(PARTICIPANTS, array);
		}	
		
		return json.toString();
	}

}
