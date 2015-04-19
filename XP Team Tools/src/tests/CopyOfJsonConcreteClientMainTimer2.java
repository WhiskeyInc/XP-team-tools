package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import string.formatter.Formatter;
import timer.TimerFormatter;
import ui.ChatUITestable;
import ui.TimerUIA;
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
public class CopyOfJsonConcreteClientMainTimer2 {
	public static void main(String[] args) {
		

		final ChatUITestable chatUI = new ChatUITestable();
		final TimerUIA timerUI = new TimerUIA();
		
		
		final AbstractClient client = new ConcreteClient("Alb", "Prova", chatUI, timerUI);
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
		chatUI.setButtonAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(teamName, Formatter.formatNickname(client.getNickname()) + chatUI.getMessage()));
				chatUI.setMessageText(null);
			}
		});
		timerUI.setButtonTimerListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timerUI.isTimeStampValid(timerUI.getTimeStamp())) {
					int[] time = TimerFormatter.getMinSec(timerUI.getTimeStamp());
					timerUI.setTimerEditable(false);
					client.sendMessageToServer(JsonMaker.timerRequest(teamName, time[0], time[1]));
				}
			}
		});
		

		Thread thread = new Thread(runnable);
		thread.start();
		
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		JPanel panel = new JPanel();
		panel.add(chatUI);
		panel.add(timerUI);
		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}
}
