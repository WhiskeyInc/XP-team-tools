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

//TODO se estendo qualcosa che ha teamManager?
public class AddTeamMembService implements IService {

	private TeamsManager teamManager;
	private ChatsManager chatsManager;
	private ClientsManager2 clientsManager1;


	public AddTeamMembService(TeamsManager teamManager,
			ChatsManager chatsManager, ClientsManager2 clientsManager1) {
		super();
		this.teamManager = teamManager;
		this.chatsManager = chatsManager;
		this.clientsManager1 = clientsManager1;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det  = JsonParser.parseAddTeamMembRequest(line);
		String teamName = det.getTeamName();
		Chat chat = rebuildChat(teamName);
		teamManager.addTeamMemb(teamName, det.getNickname());
		System.out.println("Size = " + teamManager.size()+ AddTeamMembService.class);
		System.out.println("Size singleton " + TeamsManager.getInstance().size() + AddTeamMembService.class);
		chatsManager.get(chatsManager.indexOf(chat)).addAttendant(
				det);
		
		Team team = teamManager.get(teamManager.indexOf(teamName));
		String[] membs = team.getMembers();
		for (int i = 0; i < membs.length; i++) {
			propagateMessageFirstChan(JsonMaker.teamMembsRequest(membs), new ClientDetails(membs[i], teamName), chat);
		}
		//NB bloccante se uso il wait
		propagateMessage(JsonMaker.chatIndexRequest(chatsManager.size()-1), det, chat);
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
	
	private void propagateMessageFirstChan(String message, ClientDetails details,
			Chat chat) throws IOException {
		ClientConnectionDetails conDet = clientsManager1.get(details);
		try {
			if (conDet.isOnline()) {

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						conDet.getRealTimeSocket().getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
			}
		} catch (SocketException e) {
			e.printStackTrace();
			//TODO
			conDet.setOnline(false);
		}
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
			}
		} catch (SocketException e) {
			e.printStackTrace();
			//TODO
			conDet.setOnline(false);
		}
	}

}
