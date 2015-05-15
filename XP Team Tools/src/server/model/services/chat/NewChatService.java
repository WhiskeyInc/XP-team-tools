package server.model.services.chat;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.model.propagator.MessagePropagator;
import server.model.propagator.RealTimePropagator;
import server.model.propagator.RequestPropagator;
import server.model.recover.Chat;
import server.model.recover.IMessageRecover;
import server.model.recover.NoMessagesException;
import server.model.services.IService;
import client.model.ClientDetails;
/**
 * This service [{@link IService}] allow the server to create a new chat.
 * If the chat is redundant, it only align the Attendants, propagating the last
 * @see NUM_OF_MESSAGES
 * @author alberto
 *
 */
public class NewChatService implements IService {

	private volatile ChatsManager chatsManager;
	private volatile MessagePropagator messagePropagator;
	/**
	 * The messages that a client recovers immediately
	 */
	public static final int NUM_OF_MESSAGES = 10;

	public NewChatService(ChatsManager chatsManager, MessagePropagator messagePropagator) {
		super();
		this.chatsManager = chatsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {

		ClientDetails[] details = JsonParser.parseNewChatRequest(line);
		String teamName = details[0].getTeamName();
		Chat chatMokeUp = new Chat(teamName);
		for (int i = 0; i < details.length; i++) {
			chatMokeUp.addAttendant(details[i]);
		}
		if (chatsManager.has(chatMokeUp)) {
			Chat realChat = chatsManager.get(chatsManager.indexOf(chatMokeUp));
			messagePropagator.setPropagator(new RequestPropagator());
			messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.indexOf(realChat)), details[0]);
			
			messagePropagator.setPropagator(new RealTimePropagator());
			for (int i = 1; i < details.length; i++) {
				messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.indexOf(realChat)), details[i]);
			}
			try {
				alignClient(details[0], realChat);
			} catch (NoMessagesException e) {
				System.err.println("Eccezione in " + NewChatService.class);
			}

		} else {
			chatsManager.add(chatMokeUp);
			//NB IL primo deve essere chi la richiede.
			messagePropagator.setPropagator(new RequestPropagator());
			messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), details[0]);
			messagePropagator.setPropagator(new RealTimePropagator());
			for (int i = 1; i < details.length; i++) {
				messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), details[i]);
			}
		}
	}

	private void alignClient(ClientDetails details, IMessageRecover recoverer)
			throws NoMessagesException, IOException {

		String[] messages = recoverer.recoverLastMessages(NUM_OF_MESSAGES);
		for (int i = 0; i < messages.length; i++) {
			messagePropagator.propagateMessage(messages[i], details);
		}
	}
}
