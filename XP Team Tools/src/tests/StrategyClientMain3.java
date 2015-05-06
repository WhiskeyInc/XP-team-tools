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
import ui.NewChatWorker;
import ui.TimerUIObserverStrategy;
import ui.UserListUI;
import client.model.ClientConnectionDetails;
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
public class StrategyClientMain3 {
	public static void main(String[] args) {
		
		final IClientService[] services = new IClientService[3];
		services[0] = new SetMessageService();
		services[1] = new SetTimeStampService();
		SetMembsService serviceTeamMembs = new SetMembsService();
		services[2] = new SetNewChatService();

	//	IClientService chatIndexService = new ChatIndexService();
//		IClientService confirmService = new ConfirmService();
//		ClientChatIndexManager indexManager = new ClientChatIndexManager(chatIndexService);
		
		final StrategyClient1_1 client = new StrategyClient1_1(new ClientConnectionDetails("Nic", "Prova", "Nic123"));
		client.addService(JsonParser.CHAT, services[0]);
		client.addService(JsonParser.TIMER, services[1]);
		client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX), services[2]);
		client.setMembsService(serviceTeamMembs);

//		client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX), chatIndexService);
//		client.addService(Integer.parseInt(JsonMaker.CONFIRM), confirmService);

		
		
		
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
		
//		
//		client.sendMessageToServer(JsonMaker.newTeamRequest("Prova", "Alb"));
//		System.out.println("StrategyClientMain");
//		final int index;
//		
//		
//		while(indexManager.getIndex() == -1) {
//		}
//		index = indexManager.getIndex();
//		System.err.println("L' indice della chat è : " + index + " ["+ StrategyClient.class + "]");
		final String formattedNickname = Formatter.formatNickname(client.getNickname());
		client.sendMessageToServer(JsonMaker.addTeamMemb(client.getClientDetails()));
		final int index = JsonParser.parseChatIndexRequest(client.waitServerResponse());
		final String indexString = String.valueOf(index);
		client.sendMessageToServer(JsonMaker.chatRequest("- " +client.getNickname() + " added to the team -", ""+index));
		MainUIObserver ui = new MainUIObserver(services, serviceTeamMembs, client, index);
		final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
		final TimerUIObserverStrategy timerUI = ui.getTimerUI();
		final UserListUI listUI = ui.getUserListUI();


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
				NewChatWorker newChatWorker = new NewChatWorker(listUI, client, services);
				newChatWorker.execute();
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

					formattedNickname + chatUI.getMessage(), "" + index));
					chatUI.emptyMessageArea();
					// chat.getMessageArea().setCaretPosition(0);
				}
			}
		});

	}
}
