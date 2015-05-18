package server.model.services.teams;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.model.propagator.MessagePropagator;
import server.model.recover.Chat;
import server.model.services.IService;
import server.model.services.chat.ChatsManager;
import client.model.ClientDetails;
import client.model.Team;
/**
 * It allows the server to add a Team
 * @author alberto
 *
 */
public class NewTeamService implements IService{

	private volatile TeamsManager teamsManager;
	private volatile ChatsManager chatsManager;
	private volatile MessagePropagator messagePropagator;

	public NewTeamService(TeamsManager teamsManager, ChatsManager chatsManager,
			MessagePropagator messagePropagator) {
		super();
		this.teamsManager = teamsManager;
		this.chatsManager = chatsManager;
		this.messagePropagator = messagePropagator;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		String[] vet = JsonParser.parseNewTeamRequest(line);
		Team team = new Team(vet[0]);
		ClientDetails details = new ClientDetails(vet[1], vet[0]);
		team.addMember(vet[1]);
		teamsManager.add(team);
		Chat chat = new Chat(vet[0]);
		chat.addAttendant(details);
		chatsManager.add(chat);
		team.setChatIndex(chatsManager.indexOf(chat));
		messagePropagator.propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), details);
	}

}
