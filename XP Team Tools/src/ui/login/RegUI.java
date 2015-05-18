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
 * The UI of the registration's panel, where a user can perform his registration,
 * after he has added username and password (the password is required twice for a 
 * better control)
 * 
 * @author pavlo
 *
 */
public class RegUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton sendBackLogin = new JButton("Back to Login");
	private JButton register = new JButton("Register");

	private JLabel userLabel = new JLabel("Username:");
	private JTextField loginField = new JTextField(20);
	private JLabel passLabel = new JLabel("Password:");
	private JPasswordField pwdField = new JPasswordField(20);
	private JLabel passLabel2 = new JLabel("Repeat Password:");
	private JPasswordField pwdField2 = new JPasswordField(20);

	public RegUI() {
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

		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 2;
		lim.insets = new Insets(2, 20, 0, 0);
		super.add(passLabel2, lim);

		pwdField.setFont(new Font("TimesRoman", 0, 18));
		pwdField.setMinimumSize(new Dimension(150, 40));
		pwdField.setPreferredSize(new Dimension(150, 40));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 1;
		lim.insets = new Insets(0, 10, 0, 20);
		super.add(pwdField, lim);

		pwdField2.setFont(new Font("TimesRoman", 0, 18));
		pwdField2.setMinimumSize(new Dimension(150, 40));
		pwdField2.setPreferredSize(new Dimension(150, 40));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 2;
		lim.insets = new Insets(0, 10, 0, 20);
		super.add(pwdField2, lim);

		pwdField2.setFont(new Font("TimesRoman", 0, 18));
		pwdField2.setMinimumSize(new Dimension(150, 40));
		pwdField2.setPreferredSize(new Dimension(150, 40));

		sendBackLogin.setMinimumSize(new Dimension(145, 25));
		sendBackLogin.setPreferredSize(new Dimension(145, 25));
		register.setMinimumSize(new Dimension(145, 25));
		register.setPreferredSize(new Dimension(145, 25));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 3;
		lim.insets = new Insets(5, 0, 30, 170);
		super.add(sendBackLogin, lim);

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 3;
		lim.insets = new Insets(5, 170, 30, 30);
		super.add(register, lim);

	}

	public void setBackLoginListener(ActionListener actionListener) {
		sendBackLogin.addActionListener(actionListener);
	}

	public void setRegisterListener(ActionListener actionListener) {
		register.addActionListener(actionListener);
	}

	public String getLoginNick() {
		return loginField.getText();
	}

	/**
	 * this method returns the password that the user has written in the first
	 * password's field and ensure that the user writes the same password in the
	 * second password's field
	 */
	public String getPass() {
		return String.valueOf(pwdField.getPassword());
	}

}
