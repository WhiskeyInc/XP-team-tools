package client.model;

import java.util.HashMap;
import java.util.Map;

import ui.ChatUI;
import ui.ChaTimerUI;
import ui.PrivateChaTimerUi;

/**
 * This class manages the various chat's sessions, by storing in map the state of chats (every chat 
 * has a unique index with value true = active session, false = closed session) and registering the UI of
 * different private chats
 * 
 * @author alberto
 *
 */
public class SessionManager {

	private  Map<Integer, Boolean> chatOpenMap = new HashMap<Integer, Boolean>();
	private  static SessionManager instance = new SessionManager();
	private volatile Map<Integer, PrivateChaTimerUi> uiMap = new HashMap<Integer, PrivateChaTimerUi>();
	
	public static final int NUM_OF_MESSAGES = 10;

	
	private SessionManager() {
		super();
	}
	
	public SessionManager(Map<Integer, Boolean> chatOpenMap) {
		super();
	}


	/**
	 * Stores in a map the state of a chat
	 * @param indexId a unique Id associated to a chat
	 */
	public void registerChatOpening(int indexId) {
		if (!chatOpenMap.containsKey(indexId)) {
			chatOpenMap.put(indexId, true);
		} else {
			boolean isOpen = isChatOpen(indexId);
			if(!isOpen) {
				chatOpenMap.put(indexId, true);
			}
		}
	}
	
	public boolean isChatOpen(int indexId) {
		return chatOpenMap.get(indexId);
	}

	public void setChatClosed(int indexId) {
		chatOpenMap.put(indexId, false);
	}
	
	public boolean hasChat(int indexId) {

		return chatOpenMap.containsKey(indexId);
	}
	
	public void requireFocusForUIAt(int index) {
		uiMap.get(index).toFront();
		uiMap.get(index).requestFocus();
	}
	
	/**
	 * Register in a map of private chat UI the UI of a private chat 
	 * @param index index of private chat
	 * @param ui ui of a private chat
	 */
	public void registerUI(int index, PrivateChaTimerUi ui) {
		uiMap.put(index, ui);
	}
	
	
	public void emptyChatUI(int index) {
		if(uiMap.containsKey(index)) {
			ChatUI ui = uiMap.get(index).getChatUI();
			String text = ui.getChatAreaText();
			String[] textSplitted = text.split("\n");
			String newText = "";
			int len = textSplitted.length/2;
			if(len > NUM_OF_MESSAGES) {
				len = NUM_OF_MESSAGES;
			}
			for (int i = 0; i < len; i++) {
				newText += textSplitted[i] + "\n";
			}
			ui.setChatAreaText(newText);
		}
	}
	
	public  static SessionManager getInstance() {
		return instance;
	}
	
}
