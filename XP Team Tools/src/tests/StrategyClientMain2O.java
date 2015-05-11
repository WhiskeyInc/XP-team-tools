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
import ui.TimerUIObserverStrategy;
import ui.UIObserverStrategy1;
import client.model.ClientConnectionDetails;
import client.model.IClientService;
import client.model.SetMembsService;
import client.model.SetMessageService;
import client.model.SetTimeStampService;
import client.model.StrategyClient1_1;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class StrategyClientMain2O {
	public static void main(String[] args) {
		
		IClientService serviceMessage = new SetMessageService();
		IClientService serviceTimeStamp = new SetTimeStampService();
		SetMembsService serviceTeamMembs = new SetMembsService();
		
	//	IClientService chatIndexService = new ChatIndexService();
//		IClientService confirmService = new ConfirmService();
//		ClientChatIndexManager indexManager = new ClientChatIndexManager(chatIndexService);
		
		final StrategyClient1_1 client = new StrategyClient1_1(new ClientConnectionDetails("Nic", "Prova", "Nic123"));
		client.addService(JsonParser.CHAT, serviceMessage);
		client.addService(JsonParser.TIMER, serviceTimeStamp);
		//client.setMembsService(serviceTeamMembs);

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
		final String nickname = Formatter.formatNickname(client.getNickname());
		client.sendMessageToServer(JsonMaker.addTeamMembRequest(client.getClientDetails()));
		final int index = JsonParser.parseChatIndexRequest(client.waitServerResponse());
		client.sendMessageToServer(JsonMaker.chatRequest("- " +client.getNickname() + " added to the team -", ""+index));
		UIObserverStrategy1 ui = new UIObserverStrategy1(serviceMessage, serviceTimeStamp, client, index);
		final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
		final TimerUIObserverStrategy timerUI = ui.getTimerUI();
		
		
		
		
		System.err.println("L' indice della chat è : " + index + " ["+ StrategyClient1_1.class + "]");
		//Controlla conferma data dal server in caso è fallito l'add...
		

		
		final String teamName = client.getTeamName();
		ui.setChatUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(
						teamName,
						client.getNickname()));
				chatUI.emptyMessageArea();
			}
		});
		
		
		final String indexString = String.valueOf(index);
		
		ui.setTimerUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timerUI.isTimeStampValid(timerUI.getTimeStamp())) {
					int[] time = TimerFormatter.getMinSec(timerUI
							.getTimeStamp());
					timerUI.setTimerEditable(false);// TODO se è connesso...
					client.sendMessageToServer(JsonMaker.timerRequest(indexString,
							time[0], time[1], null));
				}
			}
		});

		chatUI.setEnterListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					client.sendMessageToServer(JsonMaker.chatRequest(
							
							nickname + chatUI.getMessage()	, ""+index));
					chatUI.emptyMessageArea();
					//chat.getMessageArea().setCaretPosition(0);
				}
			}
		});


	}
}
