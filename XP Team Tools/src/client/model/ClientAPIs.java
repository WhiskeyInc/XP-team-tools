package client.model;

import java.util.ArrayList;

import protocol.JsonMaker;

public class ClientAPIs{
	
	private StrategyClient1_1 client;

		public ClientAPIs(StrategyClient1_1 client) {
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

		public void timer(String chatIndex, int minutes, int seconds) {
			client.sendMessageToServer(JsonMaker.timerRequest(chatIndex, minutes, seconds));
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
			client.sendMessageToServer(JsonMaker.teamMembsRequest(nicks));
		}


		public void manualEvent(String eventName, ArrayList<String> participants, String year, String month, String day,
				String hour, String minute) {
			client.sendMessageToServer(JsonMaker.manualEventRequest(eventName, participants, year, month, day, hour, minute));
		}
		
		
		public void automaticEvent(String eventName, ArrayList<String> participants) {
			client.sendMessageToServer(JsonMaker.automaticEventRequest(eventName, participants));
		}

	}


