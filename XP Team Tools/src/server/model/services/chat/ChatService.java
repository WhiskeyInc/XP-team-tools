package server.model.services.chat;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import server.model.propagator.MessagePropagator;
import server.model.recover.Chat;
import server.model.services.IService;
import server.utils.ILogger;
import client.model.ClientDetails;

/**
 * This class makes the Chat Service concrete, by overriding the abstract method
 * doAction in @IService: it propagates the message to the users of a chat
 * thanks to the controls made by @ChatsManager and
 * 
 * @MessagePropagator
 * 
 * @author ALberto
 *
 */
public class ChatService implements IService {

	private volatile ChatsManager chatsManager;
	private volatile MessagePropagator messagePropagator;
	private int id;
	private ILogger logger;

	public ChatService(ChatsManager chatsManager,
			MessagePropagator messagePropagator, ILogger logger) {
		super();
		this.chatsManager = chatsManager;
		this.messagePropagator = messagePropagator;
		this.logger = logger;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {

		String[] lines = JsonParser.parseChatRequest(line);
		String id = lines[0];
		this.id = Integer.parseInt(id);// TODO
		Chat chat = chatsManager.get(this.id);
		ArrayList<ClientDetails> list = chat.getAttendantsDetails();
		chat.addMessage(line);
		log(list, line);
		for (ClientDetails details : list) {
			messagePropagator.propagateMessage(line, details);

		}
	}

	private void log(ArrayList<ClientDetails> list, String message) {
		logger.writeDatabase(list, message);

	}
}