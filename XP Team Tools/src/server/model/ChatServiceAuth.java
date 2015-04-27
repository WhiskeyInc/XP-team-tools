package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class ChatServiceAuth implements IService {

	private ClientsManagerAuth clientsManager;
	private IChatStorer chatStorer;

	public ChatServiceAuth(ClientsManagerAuth clientsManager, IChatStorer chatStorer) {
		super();
		this.clientsManager = clientsManager;
		this.chatStorer	= chatStorer;
	}

	@Override
	public void doAction(String line) throws IOException,
			ParseException {
		
		String[] lines = JsonParser
				.parseChatRequest(line);
		String teamName = lines[0];
		String message = lines[1];
		System.out.println("Il team è: " + teamName);
		System.out.println(message); // TODO
		clientsManager.propagateMessageToTeamClients(
				line, teamName);
		chatStorer.storeMessage(teamName, line);
	}

}