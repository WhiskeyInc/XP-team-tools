package server.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import client.model.ClientDetails;
/**
 * A simple Chat with the teamName,
 * the list of attendants and the list of message
 * @author alberto
 *
 */
public class Chat {

	private String teamName;
	private ArrayList<ClientDetails> attendantsDetails = new ArrayList<ClientDetails>();
	private List<String> messageList = new LinkedList<String>();

	public Chat(String teamName) {
		super();
		this.teamName = teamName;
	}

	public void addMessage(String message) {
		messageList.add(message);
	}

	public List<String> getMessages() {
		return messageList;
	}

	public String getTeamName() {
		return teamName;
	}

	public void addAttendant(ClientDetails details) {
		if (!has(details)) {
			attendantsDetails.add(details);
		}
	}

	public ArrayList<ClientDetails> getAttendantsDetails() {
		return attendantsDetails;
	}

	public boolean has(ClientDetails details) {
		return attendantsDetails.contains(details);
	}

	public String[] recoverLastMessages(int numOfMessages)
			throws NoMessagesException {
		int size = messageList.size();
		if(size < numOfMessages) {
			String[] messages = new String[size];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = messageList.get(i);
			}
			return messages;

		}
		String[] messages = new String[numOfMessages];

		for (int i = 0; i < numOfMessages; i++) {
			messages[i] = messageList.get(size - numOfMessages + i);
		}

		return messages;
	}

	public int getNumOfMessages() {
		return messageList.size();
	}


	/**
	 * It returns the index of the @param socket. If it'isn't, this method
	 * returns -1
	 * 
	 * @param socket
	 * @return
	 */
	// public int indexOf(Socket socket) {
	// int i = 0;
	// for (ClientDetails clientDetails : attendantsDetails) {
	// if (socket.equals(clientDetails.getSocket())) {
	// return i;
	// }
	// i++;
	// }
	// return -1;
	// }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Chat)) {
			return false;
		}
		if (teamName.equals(((Chat) obj).teamName)) {
			if (attendantsDetails.size() == ((Chat) obj).attendantsDetails
					.size()) {
				for (int i = 0; i < attendantsDetails.size(); i++) {
					if (!attendantsDetails
							.get(i)
							.getNickname()
							.equals(((Chat) obj).attendantsDetails.get(i)
									.getNickname())) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
