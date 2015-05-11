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
	 * Function to propagate the event to the second server, from the client's json
	 * @param json
	 */
	public void propagateClientEvent(String json);
	
	
	/**
	 * Function to send an automatic event to the second server
	 * @param user
	 * @param eventName
	 * @param participants
	 */
	public void sendAutomaticEventAction(String user, String eventName,
			ArrayList<String> participants);

	
	/**
	 * Function to send a manual event to another server
	 * @param user
	 * @param eventName
	 * @param participants
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param min
	 */
	public void sendManualEventAction(String user, String eventName,
			ArrayList<String> participants, String year, String month,
			String day, String hour, String min);

}
