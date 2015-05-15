package client.model.teams;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
/**
 * It' s a Client' s feature to store the actual members of the team.
 * Extends {@link Observable} to change in real time the members list.
 * @author alberto
 *
 */
public class SetMembsService extends Observable implements IListService{

	private String[] membs;
	
	@Override
	public void setMembs(String request) {
		try {
			membs = JsonParser
					.parseMakeTeamMembs(request);
			System.err.println(request + SetMembsService.class);

			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getMembs() {
		return membs;
	}
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
