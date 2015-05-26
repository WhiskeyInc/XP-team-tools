package client.model;

import java.util.ArrayList;
import java.util.Date;

import protocol.JsonMaker;

/**
 * This class is never used, but it's a useful summary of all the ClientAPIs
 * 
 * @author Alberto
 *
 */
public class ClientAPIs{
	
	private Client client;

		public ClientAPIs(Client client) {
			super();
			this.client = client;
		}

		public void newChat(ClientDetails... details) {
			client.sendMessageToServer(JsonMaker.newChatRequest(details));
		}
		
		
		public void connectToServer(ClientDetails details) {
			client.sendMessageToServer(JsonMaker.connectToServerRequest(details));
		}
		
		
		public void chat(String message, String chatId) {
			client.sendMessageToServer(JsonMaker.chatRequest(message, chatId));
		}

		public void timer(String chatIndex, int minutes, int seconds, String id) {
			client.sendMessageToServer(JsonMaker.timerRequest(chatIndex, minutes, seconds, null, id));
		}

		
		public void newTeam(String teamName, String teamCreatorNick) {
			client.sendMessageToServer(JsonMaker.newTeamRequest(teamName, teamCreatorNick));
		}
		
		public void addTeamMemb(ClientDetails details) {
			client.sendMessageToServer(JsonMaker.addTeamMembRequest(details));
		}
		
		
		public void disconnect(ClientDetails det) {
			client.sendMessageToServer(JsonMaker.disconnectRequest(det));
		}
		
		public void teamMembs(String[] nicks) {
			client.sendMessageToServer(JsonMaker.makeTeamMembs(nicks));
		}


		public void manualEvent(String eventName, ArrayList<String> participants, Date date) {
			client.sendMessageToServer(JsonMaker.manualEventRequest("admin", eventName, participants, date));
		}
		
		
		public void automaticEvent(String eventName, ArrayList<String> participants, String id) {
			client.sendMessageToServer(JsonMaker.automaticEventRequest("admin", eventName, participants, id));
		}

	}


