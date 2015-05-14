package ui.tests;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.FramesUtils;
import ui.UserListUI;


public class UserListUiMain {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("User UI", 500, 500);
		String[] nicknames = new String[5];
		nicknames[0] = "Cesare";
		nicknames[1] = "Alessia Montanini";
		nicknames[2] = "Defo";
		nicknames[3] = "Muro";
		nicknames[4] = "Fra Nicolai";
		
		
		final UserListUI Ui = new UserListUI(nicknames);
		JPanel panel = new JPanel();
		panel.add(Ui);
		Ui.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Ui.getListOfSelectedNicknames().clear();
				for (int i = 0; i < Ui.getLabels().size(); i++) {
					if (Ui.getLabels().get(i).getForeground().equals(Color.BLUE)) {
						Ui.getListOfSelectedNicknames().add(Ui.getNicknames()[i]);
					}
				}
				
				
				System.out.println("Gli utenti selezionati sono "+Ui.getListOfSelectedNicknames());
			}
		});
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		
	}

}
