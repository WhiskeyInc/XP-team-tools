package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;
import client.model.MacroEvents;
import client.model.MessageObservable;
import client.model.SessionManager;
import client.model.Client;
import client.model.service.IClientService;
import events.SendPost;

/**
 * A class that launchs the UI for the private chat when it receives a notification 
 * from the Observable object @MessageObservable
 * 
 *
 */
public class NewChatInviteLauncher implements Observer {

	private Client client;
	private IClientService serviceMessage;
	private IClientService serviceTimeStamp;
	private MessageObservable mObs;
	private SessionManager sessionManager;

	public NewChatInviteLauncher(IClientService newChat, Client client,
			IClientService serviceMessage, IClientService serviceTimeStamp, SessionManager sessionManager) {
		super();
		this.client = client;
		this.serviceMessage = serviceMessage;
		this.serviceTimeStamp = serviceTimeStamp;
		mObs = (MessageObservable) newChat.getAttribute(0);
		mObs.addObserver(this);
		this.sessionManager = sessionManager;
	}

	@Override
	public void update(Observable o, Object arg) {
		final int index = Integer.parseInt(mObs.getMessage());
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.err.println(index+ " "+ NewChatInviteLauncher.class);
				System.err.println(EventQueue.isDispatchThread() + " " + NewChatInviteLauncher.class);
				System.err.println(index + " " + NewChatInviteLauncher.class);
				if(sessionManager.hasChat(index)) {
					if(!sessionManager.isChatOpen(index)) {
						launchUI();
					}
				} else {
					sessionManager.registerChatOpening(index);
					launchUI();
				}

			}
		
	private void launchUI() {
		final String nickname = Formatter.formatNickname(client
				.getNickname());
		
		SendPost sender = new SendPost("http://xtream-whiskeyinc.rhcloud.com/XPTT_Web/JSONAcceptor");
		String message = JsonMaker.requestMacroEventsList("admin");
					
		String answer = sender.sendJson(message);
		
		final MacroEvents events = JsonParser.parseMacroEventsResponse(answer);
				
		PrivateChaTimerUi ui = new PrivateChaTimerUi(
				serviceMessage, serviceTimeStamp, client, index, events);
		final ChatUI chatUI = ui.getChatUI();
		final TimerUI timerUI = ui.getTimerUI();
		// Controlla conferma data dal server in caso è fallito l'add...

		final String teamName = client.getTeamName();
		ui.setChatUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(JsonMaker.chatRequest(
						teamName, client.getNickname()));
				chatUI.emptyMessageArea();
			}
		});

		final String indexString = String.valueOf(index);

		ui.setTimerUI(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timerUI.isTimeStampValid(timerUI.getTimeStamp())) {
					int[] time = TimerFormatter.getMinSec(timerUI
							.getTimeStamp());
					timerUI.setTimerEditable(false);// TODO se è
													// connesso...
					
					
					client.sendMessageToServer(JsonMaker.teamMembsRequest(client.getNickname(), client.getTeamName()));
					String jsonMembs = client.waitServerResponse();
					
					String[] membs;
					ArrayList<String> participants = new ArrayList<String>();
					try {
						membs = JsonParser.parseMakeTeamMembs(jsonMembs);
						for (int i = 0; i < membs.length; i++) {
							participants.add(membs[i]);
						}
						// add myself member
						participants.add(client.getNickname());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					timerUI.setChoisesComboEnabled(false);
					
					String chosenId = events.getIdFromName(timerUI.getChosenCombo());
					
					client.sendMessageToServer(JsonMaker.timerRequest(
							indexString, time[0], time[1], participants, chosenId));
					
				}
			}
		});

		chatUI.setEnterListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					client.sendMessageToServer(JsonMaker.chatRequest(

					nickname + chatUI.getMessage(), "" + index));
					chatUI.emptyMessageArea();
					
				}
			}
		});
	}
};
try {
	SwingUtilities.invokeAndWait(runnable);
} catch (InvocationTargetException | InterruptedException e) {
	e.printStackTrace();
}
	}
}
