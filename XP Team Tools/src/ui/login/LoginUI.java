package ui.login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * The UI of the login, it extends JPanel
 * To be corrected set up TODO
 * @author koelio
 *
 */
public class LoginUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton sendLogin = new JButton("Login");

	private JLabel userLabel = new JLabel("Username:");
	private JTextField loginField = new JTextField(20);
	private JLabel passLabel = new JLabel("Password:");
	private JPasswordField pwdField = new JPasswordField(20);

	public LoginUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints lim = new GridBagConstraints();
		
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(30, 20, 0, 0);
		super.add(userLabel, lim);
		loginField.setFont(new Font("TimesRoman", 0, 18));
		loginField.setMinimumSize(new Dimension(150, 40));
		loginField.setPreferredSize(new Dimension(150, 40));
		
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		lim.insets = new Insets(30, 10, 0, 20);
		super.add(loginField, lim);
		
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(2, 20, 0, 0);
		super.add(passLabel, lim);
		
		pwdField.setFont(new Font("TimesRoman", 0, 18));
		pwdField.setMinimumSize(new Dimension(150, 40));
		pwdField.setPreferredSize(new Dimension(150, 40));
		
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 1;
		lim.insets = new Insets(0, 10, 0, 20);
		super.add(pwdField, lim);
		sendLogin.setMinimumSize(new Dimension(324, 25));
		sendLogin.setPreferredSize(new Dimension(324, 25));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 2;
		lim.insets = new Insets(0, 10, 30, 20);
		super.add(sendLogin, lim);
		
	}
	
	public void setLoginListener(ActionListener actionListener) {
		sendLogin.addActionListener(actionListener);
	}
	
	public String getLoginNick() {
		return loginField.getText();
	}
	
	public String getPass() {
		return String.valueOf(pwdField.getPassword());
	}

}
