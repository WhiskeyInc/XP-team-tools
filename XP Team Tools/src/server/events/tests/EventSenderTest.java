package server.events.tests;

import java.util.ArrayList;

import server.events.ISendEvent;
import server.events.Sender;

public class EventSenderTest {
	
	public static void main(String[] args) {
		
		ISendEvent sender = new Sender("localhost",9000);
		
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Mario");
		participants.add("Luigi");
		participants.add("Bardo");
		participants.add("Ermenegildo");
		
		sender.sendEventCreation("bo", participants);
		
	}

}
