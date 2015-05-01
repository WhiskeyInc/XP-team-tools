package server.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;
import client.model.Team;
/**
 * It allow the server to add a Team
 * @author alberto
 *
 */
public class NewTeamService implements IService{

	private TeamsManager teamsManager;
	private ChatsManager chatsManager;
	private ClientsManager2 clientsManager1;

	public NewTeamService(TeamsManager teamsManager,
			ChatsManager chatsManager, ClientsManager2 clientsManager1) {
		super();
		this.teamsManager = teamsManager;
		this.chatsManager = chatsManager;
		this.clientsManager1 = clientsManager1;
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
		propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), details, chat);
	}
	
	private void propagateMessage(String message, ClientDetails details,
			Chat chat) throws IOException {
		ClientConnectionDetails conDet = clientsManager1.get(details);
		try {
			if (conDet.isOnline()) {

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						conDet.getRequestSocket().getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
				System.out.println("SONO QUI " + message + " "+ NewTeamService.class);

			}
		} catch (SocketException e) {
			//TODO
//			int index = chat.indexOf(conDet.getSocket());
//			if (index != -1) {
//				chat.getAttendantsDetails().get(index).setOnline(false);
//			} else {
//				System.err.println("Error, index = " + index);
//			}
		}
	}

}
