package server.model;

import java.util.ArrayList;

public interface IChatStorer {
	/**
	 * 
	 * @param message
	 * @return true when the operation finish
	 */
	public void storeMessage(String message);
	public ArrayList<String> getMessages();
}
