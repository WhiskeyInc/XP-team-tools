package client.model;

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
	
	/* (non-Javadoc)
	 * @see client.model.IClientService#setAttribute(java.lang.String)
	 */
	public void setMembs(String request) {
		try {
			membs = JsonParser
					.parseMakeTeamMembs(request);
			System.err.println(request + SetMembsService.class);
//			for (int i = 0; i < membs.length; i++) {
//				System.out.println(membs[i] + " " + SetMembsService.class);
//			}
			update();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see client.model.IClientService#getCurrentMessageString()
	 */
	public String[] getMembs() {
		return membs;
	}
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
