package events;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interface of a class made to send the information of a new event to the
 * second server. The event can be generated from the chat ui (like a meeting)
 * or automatically after the end of a "tomato"
 * 
 * @author Nicola
 */
public interface IEventActionRequest {

	/**
	 * Method to propagate the event to the second server, from the client's json
	 * @param json
	 */
	public String sendJson(String json);
	
	
	/**
	 * Method to send an automatic event to the second server
	 * @param user
	 * @param eventName
	 * @param participants
	 */
	public void sendAutomaticEventAction(String user, String eventName,
			ArrayList<String> participants, String id);

	
	/**
	 * Method to send a manual event to another server
	 * @param user
	 * @param eventName
	 * @param participants
	 * @param date
	 */
	public void sendManualEventAction(String user, String eventName,
			ArrayList<String> participants, Date date);

}
