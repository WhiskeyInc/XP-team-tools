package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CacheList implements IChatStorer, IMessageRecover{

	private Map<String, ArrayList<String>> mapMessageList = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> messageList = new ArrayList<String>();
	
	@Override
	public void storeMessage(String teamName, String message) {
		messageList.add(message);
	}

	@Override
	public ArrayList<String> getMessages() {

		return messageList;
	}

	@Override
	public String[] recoverLastMessages(String teamName, int numOfMessages) throws NoMessagesException {
		
		String[] messages = new String[numOfMessages];
		for (int i = 0; i < numOfMessages; i++) {
			int size = messageList.size();
			messages[i] = messageList.get(size - numOfMessages + i);
		}
		return messages;
	}

	@Override
	public int getNumOfMessages(String teamName) throws NoMessagesException {
		return messageList.size();
	}
	

}
