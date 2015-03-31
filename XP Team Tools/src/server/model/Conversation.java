package server.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is itself the a conversation. Two main properties: participants in
 * a LinkedList and conv(ersation) in a HashMap. This class contains all the
 * messages of current conversation. The conversation, as it may seem, is not
 * unique for the chosen participants because in {@link Conversations} there is
 * created a new Conversation each time some participants, even the same, start
 * a conv.
 * 
 * 
 * @author koelio, nicola
 *
 */
public class Conversation {

	private LinkedList<String> participants = new LinkedList<String>();
	private HashMap<Long, Message> messages = new HashMap<Long, Message>();

	public Conversation(String[] participants) {
		for (int i = 0; i < participants.length; i++) {
			this.participants.add(participants[i]);
		}
	}

	public void addMessage(String author, String msg) {

		if (isParticipant(author))
			messages.put(System.currentTimeMillis(), new Message(author, msg));
	}

	private boolean isParticipant(String p) {
		for (String string : participants) {
			if (string.equals(p))
				return true;
		}
		return false;

	}

	public LinkedList<String> getParticipants() {
		return participants;
	}

	public HashMap<Long, Message> getMessages() {
		return messages;
	}

}
