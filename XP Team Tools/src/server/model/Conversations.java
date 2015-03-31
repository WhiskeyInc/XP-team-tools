package server.model;

import java.util.LinkedList;

/**
 * The class is responsible of conversations management. The
 * {@link Conversation} is stored into a LinkedList which has to be used as a
 * session buffer and it has to be saved into a different data structure as a
 * DB, file or whatever and, at a new session, it has to retrieve the last TOT
 * conversations. Those last convs have to contain TOT last messages (to be
 * implemented).
 * 
 * @author koelio, nicola
 *
 */
public class Conversations {

	private LinkedList<Conversation> convs = new LinkedList<Conversation>();

	public String getLastMessage() {// tmp method for testing
		return "Ciao";
	}

	public boolean hasConversations() {// tmp method for testing

		// convs.add("Anus");
		return !(convs.isEmpty());
	}

	public LinkedList<Conversation> getConvs() {
		return convs;
	}

	/**
	 * creates new conversation with separated String participant's names
	 * 
	 * @param participants
	 */
	public String addConv(String... participants) {
		int convIndex = checkConv(participants);
		if (convIndex != (-1)) {
			// TODO

			return "Conv esiste";
		} else {
			convs.add(new Conversation(participants));
			return "Conv NON esiste";
		}
	}

	private int checkConv(String[] participants) {

		LinkedList<String> tmp = new LinkedList<String>();

		for (int i = 0; i < participants.length; i++) {
			tmp.add(participants[i]);
		}

		for (int i = 0; i < convs.size(); i++) {
			if (convs.get(i).getParticipants().containsAll(tmp)
					&& tmp.containsAll(convs.get(i).getParticipants())) {
				return i;
			}
		}
		return -1;
	}
}
