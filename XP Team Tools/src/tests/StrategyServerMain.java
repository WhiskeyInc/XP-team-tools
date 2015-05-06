package tests;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.events.SendPost;
import server.model.AddTeamMembService;
import server.model.ChatService;
import server.model.ChatsManager;
import server.model.EventService;
import server.model.NewChatService;
import server.model.NewTeamService;
import server.model.ServerStrategy1_1;
import server.model.TeamsManager;
import server.model.TimerService1_1;
import server.model.TimersManager;

public class StrategyServerMain {
	public static void main(String[] args) {

		ChatsManager chatsManager = ChatsManager.getInstance();
		TimersManager timersManager = TimersManager.getInstance();
		ServerStrategy1_1 server = new ServerStrategy1_1(chatsManager);

		server.addService(JsonParser.TIMER, new TimerService1_1(chatsManager, timersManager, server.getClientsManager()));
		server.addService(JsonParser.CHAT, new ChatService(chatsManager, server.getClientsManager()));
		server.addService(
				Integer.parseInt(JsonMaker.ADD_TEAM_MEMB),
				new AddTeamMembService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), server.getClientsManager()));
		server.addService(Integer.parseInt(JsonMaker.NEW_CHAT),
				new NewChatService(ChatsManager.getInstance(), server.getClientsManager()));
		server.addService(
				Integer.parseInt(JsonMaker.NEW_TEAM),
				new NewTeamService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), server.getClientsManager()));
		server.addService(
				Integer.parseInt(JsonMaker.EVENT),
				new EventService(new SendPost("http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		

		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}

}
