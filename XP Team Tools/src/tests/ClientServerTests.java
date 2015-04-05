package tests;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;

import server.model.CacheList;
import server.model.TestableServerRecover;
import string.formatter.Formatter;
import ui.ChatUITestable;
import client.model.Client;

public class ClientServerTests {

	@Test
	public void clientServerChatTest() throws Exception {
		final TestableServerRecover server = new TestableServerRecover(new CacheList());
		server.openPort(9999);
		final Client client = new Client();
		client.openStreams("localhost", 9999);

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

		chatUI.setMessageText(Formatter.appendNewLine("Ciao a tutti!"));
		chatUI.simulateSendClick();
		waitTCPSending(server);

		assertEquals(chatUI.getMessage(), Formatter.appendNewLine(server.getLastMessage()));
	}


	private void waitTCPSending(final TestableServerRecover server) {
		boolean notArrived = true;
		while(notArrived)
		try {
			server.getLastMessage();
			notArrived = false;
		} catch (Exception e) {

		}
	}
}
