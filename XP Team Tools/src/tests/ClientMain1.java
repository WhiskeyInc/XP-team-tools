package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import string.formatter.NewLineMaker;
import ui.ChatUITestable;
import client.model.Client;

public class ClientMain1 {
	public static void main(String[] args) {
		final Client client = new Client("IncreMetal");
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
		
		client.sendMessageToServer(NewLineMaker.appendNewLine("Hi"));

		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(chatUI.getMessage());
			}
		});
		chatUI.setMessageText(NewLineMaker.appendNewLine("Striscia Bardo!"));
		chatUI.simulateSendClick();

	}
}
