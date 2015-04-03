package server.model;

import java.util.ArrayList;

/**
 * Interface of a storer of messages
 * @author alberto
 */
public interface IChatStorer {
	
	/**
	 * Method to store a message
	 * @param message message to store
	 */
	public void storeMessage(String message);
	
	/**
	 * @return an {@link ArrayList} of messages
	 */
	public ArrayList<String> getMessages();
}
