package tests.convmanager;

import server.model.AbstractServer;
import server.model.ConversationGenerator;
import server.model.TestableServerNoRecover;

public class ServerMainConvManager {
	public static void main(String[] args) {
		
		
		AbstractServer server = new TestableServerNoRecover(new ConversationGenerator());
		
		try {
			server.openPort(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.listenClients();
	}
	
}
