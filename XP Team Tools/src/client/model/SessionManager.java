package client.model;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

	private Map<Integer, Boolean> chatOpenMap = new HashMap<Integer, Boolean>();
	private static SessionManager instance = new SessionManager();
	
	private SessionManager() {
		super();
	}
	
	// only for test multiples client on the same pc
	public SessionManager(Map<Integer, Boolean> chatOpenMap) {
		super();
	}


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
	
	public static SessionManager getInstance() {
		return instance;
	}
	
}
