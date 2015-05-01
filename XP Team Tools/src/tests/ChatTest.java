package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.model.Chat;
import client.model.ClientDetails;

public class ChatTest {

	private Chat chat;
	private Chat chat2;
	
	 @Before
	 public void setUp() {
		chat = new Chat("Prova");
		chat2 = new Chat("Prova");

	 }
	 

	
	@Test
	public void equalsTest() throws Exception {
		chat.addAttendant(new ClientDetails("Test", "B"));
		chat.addAttendant(new ClientDetails("Test1", "A"));
		
		chat2.addAttendant(new ClientDetails("Test", "B"));
		chat2.addAttendant(new ClientDetails("Test1", "A"));
		
		assertTrue(chat.equals(chat2));
	}

}
