package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import server.model.Conversations;

public class ChatTest2 {

	// test pavlo e nicola
//	@Test
//	public void addNewChatTest() {
//
//		Conversations conversations = new Conversations();
//
//		conversations.addConversation("Anus Dei");
//
//		assertTrue(conversations.hasConversations());
//	}

	@Test
	public void participantsConvTest() throws Exception {
		Conversations conversations = new Conversations();

		conversations.addConversation("Anna", "Cesare");
		for (int i = 0; i < conversations.getConvs().size(); i++) {

			assertEquals("[Anna, Cesare]", conversations.getConvs().get(i)
					.getParticipants().toString());

		}
	}

//	@Test
//	public void sendMsgTest() throws Exception {
//		Conversations conversations = new Conversations();
//		int j = 0;
//
//		conversations.addConversation("Anna", "Cesare");
//
//		for (int i = 0; i < conversations.getConvs().size(); i++) {
//
//			if (conversations.getConvs().get(i).getParticipants().toString()
//					.equalsIgnoreCase("[Anna, Cesare]")) {
//
//				conversations.getConvs().get(i).addMessage("Anna", "ciao");
//				j = i;
//				i = conversations.getConvs().size();
//				// break;
//			}
//			;
//		}
//
//		// String timestamp =
//		// server.getConvs().get(j).getConv().keySet().toString();
//		Set<Long> timestamp2 = conversations.getConvs().get(j).getMessages()
//				.keySet();
//		System.out.println(timestamp2);
//		System.out.println(timestamp2.toArray()[0]);
//		System.out.println(conversations.getConvs().get(j).getMessages()
//				.get(timestamp2.toArray()[0]).getMessage());
//
//		assertEquals(
//				"ciao",
//				conversations.getConvs().get(j).getMessages()
//						.get(timestamp2.toArray()[0]).getMessage());
//	}

//	@Test
//	public void addExistingConvTest() throws Exception {
//		Conversations conversations = new Conversations();
//
//		conversations.addConversation("Anna", "Cesare");
//
//		assertEquals("Conv esiste", conversations.addConversation("Anna", "Cesare"));
//	}
}
