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
import ui.EventCreationController;
import ui.MainUIObserver;
import ui.MeetingUIDetails;
import ui.TimerUIObserverStrategy;
import ui.UIObserverStrategy1;
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

		final IClientService[] services = new IClientService[3];
		services[0] = new SetMessageService();
		services[1] = new SetTimeStampService();
		SetMembsService serviceTeamMembs = new SetMembsService();
		services[2] = new SetNewChatService();
		// IClientService chatIndexService = new ChatIndexService();
		// IClientService confirmService = new ConfirmService();
		// ClientChatIndexManager indexManager = new
		// ClientChatIndexManager(chatIndexService);
		final StrategyClient1_1 client = new StrategyClient1_1(
				new ClientConnectionDetails("Alb", "Prova"));
		client.addService(JsonParser.CHAT, services[0]);
		client.addService(JsonParser.TIMER, services[1]);
		client.setMembsService(serviceTeamMembs);
		client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX), services[2]);
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
		client.sendMessageToServer(JsonMaker.addTeamMemb(client
				.getClientDetails()));
		client
		.waitServerResponse();
		final String indexString = String.valueOf(index);
		client.sendMessageToServer(JsonMaker.chatRequest("- "+ client.getNickname() + " has created "+ client.getTeamName()+" -", indexString));

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
		final String formattedNickname = Formatter.formatNickname(client.getNickname());

		
		ui.setMeetingButtonAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final MeetingUIDetails ask = new MeetingUIDetails();
				EventCreationController eventContr = new EventCreationController(ask);
				ask.setCreateButtonListener(eventContr);
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
				listUI.getListOfSelectedNicknames().clear();
				for (int i = 0; i < listUI.getBox().size(); i++) {
					if (listUI.getBox().get(i).isSelected()) {
						listUI.getListOfSelectedNicknames().add(listUI.getNicknames()[i]);
					}
				}
				//NB: il primo è sempre chi la richiede
				ClientDetails[] det = new ClientDetails[listUI.getListOfSelectedNicknames().size() + 1];
				det[0] = new ClientDetails(client.getNickname(), teamName);
				for (int i = 1; i < det.length; i++) {
					det[i] = new ClientDetails(listUI.getSelectedNicknames()[i-1], teamName);
				}
				client.sendMessageToServer(JsonMaker.newChatRequest(det));
				String response = client.waitServerResponse();

				final int index = JsonParser.parseChatIndexRequest(response);
				System.err.println(index+ " "+ MultipleChatClientMain.class);
				Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					final String nickname = Formatter.formatNickname(client.getNickname());
//					IClientService serviceMessage = new SetMessageService();
//					IClientService serviceTimeStamp = new SetTimeStampService();
					UIObserverStrategy1 ui = new UIObserverStrategy1(services[0], services[1], client, index);
					final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
					final TimerUIObserverStrategy timerUI = ui.getTimerUI();
					
					client.sendMessageToServer(JsonMaker.chatRequest("- " +client.getNickname() + " added to the chat -", ""+index));
					
					System.err.println("L' indice della chat è : " + index + " ["+ MainUIObserver.class + "]");
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
										time[0], time[1]));
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
			};
			Thread thread = new Thread(runnable);
			thread.start();

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
