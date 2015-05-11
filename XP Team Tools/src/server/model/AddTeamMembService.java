package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.ClientDetails;
import client.model.Team;

//TODO se estendo qualcosa che ha teamManager?
public class AddTeamMembService implements IService {

	private TeamsManager teamManager;
	private ChatsManager chatsManager;
	private MessagePropagator messagePropagator;

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
			messagePropagator.propagateMessage(JsonMaker.teamMembsRequest(membs), new ClientDetails(membs[i], teamName));
		}
		// NB bloccante se uso il wait
		//messagePropagator.setPropagator(new RequestPropagator());
	//messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size() - 1), det);
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
