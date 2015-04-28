package tests;

import server.model.AbstractServer;
import server.model.CacheMap;
import server.model.JsonServer;
import server.utils.Logger;

public class JsonServerMain {
	public static void main(String[] args) {
		CacheMap cache = new CacheMap(new Logger());
		
		AbstractServer server = new JsonServer(cache, cache);
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
