package server.model;

import java.util.ArrayList;

public class CacheList implements IChatStorer, IMessageRecover{

	private ArrayList<String> messageList = new ArrayList<String>();
	
	@Override
	public void storeMessage(String message) {
		messageList.add(message);
	}

	@Override
	public ArrayList<String> getMessages() {

		return messageList;
	}

	@Override
	public String[] recoverLastMessages(int numOfMessages) {
		
		String[] messages = new String[numOfMessages];
		for (int i = 0; i < numOfMessages; i++) {
			int size = messageList.size();
			messages[i] = messageList.get(size - numOfMessages + i);
		}
		return messages;
	}
	

}
