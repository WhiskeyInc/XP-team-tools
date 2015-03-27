package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;

import server.model.ServerTestable;
import ui.ChatUITestable;
import client.model.Client;

public class ChatTest2 {

	@Test
	public void clientServerChatTest() throws Exception {
		final Client client = new Client();
		client.openStreams("localhost", 9999);
		final ServerTestable server = new ServerTestable();
		server.openPort(9999);
		
		System.out.println("aa");
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				server.listenClients();

			}
		};

		Thread thread = new Thread(runnable);
		thread.start();

		
		final ChatUITestable chatUI = new ChatUITestable();
		chatUI.setButtonAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(chatUI.getMessage());				
			}
		});
		
		chatUI.setMessageText("Ciao a tutti!");
		chatUI.simulateSendClick();

		
		assertEquals(chatUI.getMessage(), server.getLastMessage());
	}

}
