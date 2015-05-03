package client.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import timer.TimerFormatter;

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
						Integer.parseInt(lines[1]), Integer.parseInt(lines[2])));
			} else {
				obsMap.put(index, new MessageObservable(TimerFormatter.getDisplay(
						Integer.parseInt(lines[1]), Integer.parseInt(lines[2]))));
			}
		//	update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Observable getAttribute(int index) {
		if(!obsMap.containsKey(index)) {
			obsMap.put(index, new MessageObservable("00:10"));
		} 
		
		return obsMap.get(index);

	}

//	private void update() {
//		setChanged();
//		notifyObservers();
//	}
}
