package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 * @author Alberto
 *
 */

public class CopyOfUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private final ChatUITestable chatUI = new ChatUITestable();
	private final TimerUI timerUI = new TimerUI();
	private UserListUIBox userListUI = new UserListUIBox();
	private JPanel mainPanel;
	
	public CopyOfUI() {
		super();
		super.setSize(400, 650);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.add(timerUI, BorderLayout.NORTH);
		mainPanel.add(chatUI, BorderLayout.CENTER);
		mainPanel.add(userListUI, BorderLayout.EAST);
		super.getContentPane().setLayout(new BorderLayout());
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
	
	public TimerUI getTimerUI() {
		return timerUI;
	}
	
	public ChatUITestable getChatUI() {
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
