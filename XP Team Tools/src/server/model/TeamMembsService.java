//package server.model;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.net.SocketException;
//import java.util.Set;
//
//import org.json.simple.parser.ParseException;
//
//import string.formatter.Formatter;
//import client.model.ClientConnectionDetails;
//import client.model.ClientDetails;
//import client.model.Team;
//
//public class TeamMembsService implements IService{
//
//	private TeamsManager teamsManager;
//	private ClientsManager clientsManager;
//	
//	public TeamMembsService(TeamsManager teamsManager,
//			ClientsManager clientsManager) {
//		super();
//		this.teamsManager = teamsManager;
//		this.clientsManager = clientsManager;
//	}
//
//	@Override
//	public void doAction(String line) throws IOException, ParseException {
//		ClientDetails det  = JsonParser.parseAddTeamMembRequest(line);
//		Team team = teamsManager.get(teamsManager.indexOf(det.getTeamName()));
//		Set<String> mems = team.getMembersSet();
//		for (String string : mems) {
//			//TODO una jsonReq che mi prende un vettore e me lo carica, poi lo invio come message qui
//			//così il client riceve la lista che poi deve gestire frades
//		}
//	}
//	
//	private void propagateMessage(String message, ClientDetails details)
//			throws IOException {
//		ClientConnectionDetails conDet = clientsManager.get(details);
//		System.out.println(clientsManager.size()+" "+ ChatService.class);
//		try {
//			// TODO Salvare qui...
//			if (conDet.isOnline()) {
//				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
//						conDet.getRealTimeSocket().getOutputStream()));
//				out.write(Formatter.appendNewLine(message));
//				out.flush();
//			}
//		} catch (SocketException e) {
//			//TODO
////			Chat chat = chatsManager.get(id);
////			int index = chat.indexOf(conDet.getSocket());
////			if (index != -1) {
////				chat.getAttendantsDetails().get(index).setOnline(false);
////			} else {
////				System.err.println("Error, index = " + index);
////			}
//		}
//	}
//
//}
