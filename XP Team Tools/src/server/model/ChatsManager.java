package server.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The ChatsManager is a singleton manager of the Chats.
 * It register each chat and allow to get the index of one chat,
 * in this way the server is able to address the client about the 
 * right chat required
 * 
 * @author Alberto
 *
 */
public class ChatsManager {

	private List<Chat> completeChatList = new LinkedList<Chat>();

	private static ChatsManager instance = new ChatsManager();

	private ChatsManager() {
	}

	public void add(Chat chat) {
		if (!has(chat)) {
			System.out.println("Aggiungo chat " + ChatsManager.class);
			completeChatList.add(chat);
		}
	}

	public boolean has(Chat chat) {
		return completeChatList.contains(chat);
	}

	public Chat get(int index) {
		return completeChatList.get(index);
	}

	/**
	 * Used as key
	 * 
	 * @param chat
	 * @return
	 */
	public int indexOf(Chat chat) {
		return completeChatList.indexOf(chat);
	}

	public void remove(Chat chat) {
		if (has(chat)) {
			completeChatList.remove(indexOf(chat));
		}
	}

	public int size() {
		return completeChatList.size();
	}
	
	public static ChatsManager getInstance() {
		return instance;
	}

}
