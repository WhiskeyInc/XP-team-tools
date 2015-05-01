package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.model.IClientService;
import client.model.StrategyClient1_1;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 */

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ChatUIObserverStrategy1 chatUI;
	private final TimerUIObserverStrategy timerUI;
	private final UserListUI userListUI = new UserListUI();
	private JPanel mainPanel;

	public MainUI(IClientService setMessage,
			IClientService setTimeStamp, StrategyClient1_1 client) {
		super();

		this.chatUI = new ChatUIObserverStrategy1(setMessage, client);
		this.timerUI = new TimerUIObserverStrategy(setTimeStamp);

		super.setSize(400, 650);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		mainPanel.add(chatUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(timerUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		mainPanel.add(userListUI, lim);
		super.getContentPane().setLayout(new BorderLayout(50, 10));
		super.getContentPane().add(mainPanel);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}
	
	public void setUserListUi(ActionListener actionListener) {
		userListUI.setButtonAction(actionListener);
	}

	public TimerUIObserverStrategy getTimerUI() {
		return timerUI;
	}

	public ChatUIObserverStrategy1 getChatUI() {
		return chatUI;
	}

	public void setMembersList(String[] nicks) {
		super.getContentPane().remove(mainPanel);
		mainPanel.remove(userListUI);
		userListUI.setNicknames(nicks);
		mainPanel.add(userListUI, BorderLayout.EAST);
		super.getContentPane().add(mainPanel);
	}
	
	public void refresh() {
		super.getContentPane().invalidate();
		super.getContentPane().validate();
		super.getContentPane().repaint();
	}

}
