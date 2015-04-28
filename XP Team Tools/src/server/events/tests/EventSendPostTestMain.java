package server.events.tests;

import java.util.ArrayList;

import server.events.ISendEvent;
import server.events.SendPost;

public class EventSendPostTestMain {
	
	public static void main(String[] args) {
		
		ISendEvent sender = new SendPost("http://localhost:9998/requests");
		
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Mario");
		participants.add("Luigi");
		participants.add("Bardo");
		participants.add("Ermenegildo");
		
		sender.sendEventCreation("bo", participants);

	}

}
