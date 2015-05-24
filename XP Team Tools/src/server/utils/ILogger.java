package server.utils;

import java.util.ArrayList;

import client.model.ClientDetails;

public interface ILogger {

	public void writeDatabase(ArrayList<ClientDetails> attendantsDetails,
			String m);

}
