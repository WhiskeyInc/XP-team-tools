package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.model.AbstractServer;
import server.model.ServerTest;
import client.model.Client;

public class ChatTest1 {

	@Test
	public void receptionMessageTest1() {
		
		ServerTest server = new ServerTest();

		assertEquals("Ciao", server.getLastMessage());
	}
	
	@Test
	public void sendReceptionTest() throws Exception {
		
		Client client = new Client();
		ServerTest server = new ServerTest();

		
		assertEquals(client.sendMessage("Ciao"), server.getLastMessage());
	}
	
	@Test
	public void openPortTest() throws Exception {
		AbstractServer server = new ServerTest();
		
		server.openPort(9999);
		assertTrue(!server.isPortClosed());
		//Deve chiudersi, altrimenti per il prossimo test resta aperta
		server.closePort();

	}
	
	@Test
	public void closePortTest() throws Exception {
	
		AbstractServer server = new ServerTest();
		server.openPort(9999);
		server.closePort();
		assertTrue(server.isPortClosed());
	}
	
	

}
