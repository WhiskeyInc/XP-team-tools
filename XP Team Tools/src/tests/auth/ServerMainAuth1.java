package tests.auth;

import server.db.DBConnection;
import server.db.IDBConnection;
import server.model.CacheMap;
import server.model.ChatServiceAuth;
import server.model.ClientsManagerAuth;
import server.model.ServerStrategyAuth;
import server.model.TimerServiceAuth;
import server.utils.Logger;

public class ServerMainAuth1 {
	public static void main(String[] args) {

		CacheMap cache = new CacheMap(new Logger());
		IDBConnection db = new DBConnection();

		try {
			db.connect("root", "", 3307, "localhost", "extreme01");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientsManagerAuth clientsManager = new ClientsManagerAuth(cache, db);

		ServerStrategyAuth server = new ServerStrategyAuth(clientsManager);

		server.addService(JsonParser.TIMER,
				new TimerServiceAuth(clientsManager));
		server.addService(JsonParser.CHAT, new ChatServiceAuth(clientsManager,
				cache));

		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
}
