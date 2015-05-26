package protocol;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.model.services.AuthService;
import client.model.ClientDetails;

/**
 * This class creates different requests that will be sent to the server using JSONObject, i.e., 
 * collections of name/value pairs. All the methods of this class return a String representation 
 * of the instance of JSONObject
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
	public static final String TEAMS = "12";
	public static final String MAKE_TEAMS_LIST = "13";
	public static final String MACRO_EVENT = "14";
	public static final String AUTH_REQ = "15";

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
	public static final String NICKNAME = "nickname";
	public static final String AUTH = "auth";
	public static final String CONNECTED = "connected";
	public static final String NOT_CONNECTED = "notconnected";

	public static final String EVENT_ACTION = "action";
	public static final String EVENT_NAME = "event_name";
	public static final String PARTICIPANTS = "participants";
	public static final String EVENT_YEAR = "year";
	public static final String EVENT_MONTH = "month";
	public static final String EVENT_DAY = "day";
	public static final String EVENT_HOUR = "hour";
	public static final String EVENT_MINUTE = "min";

	
	public static final String ADD_EVENT = "addEvent"; 
	public static final String ADD_AUTOMATIC_EVENT = "addAutomaticEvent"; 
	public static final String USER = "user";
	
	public static final String MACRO_EVENT_REQUEST_ACTION = "macroEventsRequest";
	public static final String MACRO_EVENT_USER = "user";
	public static final String MACRO_EVENT_RESPONSE_ACTION = "macro_event_response";
	public static final String MACRO_EVENT_IDS = "ids";
	public static final String MACRO_EVENT_NAMES = "names";
	public static final String MACRO_EVENT_ID = "id";
	

	@SuppressWarnings("unchecked")
	/**
	 * creates a new chat request for the server
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
			json.put(ATTENDANT + i, detArray);
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

	/**
	 * Mirror image of the connectToServerRequest method, but it requests a
	 * different type of service, that is Authentication Service in
	 * {@link AuthService} on the Server.
	 * It substantially probes for Login authentication in {@link AuthService}.
	 * 
	 * @param details
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static String authRequest(ClientDetails details) {

		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		detArray.add(details.getNickname());
		detArray.add(details.getTeamName());
		detArray.add(details.getPwd());
		json.put(REQ, AUTH_REQ);
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

	/**
	 * creates a chat request for the server
	 * @param message
	 * @param chatId
	 * @return
	 */
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
	public static String timerRequest(String chatIndex, int minutes, int seconds, ArrayList<String> participants, String id) {

		JSONObject json = new JSONObject();
		json.put(REQ, TIMER);
		json.put(CHAT_INDEX, chatIndex);
		json.put(MINUTES, minutes);
		json.put(SECONDS, seconds);
		json.put(MACRO_EVENT_ID, id);

		JSONArray array = new JSONArray();
		if (participants != null) {
			array.addAll(participants);
		}
		json.put(PARTICIPANTS, array);

		return json.toString();
	}

	/**
	 * creates a new team request for the server
	 * @param teamName
	 * @param teamCreatorNick
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String newTeamRequest(String teamName, String teamCreatorNick) {
		JSONObject json = new JSONObject();
		json.put(REQ, NEW_TEAM);
		json.put(ATTENDANT, teamCreatorNick);
		json.put(TEAM_NAME, teamName);
		return json.toString();
	}

	/**
	 * creates a request of adding a teamMember
	 * @param details
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String addTeamMembRequest(ClientDetails details) {

		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		detArray.add(details.getNickname());
		detArray.add(details.getTeamName());
		json.put(REQ, ADD_TEAM_MEMB);
		json.put(ATTENDANT, detArray);
		return json.toString();
	}

	
	/**
	 * creates a request of a disconnection
	 * @param det
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String disconnectRequest(ClientDetails det) {
		JSONObject json = new JSONObject();
		json.put(REQ, DISCONNECT);
		JSONArray detArray = new JSONArray();
		detArray.add(det.getNickname());
		detArray.add(det.getTeamName());
		json.put(ATTENDANT, detArray);
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public static String disconnectRequestByServer(boolean b) {
		JSONObject json = new JSONObject();
		if (b)
			json.put(AUTH, CONNECTED);
		else
			json.put(AUTH, NOT_CONNECTED);

		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public static String makeTeamMembs(String[] nicks) {
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
	public static String teamMembsRequest(String nickname, String teamName) {
		JSONObject json = new JSONObject();
		json.put(REQ, TEAM_MEMBS);
		json.put(NICKNAME, nickname);
		json.put(TEAM_NAME, teamName);
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	/**
	 * creates an event json of a manual event to communicate it to the second server
	 * @param eventName
	 * @param user
	 * @param participants
	 * @param date
	 * @return
	 */
	public static String manualEventRequest(String user, String eventName,
			ArrayList<String> participants, Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		JSONObject json = new JSONObject();

		json.put(REQ, EVENT);

		json.put(USER, user);
		json.put(EVENT_ACTION, ADD_EVENT);
		json.put(EVENT_NAME, eventName);
		json.put(EVENT_YEAR, String.valueOf(cal.get(Calendar.YEAR)-1900));
		json.put(EVENT_MONTH, String.valueOf(cal.get(Calendar.MONTH)));
		json.put(EVENT_DAY, String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		json.put(EVENT_HOUR, String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
		json.put(EVENT_MINUTE, String.valueOf(cal.get(Calendar.MINUTE)));

		if (participants != null) {
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
	 * @param id
	 * @return
	 */
	public static String automaticEventRequest(String user, String eventName,
			ArrayList<String> participants, String id) {

		JSONObject json = new JSONObject();

		json.put(REQ, EVENT);
		json.put(USER, user);
		json.put(EVENT_ACTION, ADD_AUTOMATIC_EVENT);
		json.put(EVENT_NAME, eventName);
		json.put(MACRO_EVENT_ID, id);

		if (participants != null) {
			JSONArray array = new JSONArray();
			array.addAll(participants);
			json.put(PARTICIPANTS, array);
		}	
		
		return json.toString();
	}

	/**
	 * creates a teamsList request for the server
	 * @param nickname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String teamsListRequest(String nickname) {
		JSONObject json = new JSONObject();
		json.put(REQ, TEAMS);
		json.put(NICKNAME, nickname);

		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public static String requestMacroEventsList(String user) {

		JSONObject json = new JSONObject();

		json.put(REQ, MACRO_EVENT);
		json.put(USER, user);
		json.put(EVENT_ACTION, MACRO_EVENT_REQUEST_ACTION);
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static String makeListOfTeams(String[] teamsNames) {

		JSONObject json = new JSONObject();
		JSONArray detArray = new JSONArray();
		for (int i = 0; i < teamsNames.length; i++) {
			detArray.add(teamsNames[i]);
		}
		json.put(REQ, MAKE_TEAMS_LIST);
		json.put(ATTENDANT, detArray);
		return json.toString();
	}
}
