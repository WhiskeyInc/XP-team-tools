package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;

import server.model.ServerTestable;
import ui.ChatUITestable;
import client.model.Client;

import java.sql.Timestamp;
import java.util.Set;

import server.model.Conversations;

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

	// test pavlo e nicola
	@Test
	public void addNewChatTest() {

		Conversations conversations = new Conversations();

		conversations.addConv("Anus Dei");

		assertTrue(conversations.hasConversations());
	}

	@Test
	public void participantsConvTest() throws Exception {
		Conversations conversations = new Conversations();

		conversations.addConv("Anna", "Cesare");
		for (int i = 0; i < conversations.getConvs().size(); i++) {

			assertEquals("[Anna, Cesare]", conversations.getConvs().get(i)
					.getParticipants().toString());

		}
	}

	@Test
	public void sendMsgTest() throws Exception {
		Conversations conversations = new Conversations();
		int j = 0;

		conversations.addConv("Anna", "Cesare");

		for (int i = 0; i < conversations.getConvs().size(); i++) {

			if (conversations.getConvs().get(i).getParticipants().toString()
					.equalsIgnoreCase("[Anna, Cesare]")) {

				conversations.getConvs().get(i).addMessage("Anna", "ciao");
				j = i;
				i = conversations.getConvs().size();
				// break;
			}
			;
		}

		// String timestamp =
		// server.getConvs().get(j).getConv().keySet().toString();
		Set<Timestamp> timestamp2 = conversations.getConvs().get(j).getConv()
				.keySet();
		System.out.println(timestamp2);// test
		System.out.println(timestamp2.toArray()[0]);// test
		System.out.println(conversations.getConvs().get(j).getConv()
				.get(timestamp2.toArray()[0]).getMessage());

		assertEquals(
				"ciao",
				conversations.getConvs().get(j).getConv()
						.get(timestamp2.toArray()[0]).getMessage());
	}

}
