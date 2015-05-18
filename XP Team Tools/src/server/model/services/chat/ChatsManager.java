package server.model.services.chat;

import java.util.LinkedList;
import java.util.List;

import server.model.recover.Chat;

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

	private volatile List<Chat> completeChatList = new LinkedList<Chat>();

	private volatile static ChatsManager instance = new ChatsManager();

	private ChatsManager() {
	}

	/**
	 * Adds a chat to the complete Chat List
	 * @param chat
	 */
	public void add(Chat chat) {
		if (!has(chat)) {
			System.out.println("Aggiungo chat " + ChatsManager.class);
			completeChatList.add(chat);
		}
	}

	/**
	 * Checks if the chat list contains the required chat
	 * @param chat
	 * @return
	 */
	public boolean has(Chat chat) {
		return completeChatList.contains(chat);
	}

	public Chat get(int index) {
		return completeChatList.get(index);
	}

	/**
	 * Return the index of a chat, used as a key
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
	
	public void removeAll() {
		completeChatList.clear();
	}

	public int size() {
		return completeChatList.size();
	}
	
	public synchronized static ChatsManager getInstance() {
		return instance;
	}

}
