package tests;

import server.utils.Logger;
import protocol.JsonMaker;
import protocol.JsonParser;
import server.db.DBConnection;
import server.db.IDBConnection;
import events.SendPost;
import server.model.Server;
import server.model.propagator.ClientsManager;
import server.model.propagator.MessagePropagator;
import server.model.propagator.RealTimePropagator;
import server.model.propagator.RequestPropagator;
import server.model.services.AuthService;
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

	public final static String USER = "alemonta";
	public final static String PWD = "protgamba";
	public final static int PORT = 3306;
	public final static String ADDR = "52.74.20.119";
	public final static String DB_NAME = "extreme01";

	public static void main(String[] args) {

		ChatsManager chatsManager = ChatsManager.getInstance();
		TimersManager timersManager = TimersManager.getInstance();
		ClientsManager clientsManager = ClientsManager.getInstance();
		IDBConnection db = new DBConnection();

		try {
			db.connect(USER, PWD, PORT, ADDR, DB_NAME);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Server server = new Server(clientsManager);

		server.addService(
				JsonParser.TIMER,
				new TimerService(
						chatsManager,
						timersManager,
						new MessagePropagator(ClientsManager.getInstance(),
								new RealTimePropagator()),
						new SendPost(
								"http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(JsonParser.CHAT, new ChatService(chatsManager,
				new MessagePropagator(ClientsManager.getInstance(),
						new RealTimePropagator()), new Logger()));
		server.addService(
				Integer.parseInt(JsonMaker.ADD_TEAM_MEMB),
				new AddTeamMembService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager
						.getInstance(), new RealTimePropagator())));
		server.addService(Integer.parseInt(JsonMaker.NEW_CHAT),
				new NewChatService(ChatsManager.getInstance(),
						new MessagePropagator(ClientsManager.getInstance(),
								new RealTimePropagator())));
		server.addService(
				Integer.parseInt(JsonMaker.NEW_TEAM),
				new NewTeamService(TeamsManager.getInstance(), ChatsManager
						.getInstance(), new MessagePropagator(ClientsManager
						.getInstance(), new RequestPropagator())));
		server.addService(Integer.parseInt(JsonMaker.DISCONNECT),
				new DisconnectService(ClientsManager.getInstance()));
		server.addService(
				Integer.parseInt(JsonMaker.EVENT),
				new EventService(
						new SendPost(
								"http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor")));
		server.addService(Integer.parseInt(JsonMaker.TEAMS),
				new ListOfTeamsService(TeamsManager.getInstance(),
						new MessagePropagator(ClientsManager.getInstance(),
								new RequestPropagator())));
		server.addService(Integer.parseInt(JsonMaker.TEAM_MEMBS),
				new TeamMembsService(TeamsManager.getInstance(),
						new MessagePropagator(ClientsManager.getInstance(),
								new RequestPropagator())));
		server.addService(Integer.parseInt(JsonMaker.AUTH_REQ),
				new AuthService(ClientsManager.getInstance(),
						new MessagePropagator(ClientsManager.getInstance(),
								new RequestPropagator()), db));

		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}

}
