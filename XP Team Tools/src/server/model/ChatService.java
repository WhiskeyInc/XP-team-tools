package server.model;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import client.model.ClientDetails;

public class ChatService implements IService {

	private volatile ChatsManager chatsManager;
	private volatile MessagePropagator messagePropagator;
	private int id;

	public ChatService(ChatsManager chatsManager,
			MessagePropagator messagePropagator) {
		super();
		this.chatsManager = chatsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {

		String[] lines = JsonParser.parseChatRequest(line);
		String id = lines[0];
		System.out.println("Linee: " + lines[0] +" " + lines[1] + "[" +ChatService.class + "]");
		this.id = Integer.parseInt(id);// TODO
		Chat chat  = chatsManager.get(this.id);
		ArrayList<ClientDetails> list = chat.getAttendantsDetails();
		chat.addMessage(line);
		System.out.println("Numero di chat: " + chatsManager.size() + " " + "[" +ChatService.class + "]");
		System.out.println("Lista di attendant della chat, dimensione = "+ list.size() + " " +"[" +ChatService.class + "]");
		for (ClientDetails details : list) {
			messagePropagator.propagateMessage(line, details);
		}
	}
}