package tests;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.events.SendPost;
import server.model.AddTeamMembService;
import server.model.ChatService;
import server.model.ChatsManager;
import server.model.ClientsManager2;
import server.model.DisconnectService;
import server.model.EventService;
import server.model.ListOfTeamsService;
import server.model.MessagePropagator;
import server.model.NewChatService;
import server.model.NewTeamService;
import server.model.RealTimePropagator;
import server.model.RequestPropagator;
import server.model.ServerStrategy1_1;
import server.model.TeamMembsService;
import server.model.TeamsManager;
import server.model.TimerService1_1;
import server.model.TimersManager;

public class StrategyServerMain1O {
	public static void main(String[] args) {
		

		ChatsManager chatsManager = ChatsManager.getInstance();
		TimersManager timersManager = TimersManager.getInstance();
		ClientsManager2 clientsManager = ClientsManager2.getInstance();
		clientsManager.connectToDB();
		ServerStrategy1_1 server = new ServerStrategy1_1(clientsManager);


		server.addService(JsonParser.TIMER, new TimerService1_1(chatsManager, timersManager, new MessagePropagator(ClientsManager2.getInstance(), new RealTimePropagator()), new SendPost("http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(JsonParser.CHAT, new ChatService(chatsManager, new MessagePropagator(ClientsManager2.getInstance(), new RealTimePropagator())));
		server.addService(
				Integer.parseInt(JsonMaker.ADD_TEAM_MEMB),
				new AddTeamMembService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager2.getInstance(), new RealTimePropagator())));
		server.addService(Integer.parseInt(JsonMaker.NEW_CHAT),
				new NewChatService(ChatsManager.getInstance(), new MessagePropagator(ClientsManager2.getInstance(), new RealTimePropagator())));
		server.addService(Integer.parseInt(JsonMaker.NEW_TEAM),
				new NewTeamService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager2.getInstance(), new RequestPropagator())));
		server.addService(Integer.parseInt(JsonMaker.DISCONNECT),
				new DisconnectService(ClientsManager2.getInstance()));
		server.addService(
				Integer.parseInt(JsonMaker.EVENT),
				new EventService(
						new SendPost(
								"http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(Integer.parseInt(JsonMaker.TEAMS), new ListOfTeamsService(TeamsManager.getInstance(), new MessagePropagator(ClientsManager2.getInstance(), new RequestPropagator()) ));
		server.addService(Integer.parseInt(JsonMaker.TEAM_MEMBS), new TeamMembsService(TeamsManager.getInstance(), new MessagePropagator(ClientsManager2.getInstance(), new RequestPropagator())));
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}

}
