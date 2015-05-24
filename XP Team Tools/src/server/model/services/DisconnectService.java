package server.model.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import server.model.propagator.ClientsManager;
import client.model.ClientDetails;

/**
 * An implementation of @IService for the disconnection service, it overrides doAction 
 * method and sets offline a certain client
 * 
 * @author nicola
 *
 */
public class DisconnectService implements IService{

	private volatile ClientsManager clientsManager;
	
	public DisconnectService(ClientsManager clientsManager) {
		super();
		this.clientsManager = clientsManager;
	}


	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det = JsonParser.parseDisconnectRequest(line);
		clientsManager.get(det).setOnline(false);
	}

}
