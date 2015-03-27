package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;

import server.model.AbstractServer;
import server.model.ServerTestable;
import ui.ChatUI;
import ui.ChatUITestable;
import client.model.Client;

public class ChatTest1 {

//	@Test
//	public void receptionMessageTest1() {
//		
//		ServerTestable server = new ServerTestable();
//
//		assertEquals("Ciao", server.getLastMessage());
//	}
//	
//	@Test
//	public void sendReceptionTest() throws Exception {
//		
//		Client client = new Client();
//		ServerTestable server = new ServerTestable();
//
//		
//		assertEquals(client.sendMessage("Ciao"), server.getLastMessage());
//	}
	
	@Test
	public void openPortTest() throws Exception {
		AbstractServer server = new ServerTestable();
		
		server.openPort(9999);
		assertTrue(!server.isPortClosed());
		//Deve chiudersi, altrimenti per il prossimo test resta aperta
		server.closePort();

	}
	
	@Test
	public void closePortTest() throws Exception {
	
		AbstractServer server = new ServerTestable();
		server.openPort(9999);
		server.closePort();
		assertTrue(server.isPortClosed());
	}

}
