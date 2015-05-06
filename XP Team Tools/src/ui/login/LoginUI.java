package ui.login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	protected JButton sendLogin = new JButton("Login");

	private JLabel loginLabel = new JLabel("User");
	private JTextField loginField = new JTextField(20);
	private JLabel passLabel = new JLabel("Pass");
	protected JTextField pwdField = new JTextField(5);

	public LoginUI() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints lim = new GridBagConstraints();
		super.setLayout(layout);

		super.add(loginLabel, lim);
		super.add(loginField, lim);
		super.add(passLabel, lim);
		super.add(pwdField, lim);

		Dimension dim = new Dimension(200, 50);

		setChatPaneConstraints(lim);
		dim = new Dimension(275, 300);

		setMessagePaneConstraints(lim);
		dim = new Dimension(200, 75);

		setSendMessageButtonConstraints(lim);
		dim = new Dimension(75, 50);
		setSendMessageButtonProperties(dim);
		super.add(sendLogin, lim);
	}

	private void setSendMessageButtonProperties(Dimension dim) {
		sendLogin.setFont(new Font(Font.SERIF, Font.PLAIN, 9));
		sendLogin.setMinimumSize(dim);
		sendLogin.setPreferredSize(dim);
	}

	private void setSendMessageButtonConstraints(GridBagConstraints lim) {
		lim.gridx = 1;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
	}

	private void setMessagePaneConstraints(GridBagConstraints lim) {
		lim.gridx = 0;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
	}

	private void setChatPaneConstraints(GridBagConstraints lim) {
		lim.gridx = 0;
		lim.gridy = 1;
		lim.gridwidth = 2;
		lim.gridheight = 1;
	}

	/**
	 * Set the chat area text
	 * 
	 * @param text
	 */
	public void setChatAreaText(String text) {
		loginField.setText(text);
	}

	public String getUserFieldText() {
		return loginField.getText();
	}

	public JTextField getUserField() {
		return loginField;
	}

	/**
	 * Set the ActionListener of the sendLogin button
	 * 
	 * @param actionListener
	 */
	public void setButtonAction(ActionListener actionListener) {
		sendLogin.addActionListener(actionListener);
	}

	public void setEnterListener(KeyListener keyListener) {
		pwdField.addKeyListener(keyListener);
		pwdField.requestFocus();
	}

	public JTextField getPassField() {
		return pwdField;
	}

	public String getPassFieldText() {
		return pwdField.getText();
	}

}
