package server.model.services.teams;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.model.propagator.MessagePropagator;
import server.model.services.IService;
import client.model.ClientDetails;
import client.model.Team;

/**
 * An implementation of IService that gets the list of teams and then propagates this
 * information thanks to @MessagePropagator
 * 
 * @author alberto
 *
 */
public class ListOfTeamsService implements IService {

	private volatile TeamsManager teamsManager;
	private volatile MessagePropagator messagePropagator;

	public ListOfTeamsService(TeamsManager teamsManager,
			MessagePropagator messagePropagator) {
		super();
		this.teamsManager = teamsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {

		String nick = JsonParser.parseTeamsListRequest(line);
		ClientDetails det = new ClientDetails(nick, null);
		Team[] teams = teamsManager.getTeamsByNickname(nick);
		String[] teamsNames = new String[teams.length];
		
		for (int i = 0; i < teams.length; i++) {
			teamsNames[i] = teams[i].getName();
		}
		
		messagePropagator.propagateMessage(JsonMaker.makeListOfTeams(teamsNames), det);
		
	}
}