package ui.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The UI of Registration Confirmation
 * 
 * @author pavlo
 *
 */
public class RegConfirmationUI extends JPanel{//TODO 
	
	private static final long serialVersionUID = 1L;
	private JButton continueLogin = new JButton("Continue");
	private JButton registerAgain = new JButton("Try Again");

	private JLabel userLabel = new JLabel("Registration Confirmed");
	
	private JLabel passLabel = new JLabel("Registration Failed");


	public RegConfirmationUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints lim = new GridBagConstraints();
		
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(30, 20, 0, 0);
		super.add(userLabel, lim);
		
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(2, 20, 0, 0);
		super.add(passLabel, lim);

		
		continueLogin.setMinimumSize(new Dimension(145, 25));
		continueLogin.setPreferredSize(new Dimension(145, 25));
		
		registerAgain.setMinimumSize(new Dimension(145, 25));
		registerAgain.setPreferredSize(new Dimension(145, 25));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 2;
		lim.insets = new Insets(5, 0, 30, 170);
		super.add(continueLogin, lim);
		
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 2;
		lim.insets = new Insets(5, 170, 30, 30);
		super.add(registerAgain, lim);
		
	}
	
	/**
	 * sets the @ActionListener for continueLogin button
	 * @param actionListener
	 */
	public void setLoginListener(ActionListener actionListener) {
		continueLogin.addActionListener(actionListener);
	}
	
	/**
	 * sets the @ActionListener for registerAgain button
	 * @param actionListener
	 */
	public void setRegisterListener(ActionListener actionListener) {
		registerAgain.addActionListener(actionListener);
	}

}
