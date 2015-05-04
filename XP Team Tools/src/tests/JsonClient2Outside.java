package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.ChatUITestable;
import client.model.Client2;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class JsonClient2Outside {
	public static void main(String[] args) {
		final Client2 client = new Client2("LuBardo", "TeamFere");
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

		Thread thread = new Thread(runnable);
		thread.start();
		final String teamName = client.getTeamName();

		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		//		client.sendMessageToServer(JsonMaker.newChatRequest(
	//					teamName, (String[]) null));
			}
		});
		JFrame frame = new JFrame();
		frame.getContentPane().add(chatUI);
		frame.setVisible(true);

	}
}
