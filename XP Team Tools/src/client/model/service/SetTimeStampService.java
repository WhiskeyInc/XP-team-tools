package client.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import client.model.MessageObservable;
import protocol.JsonParser;
import timer.TimerFormatter;

/**
 * This class implements the Time Stamp Service, i.e., it stores in a map of observable messages
 * the time stamp to be shown on display (every message/time stamp is associated to only one chat).
 * Moreover, it's possible to get this message (if no timer request have been made up to this time,
 * the initial timestamp is set to default value of 10:00)
 * 
 * 
 * 
 * @author alberto
 *
 */
public class SetTimeStampService implements IClientService {

	private Map<Integer, MessageObservable> obsMap = new HashMap<Integer, MessageObservable>();


	@Override
	public void setAttribute(String request) {
		String[] lines;
		try {
			lines = JsonParser
					.parseTimerRequest(request);
			int index = Integer.parseInt(lines[0]);
			if (obsMap.containsKey(index)) {
				obsMap.get(index).setMessage(TimerFormatter.getDisplay(
						Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), lines[3]));
			} else {
				obsMap.put(index, new MessageObservable(TimerFormatter.getDisplay(
						Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), lines[3])));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Observable getAttribute(int index) {
		if(!obsMap.containsKey(index)) {
			obsMap.put(index, new MessageObservable("00:10:niente"));
		} 
		
		return obsMap.get(index);

	}

}
