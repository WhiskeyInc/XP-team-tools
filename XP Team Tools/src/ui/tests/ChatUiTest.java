package ui.tests;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	
//	@Test
//	public void testSendEnter() throws Exception {
//		final ChatUITestable chat = new ChatUITestable();
//		chat.setEnterListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {}
//			@Override
//			public void keyReleased(KeyEvent e) {}
//			@Override
//			public void keyPressed(KeyEvent e) {
//				
//				if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					chat.appendChatAreaText("TestEnter");
//				}
//				
//			}
//		});
//		
//		chat.simulateSendEnterClick();
//
//		System.out.println(chat.getChatAreaText());
//		
//		assertEquals(chat.getChatAreaText(), "TestEnter");
//	}
	
}
