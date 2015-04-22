package client.model;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import server.model.JsonParser;

public class SetMessageService extends Observable implements IClientService {

	private String currentMessage="";
	
	/* (non-Javadoc)
	 * @see client.model.IClientService#setAttribute(java.lang.String)
	 */
	@Override
	public void setAttribute(String attribute) {
		String[] lines;
		try {
			lines = JsonParser
					.parseChatRequest(attribute);
			currentMessage = lines[1];
			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see client.model.IClientService#getCurrentMessageString()
	 */
	@Override
	public String getAttribute() {
		return currentMessage;
	}
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
