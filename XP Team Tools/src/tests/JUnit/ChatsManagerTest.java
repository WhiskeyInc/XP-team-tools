package tests.JUnit;

import static org.junit.Assert.*;

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
		chatsManager.removeAll();
		Chat chat = new Chat("Prova");
		chatsManager.add(chat);
		assertEquals(1, chatsManager.size());
		
		chatsManager.add(chat);
		assertEquals(1, chatsManager.size());

		chat.addAttendant(new ClientDetails("Bo", "Test"));
		assertEquals(1, chatsManager.size());
		
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
	
	@Test
	public void getTest() throws Exception {
		chatsManager.removeAll();
		Chat chat = new Chat("Test");
		chatsManager.add(chat);
		int index = chatsManager.indexOf(chat);
		assertEquals(0, index);
		assertEquals(chat, chatsManager.get(index));
		
		
	}

}
