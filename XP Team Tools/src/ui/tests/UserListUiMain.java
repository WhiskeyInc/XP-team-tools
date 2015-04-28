package ui.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		
		final UserListUI Ui = new UserListUI(nicknames);
		JPanel panel = new JPanel();
		panel.add(Ui);
		Ui.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Ui.getSelectedNicknames().clear();
				for (int i = 0; i < Ui.getBox().size(); i++) {
					if (Ui.getBox().get(i).isSelected()) {
						Ui.getSelectedNicknames().add(Ui.getNicknames().get(i));
					}
				}
				
				
				System.out.println("Gli utenti selezionati sono "+Ui.getSelectedNicknames());
			}
		});
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		
	}

}
