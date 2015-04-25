package server.events;

import java.util.ArrayList;

/**
 * Interface of a class made to send the informations of a new event to the
 * second server. The event can be generated from the chat ui (like a meeting)
 * or automatically after the end of a "tomato"
 * 
 * @author Nicola
 */
public interface ISendEvent {

	/**
	 * Function to send the event to a server
	 * @return
	 */
	public void sendEventCreation(String eventName, ArrayList<String> participants);

}
