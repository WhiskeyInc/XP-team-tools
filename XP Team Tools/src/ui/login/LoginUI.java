package ui.login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.utils.ISessionSaver;

/**
 * The UI of the login, that allows to insert username and password and has a useful feature, i.e,
 * the possibility to memorize username and password for the next access. If the user is not registered
 * yet, there's a "Register" button, too.
 *
 * @author pavlo
 *
 */
public class LoginUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton sendLogin = new JButton("Login");
	private JButton register = new JButton("Register");

	private JLabel userLabel = new JLabel("Username:");
	private JTextField userField = new JTextField(20);
	private JLabel passLabel = new JLabel("Password:");
	private JPasswordField pwdField = new JPasswordField(20);

	private JCheckBox saveCheck = new JCheckBox("Remembre Me");
	private ISessionSaver sessionSaver;

	public LoginUI() {
		setLayout(new GridBagLayout());

		GridBagConstraints lim = new GridBagConstraints();

		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(30, 20, 0, 0);
		super.add(userLabel, lim);
		userField.setFont(new Font("TimesRoman", 0, 18));
		userField.setMinimumSize(new Dimension(150, 40));
		userField.setPreferredSize(new Dimension(150, 40));
		userField.requestFocus();

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		lim.insets = new Insets(30, 10, 0, 20);
		super.add(userField, lim);

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

		sendLogin.setMinimumSize(new Dimension(145, 25));
		sendLogin.setPreferredSize(new Dimension(145, 25));
		register.setMinimumSize(new Dimension(145, 25));
		register.setPreferredSize(new Dimension(145, 25));

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 3;
		lim.insets = new Insets(5, 0, 30, 170);
		super.add(sendLogin, lim);

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 3;
		lim.insets = new Insets(5, 170, 30, 30);
		super.add(register, lim);

		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 2;
		super.add(saveCheck, lim);

		saveCheck.setSelected(true);

	}

	public void setLoginListener(ActionListener actionListener) {
		sendLogin.addActionListener(actionListener);
	}

	public void setRegisterListener(ActionListener actionListener) {
		register.addActionListener(actionListener);
	}

	public void setEnterListener(KeyListener keyListener) {
		pwdField.addKeyListener(keyListener);

	}

	public String getLoginNick() {
		return userField.getText();
	}

	public String getPass() {
		return String.valueOf(pwdField.getPassword());
	}

	public void setLoginNick(String user) {
		userField.setText(user);
	}

	public void setPass(String pwd) {
		pwdField.setText(pwd);
	}

	public void setSessionSaver(ISessionSaver sessionSaver) {
		this.sessionSaver = sessionSaver;
	}
	
	public ISessionSaver getSessionSaver() {
		return this.sessionSaver;
	}

	/**
	 * if the user selects "remembre me", this session is saved
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void getCheckStatus() throws NoSuchAlgorithmException,
			UnsupportedEncodingException, IOException {
		if (saveCheck.isSelected()) {
			sessionSaver.saveSession();

		} else {
			sessionSaver.deleteSession();
		}
	}

	/**
	 * Checks if a previous session is saved
	 * @return true if this session is saved
	 * @throws IOException
	 */
	public boolean checkSession() throws IOException {
		if(!(sessionSaver.getSessionValues() == null)){
			return true;
		}
		return false;
	}
}
