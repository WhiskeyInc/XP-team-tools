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
	protected JButton sendMessage = new JButton("SEND");
	private JTextArea chatArea;
	protected JTextArea messageArea = new JTextArea();
	
	
	public ChatUI() {
		createChatArea();
		messageArea.setLineWrap(true);
		JScrollPane messagePane = createVerticalJScrollPane();
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints lim = new GridBagConstraints();
		super.setLayout(layout);
		
		setTopButPaneConstraints(lim);
		Dimension dim = new Dimension(200, 50);
		setTopButPaneDim(dim);
		// for example : topButPanel.add(new JButton());
		super.add(topButPanel, lim);
		
		setChatPaneConstraints(lim);
		dim = new Dimension(250, 300);
		JScrollPane chatPane = createResaizableJScrollPane(dim);
		super.add(chatPane, lim);
		
		setMessagePaneConstraints(lim);
		dim = new Dimension(175, 50);
		messagePane.setMinimumSize(dim);
		messagePane.setPreferredSize(dim);
		super.add(messagePane, lim);
		
		setSendMessageButtonConstraints(lim);
		dim = new Dimension(75, 50);
		setSendMessageButtonProperties(dim);
		super.add(sendMessage, lim);
	}
	private JScrollPane createResaizableJScrollPane(Dimension dim) {
		JScrollPane chatPane = new JScrollPane(chatArea);
		chatPane.setMinimumSize(dim);
		chatPane.setPreferredSize(dim);
		return chatPane;
	}
	private JScrollPane createVerticalJScrollPane() {
		JScrollPane messagePane = new JScrollPane(messageArea,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return messagePane;
	}
	private void setSendMessageButtonProperties(Dimension dim) {
		sendMessage.setFont(new Font(Font.SERIF, Font.PLAIN, 9));
		sendMessage.setMinimumSize(dim);
		sendMessage.setPreferredSize(dim);
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
	private void setTopButPaneDim(Dimension dim) {
		topButPanel.setPreferredSize(dim);
		topButPanel.setMinimumSize(dim);
	}
	private void setTopButPaneConstraints(GridBagConstraints lim) {
		lim.insets.top = 1;
		lim.insets.bottom = 1;
		lim.insets.left = 1;
		lim.insets.right = 1;
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;
		lim.gridy = 0;
		lim.gridwidth = 2;
		lim.gridheight = 1;
	}
	private void createChatArea() {
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setBackground(new Color(248, 244, 255));
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
