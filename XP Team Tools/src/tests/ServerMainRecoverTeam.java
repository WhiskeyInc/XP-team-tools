package tests;

import server.model.AbstractServer;
import server.model.CacheList;
import server.model.CacheMap;
import server.model.TestableServerRecoverTeam;

public class ServerMainRecoverTeam {
	public static void main(String[] args) {
		CacheMap cache = new CacheMap();
		
		AbstractServer server = new TestableServerRecoverTeam(cache, cache);
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
