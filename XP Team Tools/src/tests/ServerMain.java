package tests;

import server.model.AbstractServer;
import server.model.ServerTest;

public class ServerMain {
	public static void main(String[] args) {
		AbstractServer server = new ServerTest();
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
