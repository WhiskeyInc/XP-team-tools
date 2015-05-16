package server.events.tests;

import java.util.ArrayList;

import server.events.IEventActionRequest;
import server.events.SendPost;

public class EventSendPostTestMain {

	public static void main(String[] args) {

		IEventActionRequest sender = new SendPost(
				"http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor");

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Don Lele");
		participants.add("Nick the German");
		participants.add("Simo");
		participants.add("Digio");

		sender.sendAutomaticEventAction("admin", "communicationTest", participants, "id");
		
		sender.sendManualEventAction("admin", "communicationTestManual",
				participants, "2050", "2", "12", "12", "12");

	}

}
