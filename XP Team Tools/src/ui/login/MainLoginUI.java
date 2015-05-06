package ui.login;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.ChatUI;
import ui.TimerUI;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 * @author Alberto
 *
 */

public class MainLoginUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private final LoginUITestable loginUI = new LoginUITestable();
	
	
	public MainLoginUI() {
		super();
		super.setSize(400, 650);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		panel.add(loginUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(10, 10, 10, 10);
//		lim.anchor = GridBagConstraints.ABOVE_BASELINE;
		
		super.getContentPane().setLayout(new BorderLayout(50, 10));
		super.getContentPane().add(panel);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setLoginUI(ActionListener actionListener) {
		loginUI.setButtonAction(actionListener);
	}
		
	
	public LoginUI getChatUI() {
		return loginUI;
	}

}
