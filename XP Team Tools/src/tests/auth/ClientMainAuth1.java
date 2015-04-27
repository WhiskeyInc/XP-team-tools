package tests.auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import server.model.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUIObserverStrategyAuth;
import ui.TimerUIObserverStrategy;
import ui.UIObserverStrategyAuth;
import client.model.IClientService;
import client.model.JsonMaker;
import client.model.SetMessageService;
import client.model.SetTimeStampService;
import client.model.StrategyClientAuth;

public class ClientMainAuth1 {
	public static void main(String[] args) {

		IClientService serviceMessage = new SetMessageService();
		IClientService serviceTimeStamp = new SetTimeStampService();

		final StrategyClientAuth client = new StrategyClientAuth("Nic", "Nic123", "LeFere");
		client.addService(JsonParser.CHAT, serviceMessage);
		client.addService(JsonParser.TIMER, serviceTimeStamp);

		UIObserverStrategyAuth ui = new UIObserverStrategyAuth(serviceMessage,
				serviceTimeStamp, client);
		final ChatUIObserverStrategyAuth chatUI = ui.getChatUI();
		final TimerUIObserverStrategy timerUI = ui.getTimerUI();

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
		final String teamName = client.getTeamName();
		ui.setChatUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(
						teamName,
						Formatter.formatNickname(client.getNickname())
								+ chatUI.getMessage()));
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
					client.sendMessageToServer(JsonMaker.timerRequest(teamName,
							time[0], time[1]));
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
					client.sendMessageToServer(JsonMaker.chatRequest(teamName,
							Formatter.formatNickname(client.getNickname())
									+ chatUI.getMessage()));
					chatUI.emptyMessageArea();
					// chat.getMessageArea().setCaretPosition(0);
				}
			}
		});

		Thread thread = new Thread(runnable);
		thread.start();

	}
}
