package server.events.tests;

import java.util.ArrayList;

import server.events.IEventActionRequest;
import server.events.SendPost;

public class EventSendPostTestMain {
	
	public static void main(String[] args) {
		
		IEventActionRequest sender = new SendPost("http://localhost:8080/XPTT_Web/JSONAcceptor");
		
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Mario");
		participants.add("Luigi");
		participants.add("Bardo");
		participants.add("Ermenegildo");
		
		sender.sendEventAction("addEvent", "boh", participants);

	}

}
