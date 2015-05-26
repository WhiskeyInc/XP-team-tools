package server.utils;

import java.util.ArrayList;

import client.model.ClientDetails;

/**
 * An interface that abstracts from the ways how conversations are stored
 * @author pavlo
 *
 */
public interface ILogger {

	/**
	 * Stores conversations
	 * 
	 * @param attendantsDetails the attendants of a particular chat
	 * @param m message
	 */
	public void writeDatabase(ArrayList<ClientDetails> attendantsDetails,
			String m);

}
