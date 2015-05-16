package protocol;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import client.model.ClientDetails;
import client.model.MacroEvents;

/**
 * This class parses the requests sent to the server
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

	public static final String EVENT_ACTION = "action";
	public static final String EVENT_NAME = "event_name";
	public static final String PARTICIPANTS = "participants";


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
		ClientDetails det = new ClientDetails((String)idList.get(0), (String)idList.get(1), (String) idList.get(2));
		
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
	
	public static ClientDetails parseDisconnectRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT);
		ClientDetails det = new ClientDetails((String)idList.get(0), (String)idList.get(1));
		return det;
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
		
		String[] timerVet;
		JSONArray participants = (JSONArray) json.get(PARTICIPANTS);
		if(participants!=null){
			timerVet = new String[ participants.size() + 4 ];
		}else{
			timerVet = new String[4];
		}
		 
		timerVet[0] = (String) json.get(JsonMaker.CHAT_INDEX);
		timerVet[1] = json.get(MINUTES).toString();
		timerVet[2] = json.get(SECONDS).toString();
		timerVet[3] = json.get(JsonMaker.MACRO_EVENT_ID).toString();
		
		if(participants.size() > 0){
			for (int i = 4; i < participants.size() + 4; i++) {
				timerVet[i] = (String) participants.get(i - 4);
			}
		}
		
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
	
	public static String[] parseMakeTeamMembs(String s) throws ParseException {
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
	public static ClientDetails parseTeamMembsRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return new ClientDetails((String)json.get(JsonMaker.NICKNAME), (String)json.get(JsonMaker.TEAM_NAME));
	}

	public static String parseTeamsListRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return (String) json.get(JsonMaker.NICKNAME);
	}
	
	
	public static int getRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		return Integer.parseInt((String) json.get(REQ));
	}
	
	public static String[] parseListOfTeamsRequest(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(s);
		JSONArray idList = (JSONArray) json.get(JsonMaker.ATTENDANT);
		int size = idList.size();
		String[] teams = new String[size];
		for (int i = 0; i < size; i++) {
			teams[i] = (String) idList.get(i);
		}
		return teams;
	}
	
	
	public static MacroEvents parseMacroEventsResponse(String s) {
		JSONParser parser = new JSONParser();
		JSONObject json;
		
		MacroEvents events = new MacroEvents();
		
		try {
			json = (JSONObject) parser.parse(s);
		
			//String user = (String) json.get(JsonMaker.MACRO_EVENT_USER);

			JSONArray idList = (JSONArray) json.get(JsonMaker.MACRO_EVENT_IDS);
			JSONArray nameList = (JSONArray) json.get(JsonMaker.MACRO_EVENT_NAMES);
			int size = idList.size();
			for (int i = 0; i < size; i++) {
				events.addEvent( (String) idList.get(i) , (String) nameList.get(i));
			}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return events;
	}

}
