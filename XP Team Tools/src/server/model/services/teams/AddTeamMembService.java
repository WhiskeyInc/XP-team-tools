package server.model.services.teams;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.model.propagator.MessagePropagator;
import server.model.propagator.RealTimePropagator;
import server.model.recover.Chat;
import server.model.services.IService;
import server.model.services.chat.ChatsManager;
import client.model.ClientDetails;
import client.model.Team;


public class AddTeamMembService implements IService {

	private volatile TeamsManager teamManager;
	private volatile ChatsManager chatsManager;
	private volatile MessagePropagator messagePropagator;

	public AddTeamMembService(TeamsManager teamManager,
			ChatsManager chatsManager, MessagePropagator messagePropagator) {
		super();
		this.teamManager = teamManager;
		this.chatsManager = chatsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det = JsonParser.parseAddTeamMembRequest(line);
		String teamName = det.getTeamName();
		Chat chat = rebuildChat(teamName);
		teamManager.addTeamMemb(teamName, det.getNickname());
		System.out.println("Size = " + teamManager.size()
				+ AddTeamMembService.class);
		System.out.println("Size singleton "
				+ TeamsManager.getInstance().size() + AddTeamMembService.class);
		chatsManager.get(chatsManager.indexOf(chat)).addAttendant(det);

		Team team = teamManager.get(teamManager.indexOf(teamName));
		String[] membs = team.getMembers();
		messagePropagator.setPropagator(new RealTimePropagator());
		for (int i = 0; i < membs.length; i++) {
			messagePropagator.propagateMessage(JsonMaker.makeTeamMembs(membs), new ClientDetails(membs[i], teamName));
		}
	}

	private Chat rebuildChat(String teamName) {
		Team team = teamManager.get(teamManager.indexOf(teamName));
		String[] membs = team.getMembers();
		Chat chat = new Chat(teamName);
		for (int i = 0; i < membs.length; i++) {
			chat.addAttendant(new ClientDetails(membs[i], teamName));
		}
		return chat;
	}
}
