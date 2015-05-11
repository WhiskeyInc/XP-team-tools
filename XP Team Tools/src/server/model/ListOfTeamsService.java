package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.ClientDetails;
import client.model.Team;

public class ListOfTeamsService implements IService {

	private TeamsManager teamsManager;
	private MessagePropagator messagePropagator;

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
		//prendo il team perchè forse potrei aver bisogno di altre cose
		String[] teamsNames = new String[teams.length];
		
		for (int i = 0; i < teams.length; i++) {
			teamsNames[i] = teams[i].getName();
		}
		
		messagePropagator.propagateMessage(JsonMaker.makeListOfTeams(teamsNames), det);
		
	}
}