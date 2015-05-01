package ui.tests;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.Test;

import ui.ChatUITestable;

public class ChatUiTest {

	@Test
	public void testMessage() {
		ChatUITestable chat = new ChatUITestable();
		chat.setMessageText("Test");
		assertEquals("Test", chat.getMessage());
	}

	@Test
	public void testChatArea() throws Exception {
		ChatUITestable chat = new ChatUITestable();
		chat.setChatAreaText("Test");
		System.out.println(chat.getChatAreaText());
		assertEquals(chat.getChatAreaText(), "Test");
	}
	@Test
	public void testSendButton() throws Exception {
		final ChatUITestable chat = new ChatUITestable();
		chat.setButtonAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chat.setChatAreaText("TestButton");
				
			}
		});
		
		chat.simulateSendButtonClick();
		
		System.out.println(chat.getChatAreaText());

		assertEquals(chat.getChatAreaText(), "TestButton");
	}
	
	
}
