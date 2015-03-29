package server.model;

import java.util.ArrayList;

public class CacheList implements IChatStorer{

	private ArrayList<String> messageList = new ArrayList<String>();
	
	@Override
	public void storeMessage(String message) {
		messageList.add(message);
	}

	@Override
	public ArrayList<String> getMessages() {

		return messageList;
	}
	

}
