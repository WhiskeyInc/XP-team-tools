package client.model;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
/**
 * It' s a Client' s feature to get the actual teams of the user.
 * Extends {@link Observable} to change in real time the teams list.
 * @author alberto
 *
 */
public class SetListOfTeamsService extends Observable implements IListService {

	private String[] teams;
	
	/* (non-Javadoc)
	 * @see client.model.IClientService#setAttribute(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see client.model.IListServices#setMembs(java.lang.String)
	 */
	@Override
	public void setMembs(String request) {
		try {
			teams = JsonParser.parseListOfTeamsRequest(request);
			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see client.model.IClientService#getCurrentMessageString()
	 */
	/* (non-Javadoc)
	 * @see client.model.IListServices#getMembs()
	 */
	@Override
	public String[] getMembs() {
		return teams;
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
