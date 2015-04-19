package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import string.formatter.Formatter;
import ui.ChatUITestable;
import ui.TimerUIA;
import client.model.AbstractClient;
import client.model.ConcreteClient;
import client.model.JsonMaker;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class JsonConcreteClientMain {
	public static void main(String[] args) {
		

		final ChatUITestable chatUI = new ChatUITestable();
		
		
		final AbstractClient client = new ConcreteClient("Alb", "TeamFere", chatUI, new TimerUIA());
		client.openStreams("koelio.no-ip.org", 9999);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					client.readFromSocket();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		final String teamName = client.getTeamName();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(teamName, Formatter.formatNickname(client.getNickname()) + chatUI.getMessage()));
				chatUI.setMessageText(null);
			}
		});

		Thread thread = new Thread(runnable);
		thread.start();
		
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.getContentPane().add(chatUI);
		frame.setVisible(true);

	}
}