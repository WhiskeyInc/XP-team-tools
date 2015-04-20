package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import string.formatter.Formatter;
import ui.ChatUIObserver;
import ui.ChatUITestable;
import ui.TimerUIA;
import client.model.AbstractClient;
import client.model.ConcreteClient;
import client.model.JsonMaker;
import client.model.ObservableClient;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class JsonConcreteClientMainOBS2 {
	public static void main(String[] args) {
		
		final ObservableClient client = new ObservableClient("nic","Prova");
		
		final ChatUIObserver chatUI = new ChatUIObserver(client);
		
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
				chatUI.emptyMessageArea();
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
		
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.getContentPane().add(chatUI);
		frame.setVisible(true);

	}
}