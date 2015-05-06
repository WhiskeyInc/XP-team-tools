package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import client.model.ClientDetails;

public class DisconnectService implements IService{

	private ClientsManager2 clientsManager;
	
	public DisconnectService(ClientsManager2 clientsManager) {
		super();
		this.clientsManager = clientsManager;
	}


	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det = JsonParser.parseDisconnectRequest(line);
		clientsManager.get(det).setOnline(false);
	}

}
