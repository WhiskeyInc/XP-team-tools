package tests;

import server.model.AbstractServer;
import server.model.CacheList;
import server.model.TestableServerRecover;

public class ServerMainRecover {
	public static void main(String[] args) {
		CacheList cache = new CacheList();
		
		AbstractServer server = new TestableServerRecover(cache, cache);
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
