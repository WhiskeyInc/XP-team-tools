package client.model;

import ui.ChatUI;
import ui.TimerUI;
/**
 * Concrete client with the Ui
 * @author alberto
 *
 */
public class ConcreteClient extends AbstractClient {

	private ChatUI chatUI;
	private TimerUI timerUI;
	
	public ConcreteClient(String nickname, String teamName, ChatUI chatUI, TimerUI timerUI) {
		super(nickname, teamName);
		this.chatUI = chatUI;
		this.timerUI = timerUI;
	}

	
	@Override
	protected void handleMessage(String message) {
		
		chatUI.appendChatAreaText(message+"\n");
	}

	@Override
	protected void handleTimeStamp(String timeStamp) {
		timerUI.setTimer(timeStamp);
		if(!timeStamp.equals(TimerUI.ENDTIMER)) {
			timerUI.setTimerEditable(false);
		}
	}
	

}
