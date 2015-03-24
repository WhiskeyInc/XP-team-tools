package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * The UI of the chat, it extend JPanel
 * @author alberto
 *
 */
public class ChatUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topButPanel = new JPanel();
	protected JButton sendMessage = new JButton();
	private JTextArea chatArea;
	protected JTextArea messageArea;
	
	
	public ChatUI() {
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setBackground(new Color(248, 244, 255));
		messageArea = new JTextArea();
		messageArea.setLineWrap(true);
		JScrollPane messagePane = new JScrollPane(messageArea,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane chatPane = new JScrollPane(chatArea);
		sendMessage.setText("SEND");
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints lim = new GridBagConstraints();
		super.setLayout(layout);
		lim.insets.top = 1;
		lim.insets.bottom = 1;
		lim.insets.left = 1;
		lim.insets.right = 1;
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;
		lim.gridy = 0;
		lim.gridwidth = 2;
		lim.gridheight = 1;
		Dimension dim = new Dimension(200, 50);
		topButPanel.setPreferredSize(dim);
		topButPanel.setMinimumSize(dim);
		// for example : topButPanel.add(new JButton());
		super.add(topButPanel, lim);
		lim.gridx = 0;
		lim.gridy = 1;
		lim.gridwidth = 2;
		lim.gridheight = 1;
		dim = new Dimension(250, 300);
		chatPane.setMinimumSize(dim);
		chatPane.setPreferredSize(dim);
		super.add(chatPane, lim);
		lim.gridx = 0;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
		dim = new Dimension(175, 50);
		messagePane.setMinimumSize(dim);
		messagePane.setPreferredSize(dim);
		super.add(messagePane, lim);
		lim.gridx = 1;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
		dim = new Dimension(75, 50);
		sendMessage.setFont(new Font(Font.SERIF, Font.PLAIN, 9));
		sendMessage.setMinimumSize(dim);
		sendMessage.setPreferredSize(dim);
		super.add(sendMessage, lim);
	}
	/**
	 * Set the chat area text
	 * @param text
	 */
	public void setChatAreaText(String text) {
		chatArea.setText(text);
	}
	/**
	 * Append text on the chat area
	 * @param textToAppend
	 */
	public void appendChatAreaText(String textToAppend) {
		chatArea.append(textToAppend);
	}
	/**
	 * Returns the chat area text
	 * @return
	 */
	public String getChatAreaText() {
		return chatArea.getText();
	}
	/**
	 * Set the ActionListener of the sendMessage button
	 * @param actionListener
	 */
	public void setButtonAction(ActionListener actionListener) {
		sendMessage.addActionListener(actionListener);
	}
	/**
	 * It return the text of the message area
	 * @return
	 */
	public String getMessage() {
		return messageArea.getText();
	}
	
	/**
	 * It returns the JPanel upon the ChatArea, to allow to add other features
	 * @return
	 */
	public JPanel getTopButPanel() {
		return topButPanel;
	}
}
