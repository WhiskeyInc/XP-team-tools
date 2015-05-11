package tests;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUIObserverStrategy1;
import ui.EventCreationController;
import ui.MainUIObserver;
import ui.MeetingUIDetails;
import ui.NewChatWorker;
import ui.TimerUIObserverStrategy;
import ui.UserListUI;
import ui.login.LoginUI;
import ui.login.MainLoginUI;
import ui.login.RegUI;
import client.model.ClientConnectionDetails;
import client.model.IClientService;
import client.model.SessionManager;
import client.model.SetMembsService;
import client.model.SetMessageService;
import client.model.SetNewChatService;
import client.model.SetTimeStampService;
import client.model.StrategyClient1_1;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto, koelio
 *
 */
public class MultipleChatClientMainO {
	public static void main(String[] args) {

		final MainLoginUI ui = new MainLoginUI();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		ui.setLocation((int) (dim.getWidth() - ui.getWidth()) / 2,
				(int) (dim.getHeight() - ui.getHeight()) / 2);
		

		final LoginUI login = ui.getLoginUI();
		final RegUI reg = ui.getRegUI();
		reg.setVisible(false);

		// final MainRegUI regUI = new MainRegUI();
		// final RegUITMP reg = regUI.getRegLoginUI();
		// reg.setVisible(false);

		login.setEnterListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out
							.println("Fai la stessa cosa del setLoginButton ");
					// TODO estrarre tutto il launcher della chat
				}
			}
		});

		login.setLoginListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final IClientService[] services = new IClientService[3];
				services[0] = new SetMessageService();
				services[1] = new SetTimeStampService();
				final SetMembsService serviceTeamMembs = new SetMembsService();
				services[2] = new SetNewChatService();

				final StrategyClient1_1 client = new StrategyClient1_1(
						new ClientConnectionDetails(login.getLoginNick(),
								"Prova", login.getPass()));
				client.addService(JsonParser.CHAT, services[0]);
				client.addService(JsonParser.TIMER, services[1]);
				client.setMembsService(serviceTeamMembs);
				client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX),
						services[2]);
				// client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX),
				// chatIndexService);
				// client.addService(Integer.parseInt(JsonMaker.CONFIRM),
				// confirmService);
				//

				client.openStreams("52.74.20.119", 9999);
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
				client.sendMessageToServer(JsonMaker.newTeamRequest("Prova",
						login.getLoginNick()));
				final int index = JsonParser.parseChatIndexRequest(client
						.waitServerResponse());
				System.err.println("L' indice della chat è : " + index + " ["
						+ StrategyClient1_1.class + "]");
				client.sendMessageToServer(JsonMaker.addTeamMembRequest(client
						.getClientDetails()));
				client.waitServerResponse();
				final String indexString = String.valueOf(index);
				client.sendMessageToServer(JsonMaker.chatRequest(
						"- " + client.getNickname() + " has created "
								+ client.getTeamName() + " -", indexString));
				Runnable runnable2 = new Runnable() {

					@Override
					public void run() {
						MainUIObserver ui = new MainUIObserver(services,
								serviceTeamMembs, client, index);
						/**opens da winda in the the screen center
						 * 
						 */
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						ui.setLocation((int) (dim.getWidth() - ui.getWidth()) / 2,
								(int) (dim.getHeight() - ui.getHeight()) / 2);

						System.err.println(EventQueue.isDispatchThread() + " "
								+ MultipleChatClientMainO.class);
						final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
						final TimerUIObserverStrategy timerUI = ui.getTimerUI();
						final UserListUI listUI = ui.getUserListUI();
						final String teamName = client.getTeamName();
						ui.setChatUI(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								client.sendMessageToServer(JsonMaker
										.chatRequest(teamName,
												client.getNickname()));
								chatUI.emptyMessageArea();
							}
						});
						final String formattedNickname = Formatter
								.formatNickname(client.getNickname());

						ui.setTimerUI(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (timerUI.isTimeStampValid(timerUI
										.getTimeStamp())) {
									int[] time = TimerFormatter
											.getMinSec(timerUI.getTimeStamp());
									timerUI.setTimerEditable(false);// TODO se è
																	// connesso...
									client.sendMessageToServer(JsonMaker
											.timerRequest(indexString, time[0],
													time[1]));
								}
							}
						});

						ui.setUserListUi(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								SessionManager sessionManager = SessionManager
										.getInstance();
								if (sessionManager.hasChat(index)) {
									if (!sessionManager.isChatOpen(index)) {
										NewChatWorker newChatWorker = new NewChatWorker(
												listUI, client, services);
										newChatWorker.execute();
									} else {
										listUI.deselectAll();
									}
								} else {
									sessionManager.registerChatOpening(index);
									NewChatWorker newChatWorker = new NewChatWorker(
											listUI, client, services);
									newChatWorker.execute();
								}

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

									formattedNickname + chatUI.getMessage(), ""
											+ index));
									chatUI.emptyMessageArea();
									// chat.getMessageArea().setCaretPosition(0);
								}
							}
						});

						ui.setMeetingButtonAction(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								final MeetingUIDetails ask = new MeetingUIDetails();
								EventCreationController eventContr = new EventCreationController(
										ask, client);
								ask.setCreateButtonListener(eventContr);
							}
						});
					}
				};

				SwingUtilities.invokeLater(runnable2);

				ui.dispose();
				
			}
		});

		login.setRegisterListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				login.setVisible(false);
				reg.setVisible(true);
				ui.refresh();
			}
		});

		reg.setBackLoginListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				reg.setVisible(false);
				login.setVisible(true);
				ui.refresh();
			}
		});

	}
}
