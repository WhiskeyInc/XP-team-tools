package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;
import client.model.Client;
import client.model.ClientDetails;
import client.model.MacroEvents;
import client.model.SessionManager;
import client.model.service.IClientService;
import events.SendPost;

/**
 * A class that launches the UI after the user has selected the new private chat in @TeamListUI
 * It extends {@link SwingWorker} SwingWorker class. In this way the EDT takes care only of the
 * Swing 's elements
 * 
 * @author alberto
 *
 */
public class NewChatWorker extends SwingWorker<Integer, Void>{

	private UserListUI listUI;
	private Client client;
	private IClientService[] services;
	
	public NewChatWorker(UserListUI listUI, Client client,
			IClientService[] services) {
		super();
		this.listUI = listUI;
		this.client = client;
		this.services = services;
	}

	//NB: lavora in un thread diverso dall EDT
	@Override
	protected Integer doInBackground() throws Exception {
		String teamName = client.getTeamName();
		listUI.getListOfSelectedNicknames().clear();
		for (int i = 0; i < listUI.getLabels().size(); i++) {
			if (listUI.getLabels().get(i).getForeground().equals(Color.BLUE)) {
				listUI.getListOfSelectedNicknames().add(
						listUI.getNicknames()[i]);
			}
		}
		listUI.deselectAll();
		// NB: il primo è sempre chi la richiede
		ClientDetails[] det = new ClientDetails[listUI
				.getListOfSelectedNicknames().size() + 1];
		det[0] = new ClientDetails(client.getNickname(),
				teamName);
		for (int i = 1; i < det.length; i++) {
			det[i] = new ClientDetails(listUI
					.getSelectedNicknames()[i - 1], teamName);
		}
		client.sendMessageToServer(JsonMaker
				.newChatRequest(det));
		String response = client.waitServerResponse();

		int index = JsonParser
				.parseChatIndexRequest(response);		

		
		return index;
	}
	
	@Override
	protected void done() {
		try {
			final int index = get();

			SessionManager sessionManager = SessionManager
					.getInstance();
			if (sessionManager.hasChat(index)) {
				if (!sessionManager.isChatOpen(index)) {
					launchUI(index);
					sessionManager.registerChatOpening(index);
				} else {
					SessionManager.getInstance().emptyChatUI(index);

					sessionManager.requireFocusForUIAt(index);
					listUI.deselectAll();
				}
			} else {
				sessionManager
						.registerChatOpening(index);
				launchUI(index);
				sessionManager.registerChatOpening(index);

			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
		// };
		// Thread thread = new Thread(runnable);
		// thread.start();

	}

	private void launchUI(final int index) {
		final String nickname = Formatter.formatNickname(client
				.getNickname());
		
		
		// looks for the list of macro events
		SendPost sender = new SendPost("http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor");
		String message = JsonMaker.requestMacroEventsList("admin");
					
		String answer = sender.sendJson(message);
		
		//TODO test the request for the events to the other server
		//TODO remove later fake answer:
		
		answer = "{\"request\": \"14\",\"action\": \"macro_event_response\",\"user\": \"admin\",\"ids\": [\"001\",\"002\",\"003\"],\"names\": [\"Incre programma tutto il dì\",\"Ciao LELE\",\"Martin fera\"]}";
		
		final MacroEvents events = JsonParser.parseMacroEventsResponse(answer);

		
		PrivateChaTimerUi ui = new PrivateChaTimerUi(
				services[0], services[1], client, index, events);
		ui.requestFocus();
		ui.toFront();
		SessionManager.getInstance().registerUI(index, ui);
		final ChatUI chatUI = ui.getChatUI();
		final TimerUI timerUI = ui.getTimerUI();
//			client.sendMessageToServer(JsonMaker.chatRequest(
//					"- " + client.getNickname()
//							+ " added to the chat -", "" + index));

		

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
		
		
		final String indexString = String.valueOf(index);

		ui.setTimerUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timerUI.isTimeStampValid(timerUI
						.getTimeStamp())) {
					int[] time = TimerFormatter
							.getMinSec(timerUI.getTimeStamp());
					timerUI.setTimerEditable(false);// TODO se è
													// connesso...
					
					
					client.sendMessageToServer(JsonMaker.teamMembsRequest(client.getNickname(), client.getTeamName()));
					String jsonMembs = client.waitServerResponse();
					
					String[] membs;
					ArrayList<String> participants = new ArrayList<String>();
					try {
						membs = JsonParser.parseMakeTeamMembs(jsonMembs);
						for (int i = 0; i < membs.length; i++) {
							participants.add(membs[i]);
						}
						// add myself member
						participants.add(client.getNickname());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					timerUI.setChoisesComboEnabled(false);
					
					String chosenId = events.getIdFromName(timerUI.getChosenCombo());
					
					client.sendMessageToServer(JsonMaker.timerRequest(
							indexString, time[0], time[1], participants, chosenId));
					
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
					client.sendMessageToServer(JsonMaker
							.chatRequest(

							nickname + chatUI.getMessage(), ""
									+ index));
					chatUI.emptyMessageArea();
					// chat.getMessageArea().setCaretPosition(0);
				}
			}
		});
	}

}
