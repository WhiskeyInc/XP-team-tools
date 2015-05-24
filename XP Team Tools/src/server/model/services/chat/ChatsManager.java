package server.model.services.chat;

import java.util.LinkedList;
import java.util.List;

import server.model.recover.Chat;

/**
 * The ChatsManager is a singleton manager of the Chats.
 * It records each chat and allows to get the index of a chat,
 * in this way the server is able to address the client to the 
 * right chat requested.
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

	/**
	 * Method for getting a desired chat
	 * @param the index of required chat
	 * @return the Chat corresponding to this index
	 */
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

	/**
	 * Removes a chat
	 * @param the chat to be removed
	 */
	public void remove(Chat chat) {
		if (has(chat)) {
			completeChatList.remove(indexOf(chat));
		}
	}
	
	/**
	 * Removes all the chats
	 */
	public void removeAll() {
		completeChatList.clear();
	}

	/**
	 * 
	 * @return the size of chatList
	 */
	public int size() {
		return completeChatList.size();
	}
	
	public synchronized static ChatsManager getInstance() {
		return instance;
	}

}
