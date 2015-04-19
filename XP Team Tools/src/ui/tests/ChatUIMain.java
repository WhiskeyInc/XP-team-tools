package ui.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ui.ChatUI;

public class ChatUIMain {
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("ChatUi", 450, 450);
		
	
		final ChatUI chat = new ChatUI();
		
		chat.setButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chat.appendChatAreaText(chat.getMessage());
				chat.emptyMessageArea();
			}
		});
		
		chat.setEnterListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					chat.appendChatAreaText(chat.getMessage());
					chat.emptyMessageArea();
				}
				
			}
		});
		
		frame.getContentPane().add(chat);
		frame.setVisible(true);
	}
}
