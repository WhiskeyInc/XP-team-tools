package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.model.IClientService;
import client.model.MessageObservable;
import client.model.StrategyClient1_1;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 */

public class UIObserverStrategy1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ChatUIObserverStrategy1 chatUI;
	private final TimerUIObserverStrategy timerUI;

	public UIObserverStrategy1(IClientService setMessage,
			IClientService setTimeStamp, StrategyClient1_1 client, int index) {
		super();

		this.chatUI = new ChatUIObserverStrategy1((MessageObservable) setMessage.getAttribute(index), client);
		this.timerUI = new TimerUIObserverStrategy((MessageObservable)setTimeStamp.getAttribute(index));

		super.setSize(400, 650);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		panel.add(chatUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(10, 10, 10, 10);
		panel.add(timerUI, lim);
		super.getContentPane().setLayout(new BorderLayout(50, 10));
		super.getContentPane().add(panel);
		super.setVisible(true);
		//super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}

	public TimerUIObserverStrategy getTimerUI() {
		return timerUI;
	}

	public ChatUIObserverStrategy1 getChatUI() {
		return chatUI;
	}

}
