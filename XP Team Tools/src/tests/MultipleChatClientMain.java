package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUIObserverStrategy1;
import ui.MainUIObserver;
import ui.TimerUIObserverStrategy;
import ui.UserListUI;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;
import client.model.IClientService;
import client.model.SetMembsService;
import client.model.SetMessageService;
import client.model.SetNewChatService;
import client.model.SetTimeStampService;
import client.model.StrategyClient1_1;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class MultipleChatClientMain {
	public static void main(String[] args) {

		IClientService serviceMessage = new SetMessageService();
		IClientService serviceTimeStamp = new SetTimeStampService();
		SetMembsService serviceTeamMembs = new SetMembsService();
		SetNewChatService serviceNewChat = new SetNewChatService();
		// IClientService chatIndexService = new ChatIndexService();
		// IClientService confirmService = new ConfirmService();
		// ClientChatIndexManager indexManager = new
		// ClientChatIndexManager(chatIndexService);
		final StrategyClient1_1 client = new StrategyClient1_1(
				new ClientConnectionDetails("Alb", "Prova"));
		client.addService(JsonParser.CHAT, serviceMessage);
		client.addService(JsonParser.TIMER, serviceTimeStamp);
		client.addService(Integer.parseInt(JsonMaker.TEAM_MEMBS), serviceTeamMembs);
		client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX), serviceNewChat);
		// client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX),
		// chatIndexService);
		// client.addService(Integer.parseInt(JsonMaker.CONFIRM),
		// confirmService);
		//

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
		client.sendMessageToServer(JsonMaker.newTeamRequest("Prova", "Alb"));
		final int index = JsonParser.parseChatIndexRequest(client
				.waitServerResponse());
		System.err.println("L' indice della chat è : " + index + " ["
				+ StrategyClient1_1.class + "]");
		MainUIObserver ui = new MainUIObserver(serviceMessage,
				serviceTimeStamp, serviceTeamMembs, serviceNewChat, client);
		final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
		final TimerUIObserverStrategy timerUI = ui.getTimerUI();
		final UserListUI listUI = ui.getUserListUI();

		client.sendMessageToServer(JsonMaker.addTeamMemb(client
				.getClientDetails()));
		// ClientDetails detail = new ClientDetails("Alb", "Prova");
		// client.sendMessageToServer(JsonMaker.newChatRequest(detail));

		final String teamName = client.getTeamName();
		ui.setChatUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(teamName,
						client.getNickname()));
				chatUI.emptyMessageArea();
			}
		});
		final String indexString = String.valueOf(index);
		final String nickname = Formatter.formatNickname(client.getNickname());
		client.sendMessageToServer(JsonMaker.chatRequest("- "+ client.getNickname() + " has created "+ client.getTeamName()+" -", indexString));

		ui.setTimerUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timerUI.isTimeStampValid(timerUI.getTimeStamp())) {
					int[] time = TimerFormatter.getMinSec(timerUI
							.getTimeStamp());
					timerUI.setTimerEditable(false);// TODO se è connesso...
					client.sendMessageToServer(JsonMaker.timerRequest(indexString,
							time[0], time[1]));
				}
			}
		});

		ui.setUserListUi(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("//////////////////// " + MultipleChatClientMain.class);
				listUI.getListOfSelectedNicknames().clear();
				for (int i = 0; i < listUI.getBox().size(); i++) {
					if (listUI.getBox().get(i).isSelected()) {
						listUI.getListOfSelectedNicknames().add(listUI.getNicknames()[i]);
					}
				}
				ClientDetails[] det = new ClientDetails[listUI.getListOfSelectedNicknames().size()];
				for (int i = 0; i < det.length; i++) {
					det[i] = new ClientDetails(listUI.getSelectedNicknames()[i], client.getTeamName());
				}
				client.sendMessageToServer(JsonMaker.newChatRequest(det));
			}
		});
		
		chatUI.setEnterListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					client.sendMessageToServer(JsonMaker.chatRequest(

					nickname + chatUI.getMessage(), "" + index));
					chatUI.emptyMessageArea();
					// chat.getMessageArea().setCaretPosition(0);
				}
			}
		});

	}
}
