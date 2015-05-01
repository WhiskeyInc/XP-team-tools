package client.model;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;

public class SetMessageService extends Observable implements IClientService {

	private String[] currentMessage = new String[1];
	
	/* (non-Javadoc)
	 * @see client.model.IClientService#setAttribute(java.lang.String)
	 */
	@Override
	public void setAttribute(String request) {
		String[] lines;
		try {
			lines = JsonParser
					.parseChatRequest(request);
			currentMessage[0] = lines[1];
			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see client.model.IClientService#getCurrentMessageString()
	 */
	@Override
	public String[] getAttribute() {
		return currentMessage;
	}
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
