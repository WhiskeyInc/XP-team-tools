package client.model;

import java.util.Observable;

import protocol.JsonParser;
/**
 * TODO dovrei creare la nuova UI o rimandare a qualcosa che la crea
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
