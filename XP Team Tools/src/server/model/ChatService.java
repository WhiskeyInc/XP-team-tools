package server.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import string.formatter.Formatter;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ChatService implements IService {

	private ChatsManager chatsManager;
	private ClientsManager2 clientsManager1;
	private int id;


	public ChatService(ChatsManager chatsManager,
			ClientsManager2 clientsManager1) {
		super();
		this.chatsManager = chatsManager;
		this.clientsManager1 = clientsManager1;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {

		String[] lines = JsonParser.parseChatRequest(line);
		String id = lines[0];
		System.out.println("Linee: " + lines[0] +" " + lines[1] + "[" +ChatService.class + "]");
		this.id = Integer.parseInt(id);// TODO
		Chat chat  = chatsManager.get(this.id);
		ArrayList<ClientDetails> list = chat.getAttendantsDetails();
		chat.addMessage(line);
		System.out.println("Numero di chat: " + chatsManager.size() + " " + "[" +ChatService.class + "]");
		System.out.println("Lista di attendant della chat, dimensione = "+ list.size() + " " +"[" +ChatService.class + "]");
		for (ClientDetails details : list) {
			propagateMessage(line, details);
		}
	}

	private void propagateMessage(String message, ClientDetails details)
			throws IOException {
		ClientConnectionDetails conDet = clientsManager1.get(details);
		System.out.println("Nick: " + details.getNickname() + "Team : "+  details.getTeamName());
		System.out.println("Size di clientsManager = " + clientsManager1.size()+" "+ ChatService.class);
		try {
			// TODO Salvare qui...
			if (conDet.isOnline()) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						conDet.getRealTimeSocket().getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
			}
		} catch (SocketException e) {
			conDet.setOnline(false);
		}
	}
}