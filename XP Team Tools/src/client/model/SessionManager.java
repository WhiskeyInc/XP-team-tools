package client.model;

import java.util.HashMap;
import java.util.Map;

import ui.ChatUIObserverStrategy1;
import ui.UIObserverStrategy1;

public class SessionManager {

	private  Map<Integer, Boolean> chatOpenMap = new HashMap<Integer, Boolean>();
	private  static SessionManager instance = new SessionManager();
	private volatile Map<Integer, UIObserverStrategy1> uiMap = new HashMap<Integer, UIObserverStrategy1>();
	
	//NB deve essere uguale a quella in NewChatService
	public static final int NUM_OF_MESSAGES = 10;

	
	private SessionManager() {
		super();
	}
	
	// only for test multiples client on the same pc
	public SessionManager(Map<Integer, Boolean> chatOpenMap) {
		super();
	}


	public void registerChatOpening(int indexId) {
		System.err.println(indexId + " " + SessionManager.class);
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
		System.err.println("Sto chiudendo la chat indice " + indexId);
		chatOpenMap.put(indexId, false);
	}
	
	public boolean hasChat(int indexId) {
		System.err.println(indexId + " has " + SessionManager.class);

		return chatOpenMap.containsKey(indexId);
	}
	
	public void requireFocusForUIAt(int index) {
		uiMap.get(index).toFront();
		uiMap.get(index).requestFocus();
	}
	
	public void registerUI(int index, UIObserverStrategy1 ui) {
		uiMap.put(index, ui);
	}
	//Temporary solution: nb it is "server-unsafe" and it make visible the changes
	//TODO bufferize la classe chat e aggiungere tipo un metodo flush()
	public void emptyChatUI(int index) {
		if(uiMap.containsKey(index)) {
			ChatUIObserverStrategy1 ui = uiMap.get(index).getChatUI();
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
