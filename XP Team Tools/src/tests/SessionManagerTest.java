package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.model.SessionManager;

public class SessionManagerTest {
	
	@Test
	public void chatHandlingTest() {
		SessionManager sessionManager = SessionManager.getInstance();
		sessionManager.registerChatOpening(0);
		assertTrue(sessionManager.isChatOpen(0));
		assertTrue(sessionManager.hasChat(0));
		sessionManager.registerChatOpening(0);
		assertTrue(sessionManager.isChatOpen(0));
		
		sessionManager.setChatClosed(0);
		assertFalse(sessionManager.isChatOpen(0));
		
	}

}
