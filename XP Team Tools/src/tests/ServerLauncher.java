package tests;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.events.SendPost;
import server.model.Server;
import server.model.propagator.ClientsManager;
import server.model.propagator.MessagePropagator;
import server.model.propagator.RealTimePropagator;
import server.model.propagator.RequestPropagator;
import server.model.services.DisconnectService;
import server.model.services.EventService;
import server.model.services.chat.ChatService;
import server.model.services.chat.ChatsManager;
import server.model.services.chat.NewChatService;
import server.model.services.teams.AddTeamMembService;
import server.model.services.teams.ListOfTeamsService;
import server.model.services.teams.NewTeamService;
import server.model.services.teams.TeamMembsService;
import server.model.services.teams.TeamsManager;
import server.model.services.timer.TimerService;
import server.model.services.timer.TimersManager;

public class ServerLauncher {
	public static void main(String[] args) {
		

		ChatsManager chatsManager = ChatsManager.getInstance();
		TimersManager timersManager = TimersManager.getInstance();
		ClientsManager clientsManager = ClientsManager.getInstance();
		clientsManager.connectToDB();
		Server server = new Server(clientsManager);


		server.addService(JsonParser.TIMER, new TimerService(chatsManager, timersManager, new MessagePropagator(ClientsManager.getInstance(), new RealTimePropagator()), new SendPost("http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(JsonParser.CHAT, new ChatService(chatsManager, new MessagePropagator(ClientsManager.getInstance(), new RealTimePropagator())));
		server.addService(
				Integer.parseInt(JsonMaker.ADD_TEAM_MEMB),
				new AddTeamMembService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager.getInstance(), new RealTimePropagator())));
		server.addService(Integer.parseInt(JsonMaker.NEW_CHAT),
				new NewChatService(ChatsManager.getInstance(), new MessagePropagator(ClientsManager.getInstance(), new RealTimePropagator())));
		server.addService(Integer.parseInt(JsonMaker.NEW_TEAM),
				new NewTeamService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager.getInstance(), new RequestPropagator())));
		server.addService(Integer.parseInt(JsonMaker.DISCONNECT),
				new DisconnectService(ClientsManager.getInstance()));
		server.addService(
				Integer.parseInt(JsonMaker.EVENT),
				new EventService(
						new SendPost(
								"http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(Integer.parseInt(JsonMaker.TEAMS), new ListOfTeamsService(TeamsManager.getInstance(), new MessagePropagator(ClientsManager.getInstance(), new RequestPropagator()) ));
		server.addService(Integer.parseInt(JsonMaker.TEAM_MEMBS), new TeamMembsService(TeamsManager.getInstance(), new MessagePropagator(ClientsManager.getInstance(), new RequestPropagator())));
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}

}
