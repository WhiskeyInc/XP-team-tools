package server.events.tests;

import server.model.AbstractServer;

public class SimulationServerMain {
	public static void main(String[] args) {
		AbstractServer server = new SimulationServer();
		
		try {
			server.openPort(9000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
