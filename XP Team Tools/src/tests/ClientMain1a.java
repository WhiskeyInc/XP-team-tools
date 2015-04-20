package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import ui.ChatUITestable;
import client.model.Client1;

public class ClientMain1a {
	public static void main(String[] args) {
		final Client1 client = new Client1("IncreMetal", "TeamFere");
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
		
		client.sendMessageToServer("Hi");

		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(chatUI.getMessage());
			}
		});
		chatUI.setMessageText("Striscia Bardo!");
		chatUI.simulateSendButtonClick();

	}
}
