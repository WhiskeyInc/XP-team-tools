package client.model.service;

import java.util.Observable;

import client.model.MessageObservable;
import protocol.JsonParser;
/**
 * 
 * This class implements the service of a new chat's creation, by introducing a index that 
 * identifies univocally the chat
 * 
 * @author alberto
 *
 */
public class SetNewChatService  implements IClientService{

	private static final String DEFAULT_VALUE = "-1";
	private MessageObservable chatIndex = new MessageObservable(DEFAULT_VALUE);
	
	@Override
	public void setAttribute(String request) {
		chatIndex.setMessage(String.valueOf(JsonParser.parseChatIndexRequest(request)));
	}

	@Override
	public Observable getAttribute(int index) {
		return chatIndex;
	}


}
