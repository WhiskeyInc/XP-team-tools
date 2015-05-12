package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.ClientDetails;
import client.model.Team;

public class TeamMembsService implements IService{

	private TeamsManager teamsManager;
	private MessagePropagator messagePropagator;
	
	public TeamMembsService(TeamsManager teamsManager,
			MessagePropagator messagePropagator) {
		super();
		this.teamsManager = teamsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det  = JsonParser.parseTeamMembsRequest(line);
		Team team = teamsManager.get(teamsManager.indexOf(det.getTeamName()));
		String[] membs = team.getMembers();
		String[] membsWithoutMe = new String[membs.length-1];
		int i = 0;
		for (String string : membs) {
			if (!string.equals(det.getNickname())) {
				membsWithoutMe[i] = string;
				i++;
			}
		}
		messagePropagator.propagateMessage(JsonMaker.makeTeamMembs(membsWithoutMe), det);
	}

}