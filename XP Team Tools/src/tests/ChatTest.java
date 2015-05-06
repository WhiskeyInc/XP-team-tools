package tests;

import static org.junit.Assert.*;

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

	}

}
