package ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ChatUITestable extends ChatUI {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Set the text of the message area [test]
	 * @param text
	 */
	public void setMessageText(String text) {
		messageArea.setText(text);
	}
	/**
	 * Simulate the click of the sendMessage button [test]
	 */
	public void simulateSendButtonClick() {
		sendMessage.doClick();
	}
	/**
	 * Simulate the enter click [test]
	 */
	public void simulateSendEnterClick() {
		try {
	        Robot robot = new Robot();
	        
	        messageArea.requestFocus();
	        messageArea.requestFocusInWindow();
	        
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
