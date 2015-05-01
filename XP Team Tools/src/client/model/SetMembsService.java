package client.model;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
/**
 * It' s a Client' s feature to store the actual members of the team.
 * Extends {@link Observable} to change in real time the members list.
 * Implements  {@link IClientService}
 * @author alberto
 *
 */
public class SetMembsService extends Observable implements IClientService {

	private String[] membs;
	
	/* (non-Javadoc)
	 * @see client.model.IClientService#setAttribute(java.lang.String)
	 */
	@Override
	public void setAttribute(String request) {
		try {
			membs = JsonParser
					.parseTeamMembsRequest(request);
			for (int i = 0; i < membs.length; i++) {
				System.out.println(membs[i] + " " + SetMembsService.class);
			}
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
		return membs;
	}
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	

}
