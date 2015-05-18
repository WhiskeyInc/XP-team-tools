package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.model.recover.Chat;
import server.model.services.chat.ChatsManager;
import client.model.ClientDetails;

public class ChatsManagerTest {

	private ChatsManager chatsManager;
	
	@Before
	public void setUp() {
		chatsManager = ChatsManager.getInstance();
	}
	
	
	@Test
	public void addChatTest() throws Exception {
		Chat chat = new Chat("Prova");
		chatsManager.add(chat);
		assertEquals(chatsManager.size(), 1);
		
		chatsManager.add(chat);
		assertEquals(chatsManager.size(), 1);

		chat.addAttendant(new ClientDetails("Bo", "Test"));
		assertEquals(chatsManager.size(), 1);
		
	}
	
	@Test
	public void hasTest() throws Exception {
		chatsManager.removeAll();
		assertEquals(chatsManager.size(), 0);
		Chat chat = new Chat("Prova");
		chatsManager.add(chat);
		assertTrue(chatsManager.has(chat));
		Chat chat1 = new Chat("Prova");
		assertTrue(chatsManager.has(chat1));
		chat1.addAttendant(new ClientDetails("F", "f"));
		assertFalse(chatsManager.has(chat1));

	}

}
