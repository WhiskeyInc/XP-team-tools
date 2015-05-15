package client.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import client.model.MessageObservable;
import protocol.JsonParser;

/**
 * This class implements the message service, it has a map of observable messages
 * that associate to every open chat only one observable message, by verifying the index of the chat.
 * Afterwards, it's possible to take the value (i.e. the message) from the map
 * 
 *@author alberto
 *
 */
public class SetMessageService implements IClientService {

	private Map<Integer, MessageObservable> obsMap = new HashMap<Integer, MessageObservable>();
	
	@Override
	public void setAttribute(String request) {
		String[] lines;
		try {
			lines = JsonParser
					.parseChatRequest(request);
			int index = Integer.parseInt(lines[0]);
			if (obsMap.containsKey(index)) {
				obsMap.get(index).setMessage(lines[1]);
			} else {
				obsMap.put(index, new MessageObservable(lines[1]));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Observable getAttribute(int index) {
		if(!obsMap.containsKey(index)) {
			obsMap.put(index, new MessageObservable(""));
		} 
		
		return obsMap.get(index);
	}
	
	
	

}
