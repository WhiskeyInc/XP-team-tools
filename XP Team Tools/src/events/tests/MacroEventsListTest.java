package events.tests;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.MacroEvents;
import events.SendPost;

public class MacroEventsListTest {
	
	public static void main(String[] args) {
		

		SendPost sender = new SendPost("http:Q//xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor");
		String message = JsonMaker.requestMacroEventsList("admin");
					
		String answer = sender.sendJson(message);
		
		//TODO test the request for the events to the other server
		//TODO remove later fake answer:
		//answer = "{\"request\": \"14\",\"action\": \"macro_event_response\",\"user\": \"admin\",\"ids\": [\"001\",\"002\",\"003\"],\"names\": [\"Incre programma tutto il dì\",\"Ciao LELE\",\"Martin fera\"]}";
		
		MacroEvents events = JsonParser.parseMacroEventsResponse(answer);
		
		System.out.println("Macro events: ");
		for (String string : events.getNames()) {
			System.out.println(string);
		}
		
	}

}
