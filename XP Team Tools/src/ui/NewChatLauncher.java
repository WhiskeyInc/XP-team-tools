package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import protocol.JsonMaker;
import string.formatter.Formatter;
import timer.TimerFormatter;
import client.model.IClientService;
import client.model.MessageObservable;
import client.model.StrategyClient1_1;

public class NewChatLauncher implements Observer {

	private StrategyClient1_1 client;
	private IClientService serviceMessage;
	private IClientService serviceTimeStamp;
	private MessageObservable mObs;

	public NewChatLauncher(IClientService newChat, StrategyClient1_1 client,
			IClientService serviceMessage, IClientService serviceTimeStamp) {
		super();
		this.client = client;
		this.serviceMessage = serviceMessage;
		this.serviceTimeStamp = serviceTimeStamp;
		mObs = (MessageObservable) newChat.getAttribute(0);
		mObs.addObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		final int index = Integer.parseInt(mObs.getMessage());
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				final String nickname = Formatter.formatNickname(client
						.getNickname());
				// IClientService serviceMessage = new SetMessageService();
				// IClientService serviceTimeStamp = new SetTimeStampService();
				System.err.println(EventQueue.isDispatchThread() + " " + NewChatLauncher.class);

				UIObserverStrategy1 ui = new UIObserverStrategy1(
						serviceMessage, serviceTimeStamp, client, index);
				final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
				final TimerUIObserverStrategy timerUI = ui.getTimerUI();

				client.sendMessageToServer(JsonMaker.chatRequest(
						"- " + client.getNickname() + " added to the chat -",
						"" + index));

				System.err.println("L' indice della chat è : " + index + " ["
						+ MainUIObserver.class + "]");
				// Controlla conferma data dal server in caso è fallito l'add...

				final String teamName = client.getTeamName();
				ui.setChatUI(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						client.sendMessageToServer(JsonMaker.chatRequest(
								teamName, client.getNickname()));
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
							timerUI.setTimerEditable(false);// TODO se è
															// connesso...
							client.sendMessageToServer(JsonMaker.timerRequest(
									indexString, time[0], time[1]));
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

							nickname + chatUI.getMessage(), "" + index));
							chatUI.emptyMessageArea();
							// chat.getMessageArea().setCaretPosition(0);
						}
					}
				});
			}
		};
		try {
			SwingUtilities.invokeAndWait(runnable);
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("Sono il thread " + Thread.currentThread().getName()
				+ " - L'EDT ha aggiornato la GUI :-)");

	}

}
