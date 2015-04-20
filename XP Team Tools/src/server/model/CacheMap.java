package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.utils.FileWriter;

public class CacheMap implements IChatStorer, IMessageRecover {

	private Map<String, ArrayList<String>> mapMessageList = new HashMap<String, ArrayList<String>>();
	private FileWriter database = new FileWriter(this);
	
	@Override
	public void storeMessage(String teamName, String message) {
		if (mapMessageList.containsKey(teamName)) {
			addTeamMessage(teamName, message);
		} else {
			initMapMessageList(teamName, message);
		}
	}

	private void initMapMessageList(String teamName, String message) {
		mapMessageList.put(teamName, new ArrayList<String>());
		addTeamMessage(teamName, message);
	}

	private void addTeamMessage(String teamName, String message) {
		mapMessageList.get(teamName).add(message);
		writeData();
	}

	@Override
	public Map<String, ArrayList<String>> getMessages() {

		return mapMessageList;
	}

	@Override
	public String[] recoverLastMessages(String teamName, int numOfMessages)
			throws NoMessagesException {

		// TODO

		String[] messages = new String[numOfMessages];

		if (mapMessageList.containsKey(teamName)) {
			ArrayList<String> messageList = mapMessageList.get(teamName);

			for (int i = 0; i < numOfMessages; i++) {
				int size = messageList.size();
				messages[i] = messageList.get(size - numOfMessages + i);
			}
		}
		return messages;
	}

	@Override
	public int getNumOfMessages(String teamName) throws NoMessagesException {
		return mapMessageList.get(teamName).size();
	}
	
	public void writeData(){
		database.writeDatabase();
	}
}
