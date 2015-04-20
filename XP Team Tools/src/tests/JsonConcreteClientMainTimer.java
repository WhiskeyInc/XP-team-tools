package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUI;
import ui.TimerUIA;
import ui.UI;
import client.model.AbstractClient;
import client.model.ConcreteClient;
import client.model.JsonMaker;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class JsonConcreteClientMainTimer {
	public static void main(String[] args) {
		
		UI ui = new UI();
		final ChatUI chatUI = ui.getChatUI();
		final TimerUIA timerUI = ui.getTimerUI();
		
		

		final AbstractClient client = new ConcreteClient("Nic", "Prova",
				chatUI, timerUI);
		client.openStreams("ec2-52-74-20-119.ap-southeast-1.compute.amazonaws.com", 9999);
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
