package ui.tests;

import javax.swing.JFrame;

import ui.ChatUI;

public class ChatUIMain {
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("ChatUi", 450, 450);
		frame.getContentPane().add(new ChatUI());
		frame.setVisible(true);
		
		
	}
}
