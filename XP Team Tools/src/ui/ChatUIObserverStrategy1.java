package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import string.formatter.Formatter;
import client.model.MessageObservable;
import client.model.StrategyClient1_1;
/**
 * The UI of the chat, it extend JPanel
 * @author alberto
 *
 */
public class ChatUIObserverStrategy1 extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topButPanel = new JPanel();
	protected JButton sendMessage = new JButton("SEND");
	private JTextArea chatArea;
	protected JTextArea messageArea = new JTextArea();
	
	private StrategyClient1_1 client;
	private MessageObservable messageObs;
	
	public ChatUIObserverStrategy1(MessageObservable messageObs, StrategyClient1_1 client) {
		this.client = client;
		this.messageObs = messageObs;
		messageObs.addObserver(this);
		createChatArea();
		messageArea.setLineWrap(true);
		messageArea.requestFocus();
		messageArea.setFont(new Font("TimesRoman", Font.ITALIC, 13));
		messageArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
		         null, "Write a message",
		         TitledBorder.DEFAULT_JUSTIFICATION,
		         TitledBorder.DEFAULT_POSITION,
		         new java.awt.Font("Verdana", 1, 8)
		      ),
		      BorderFactory.createEmptyBorder(1, 1, 1, 1)
		   ));
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
		dim = new Dimension(275, 300);
		JScrollPane chatPane = createResaizableJScrollPane(dim);
		super.add(chatPane, lim);
		
		setMessagePaneConstraints(lim);
		dim = new Dimension(200, 75);
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
		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		chatArea.setFont(new Font("TimesRoman", Font.ITALIC, 16));
		chatArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
		         null, client.getTeamName(),
		         TitledBorder.DEFAULT_JUSTIFICATION,
		         TitledBorder.DEFAULT_POSITION,
		         new Font("Verdana", 1, 11)
		      ),
		      BorderFactory.createEmptyBorder(4, 4, 4, 4)
		   ));
		DefaultCaret caret = (DefaultCaret)chatArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
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
	 * Sets the KeyListener on the message text area
	 * @param actionListener
	 */
	public void setEnterListener(KeyListener keyListener) {
		messageArea.addKeyListener(keyListener);
		messageArea.requestFocus();
	}
	public JTextArea getMessageArea() {
		return messageArea;
	}
	/**
	 * It return the text of the message area
	 * @return
	 */
	public String getMessage() {
		return messageArea.getText();
	}
	/**
	 * It empties the message area
	 */
	public void emptyMessageArea() {
		messageArea.setText("");
	}
	/**
	 * It returns the JPanel upon the ChatArea, to allow to add other features
	 * @return
	 */
	public JPanel getTopButPanel() {
		return topButPanel;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		appendChatAreaText(Formatter.appendNewLine(messageObs.getMessage()));
	}
}
