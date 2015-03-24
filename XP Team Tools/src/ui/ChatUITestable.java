package ui;

public class ChatUITestable extends ChatUI{

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
	public void simulateButtonClick() {
		sendMessage.doClick();
	}
}
