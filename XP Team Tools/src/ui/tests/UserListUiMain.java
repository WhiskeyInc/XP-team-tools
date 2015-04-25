package ui.tests;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.UserListUI;

public class UserListUiMain {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("User UI", 500, 500);
		ArrayList<String> nicknames = new ArrayList<String>();
		nicknames.add("Cesare");
		nicknames.add("Alessia Montanini");
		nicknames.add("Defo");
		nicknames.add("Muro");
		nicknames.add("Fra Nicolai");
		
		
		UserListUI Ui = new UserListUI(nicknames);
		JPanel panel = new JPanel();
		panel.add(Ui);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		
	}

}
