package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import string.formatter.Formatter;
import ui.ChatUITestable;
import client.model.Client2;
import client.model.JsonMaker;

public class JsonClient2a {
	public static void main(String[] args) {
		final Client2 client = new Client2("IncreMetal", "TeamFere");
		client.openStreams("localhost", 9999);
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
		client.sendMessageToServer(JsonMaker.chatRequest(teamName, Formatter.formatNickname(client.getNickname()) + "Hi"));

		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(teamName, Formatter.formatNickname(client.getNickname()) + chatUI.getMessage()));
			}
		});
		chatUI.setMessageText("Striscia Bardo!");
		chatUI.simulateSendButtonClick();

	}
}
