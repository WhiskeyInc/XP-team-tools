package client.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;

public class SetMessageService implements IClientService {

	private Map<Integer, MessageObservable> obsMap = new HashMap<Integer, MessageObservable>();
	
	@Override
	public void setAttribute(String request) {
		String[] lines;
		try {
			lines = JsonParser
					.parseChatRequest(request);
			int index = Integer.parseInt(lines[0]);
			System.out.println(request+" index = "+ index + " " + SetMessageService.class);
			if (obsMap.containsKey(index)) {
				obsMap.get(index).setMessage(lines[1]);
			} else {
				obsMap.put(index, new MessageObservable(lines[1]));
			}
		//	update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see client.model.IClientService#getCurrentMessageString()
	 */

	@Override
	public Observable getAttribute(int index) {
		if(!obsMap.containsKey(index)) {
			obsMap.put(index, new MessageObservable(""));
		} 
		
		return obsMap.get(index);
	}
	
//	private void update() {
//		setChanged();
//		notifyObservers();
//	}
//	
	

}
