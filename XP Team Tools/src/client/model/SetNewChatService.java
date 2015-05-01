package client.model;

import java.util.Observable;

import protocol.JsonParser;

public class SetNewChatService extends Observable implements IClientService{

	private String[] chatIndex = new String[1];
	
	@Override
	public void setAttribute(String request) {
		chatIndex[0] = String.valueOf(JsonParser.parseChatIndexRequest(request));
		update();
	}

	@Override
	public String[] getAttribute() {
		return chatIndex;
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}

}
