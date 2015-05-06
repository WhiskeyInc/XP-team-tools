package server.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;
/**
 * This service [{@link IService}] allow the server to create a new chat.
 * If the chat is redundant, it only align the Attendants, propagating the last
 * @see NUM_OF_MESSAGES
 * @author alberto
 *
 */
public class NewChatService implements IService {

	private ChatsManager chatsManager;
	private ClientsManager2 clientsManager1;
	/**
	 * The messages that a client recover immediately
	 */
	public static final int NUM_OF_MESSAGES = 10;


	public NewChatService(ChatsManager chatsManager, ClientsManager2 clientsManager1) {
		super();
		this.chatsManager = chatsManager;
		this.clientsManager1 = clientsManager1;
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
			propagateMessageResponse(JsonMaker.chatIndexRequest(chatsManager.indexOf(realChat)), details[0], realChat);
			for (int i = 1; i < details.length; i++) {
				propagateMessage(JsonMaker.chatIndexRequest(chatsManager.indexOf(realChat)), details[i], realChat);
			}
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			for (int i = 0; i < details.length; i++) {
				try {
					alignClient(details[i], realChat);
				} catch (NoMessagesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			chatsManager.add(chatMokeUp);
			//NB IL primo deve essere chi la richiede.
			propagateMessageResponse(JsonMaker.chatIndexRequest(chatsManager.size()-1), details[0], chatMokeUp);
			for (int i = 1; i < details.length; i++) {
				propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), details[i], chatMokeUp);
			}
		}
	}

	private void alignClient(ClientDetails details, Chat chat)
			throws NoMessagesException, IOException {

		//poi allineo
		String[] messages = chat.recoverLastMessages(NUM_OF_MESSAGES);
		for (int i = 0; i < messages.length; i++) {
			propagateMessage(messages[i], details, chat);
		}
	}

	public void propagateMessage(String message, ClientDetails details,
			Chat chat) throws IOException {
		ClientConnectionDetails conDet = clientsManager1.get(details);
		try {
			if (conDet.isOnline()) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						conDet.getRealTimeSocket().getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
			}
		} catch (SocketException e) {
			//TODO
//			int index = chat.indexOf(conDet.getSocket());
//			if (index != -1) {
//				chat.getAttendantsDetails().get(index).setOnline(false);
//			} else {
//				System.err.println("Error, index = " + index);
//			}
		}
	}
	
	public void propagateMessageResponse(String message, ClientDetails details,
			Chat chat) throws IOException {
		ClientConnectionDetails conDet = clientsManager1.get(details);
		try {
			if (conDet.isOnline()) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						conDet.getRequestSocket().getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
			}
		} catch (SocketException e) {
			//TODO
//			int index = chat.indexOf(conDet.getSocket());
//			if (index != -1) {
//				chat.getAttendantsDetails().get(index).setOnline(false);
//			} else {
//				System.err.println("Error, index = " + index);
//			}
		}
	}

	// TODO
//	private String[] recoverMessages(String teamName)
//			throws NoMessagesException {
//		int numOfMessages = NUM_OF_MESSAGES;
//		String[] sentMessages;
//
//		if (recover.getNumOfMessages(teamName) < numOfMessages) {
//			numOfMessages = recover.getNumOfMessages(teamName);
//		}
//		sentMessages = recover.recoverLastMessages(teamName, numOfMessages);
//
//		return sentMessages;
//	}

}
