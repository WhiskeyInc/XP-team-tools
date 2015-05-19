package events.tests;

import java.util.ArrayList;

import events.IEventActionRequest;
import events.SendPost;

public class EventSendPostTestMain {

	public static void main(String[] args) {

		IEventActionRequest sender = new SendPost(
				"http://localhost:9998/requests");

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Don Lele");
		participants.add("Nick the German");
		participants.add("Simo");
		participants.add("Digio");

		//sender.sendAutomaticEventAction("admin", "communicationTest", participants, "id");
		
		sender.sendManualEventAction("admin", "communicationTestManual",participants, "2050", "2", "12", "12", "12");

	}

}
