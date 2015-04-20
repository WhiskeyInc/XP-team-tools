package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import ui.ChatUITestable;
import client.model.Client1;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class ClientMain1b {
	public static void main(String[] args) {
		final Client1 client = new Client1("LuBardo", "TeamFere");
		client.openStreams("localhost", 9999);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					client.readFromSocket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
		client.sendMessageToServer("Vai murotz");

		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(chatUI.getMessage());
			}
		});
		chatUI.setMessageText("Ehehehe");
		chatUI.simulateSendButtonClick();

	}
}
