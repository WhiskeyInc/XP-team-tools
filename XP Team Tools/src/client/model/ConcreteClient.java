package client.model;

import ui.ChatUI;
/**
 * Concrete client fused with the Ui
 * @author alberto
 *
 */
public class ConcreteClient extends AbstractClient {

	private ChatUI chatUI;
	
	public ConcreteClient(String nickname, String teamName, ChatUI chatUI) {
		super(nickname, teamName);
		this.chatUI = chatUI;
	}

	
	@Override
	protected void handleMessage(String message) {
		
		chatUI.appendChatAreaText(message+"\n");
		
	}

}
