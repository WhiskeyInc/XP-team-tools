package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.model.Chat;
import server.model.NoMessagesException;
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
		chat.addAttendant(new ClientDetails("Test", "B", null));
		chat.addAttendant(new ClientDetails("Test1", "A", null));
		
		chat2.addAttendant(new ClientDetails("Test", "B", null));
		chat2.addAttendant(new ClientDetails("Test1", "A", null));
		
		assertTrue(chat.equals(chat2));
		
		Chat chat3 = new Chat("Test");
		assertFalse(chat3.equals(chat2) || chat3.equals(chat));
		
		chat3.addAttendant(new ClientDetails("Test", "B", null));
		chat3.addAttendant(new ClientDetails("Test1", "A", null));
		
		assertFalse(chat3.equals(chat2) || chat3.equals(chat));
		
		Chat chat4 = new Chat("Prova");
		chat4.addAttendant(new ClientDetails("Test4", "B", null));
		chat4.addAttendant(new ClientDetails("Test5", "A", null));
		assertFalse(chat4.equals(chat2) || chat4.equals(chat) || chat4.equals(chat3));

	}
	
	@Test
	public void recoverTest() throws Exception {
		String[] mex1 = new String[2];
		mex1[0] = "Ciao";
		mex1[1] = "Hei";
		chat.addMessage(mex1[0]);
		chat.addMessage(mex1[1]);
		String[] mess = chat.recoverLastMessages(5);
		assertArrayEquals(mex1, mess);
		
		String[] mex2 = new String[4];
		mex2[0] = "Ciao";
		mex2[1] = "Hei";
		mex2[2] = "a";
		mex2[3] = "b";
		
		mess = chat.recoverLastMessages(2);
		assertArrayEquals(mex1, mess);
		
		chat.emptyMessages();
		boolean noMessages = false;
		String[] mes;
		try {
			mes = chat.recoverLastMessages(4);
			assertEquals(0, mes.length);

		} catch (NoMessagesException e) {
			noMessages = true;
		}
		
		assertTrue(noMessages);
	}
	
	@Test
	public void addAttendantTest() throws Exception {
		
		chat.removeAllAttendants();
		chat.addAttendant(new ClientDetails("Test", "B", null));
		chat.addAttendant(new ClientDetails("Test", "B", null));
		assertEquals(1, chat.getAttendantsDetails().size());
		
		chat.addAttendant(new ClientDetails("Test", "C", null));
		assertEquals(1, chat.getAttendantsDetails().size());
		assertEquals("B", chat.getAttendantsDetails().get(0).getTeamName());
		
		chat.addAttendant(new ClientDetails("TEST", "C", null));
		assertEquals(2, chat.getAttendantsDetails().size());


	}
	
	@Test
	public void hasTest() throws Exception {
		
		chat.removeAllAttendants();
		ClientDetails det = new ClientDetails("Test", "B", null);
		assertFalse(chat.has(det));
		
		chat.addAttendant(det);
		assertTrue(chat.has(det));
		
		det = new ClientDetails("Test1", "B", null);
		
		assertFalse(chat.has(det));
		
		det = new ClientDetails("Test", "B1", null);

		assertTrue(chat.has(det));
				
	}

}
