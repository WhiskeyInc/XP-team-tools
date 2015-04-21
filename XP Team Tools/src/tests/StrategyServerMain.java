package tests;

import server.model.CacheMap;
import server.model.ChatService;
import server.model.ClientsManager;
import server.model.JsonParser;
import server.model.ServerStrategy;
import server.model.TimerService;

public class StrategyServerMain {
	public static void main(String[] args) {
		
		CacheMap cache = new CacheMap();
		
		ClientsManager clientsManager = new ClientsManager(cache);
		
		ServerStrategy server = new ServerStrategy(clientsManager);
		
		server.addService(JsonParser.TIMER, new TimerService(clientsManager));
		server.addService(JsonParser.CHAT, new ChatService(clientsManager, cache));
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
