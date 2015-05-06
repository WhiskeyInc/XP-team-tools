package server.events;

import java.util.ArrayList;

/**
 * Interface of a class made to send the information of a new event to the
 * second server. The event can be generated from the chat ui (like a meeting)
 * or automatically after the end of a "tomato"
 * 
 * @author Nicola
 */
public interface IEventActionRequest {

	/**
	 * Function to send the event to a server, from the details
	 * @return
	 */
	public void sendEventAction(String eventName, ArrayList<String> participants);

	/**
	 * Function to send the event to a server, from the json
	 * @return
	 */
	public void sendEventAction(String json);

}
