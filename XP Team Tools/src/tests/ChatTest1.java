package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.Server;

public class ChatTest1 {

	@Test
	public void receptionMessageTest1() {
		
		Server server = new Server();

		assertEquals("Ciao", server.getLastMessage());
	}
	
	

}
