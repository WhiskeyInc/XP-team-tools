package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import tests.MultipleChatClientMainO;
import timer.TimerFormatter;
import client.model.ClientDetails;
import client.model.IClientService;
import client.model.StrategyClient1_1;

public class NewChatWorker extends SwingWorker<Integer, Void>{

	private UserListUI listUI;
	private StrategyClient1_1 client;
	private IClientService[] services;
	
	
	public NewChatWorker(UserListUI listUI, StrategyClient1_1 client,
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
		System.err.println(index + " "
				+ MultipleChatClientMainO.class);
		
		return index;
	}
	
	@Override
	protected void done() {
		try {
			final int index = get();
			final String nickname = Formatter.formatNickname(client
					.getNickname());
			UIObserverStrategy1 ui = new UIObserverStrategy1(
					services[0], services[1], client, index);
			System.err.println("Ora Ho appena aperto la ui " + System.currentTimeMillis());
			final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
			final TimerUIObserverStrategy timerUI = ui.getTimerUI();
//			client.sendMessageToServer(JsonMaker.chatRequest(
//					"- " + client.getNickname()
//							+ " added to the chat -", "" + index));

			System.err.println("L' indice della chat è : " + index
					+ " [" + NewChatWorker.class + "]");
			// Controlla conferma data dal server in caso è fallito
			// l'add...

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
						client.sendMessageToServer(JsonMaker
								.timerRequest(indexString, time[0],
										time[1]));
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

}
