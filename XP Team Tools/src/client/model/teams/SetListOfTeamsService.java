package client.model.teams;

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
	
	@Override
	public void setMembs(String request) {
		try {
			teams = JsonParser.parseListOfTeamsRequest(request);
			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String[] getMembs() {
		return teams;
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
