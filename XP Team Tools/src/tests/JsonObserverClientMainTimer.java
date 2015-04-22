package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUIObserver;
import ui.TimerUIObserver;
import ui.UIObserver;
import client.model.JsonMaker;
import client.model.ObservableClient;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class JsonObserverClientMainTimer {
	public static void main(String[] args) {
		
		
		final ObservableClient client = new ObservableClient("Nic","Prova");
		
		UIObserver ui = new UIObserver(client);
		final ChatUIObserver chatUI = ui.getChatUI();
		final TimerUIObserver timerUI = ui.getTimerUI();
		
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
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					client.sendMessageToServer(JsonMaker.chatRequest(
							teamName,
							Formatter.formatNickname(client.getNickname())
									+ chatUI.getMessage()));
					chatUI.emptyMessageArea();
					//chat.getMessageArea().setCaretPosition(0);
				}
			}
		});

		Thread thread = new Thread(runnable);
		thread.start();

	}
}
