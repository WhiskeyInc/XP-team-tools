package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.model.IClientService;
import client.model.MessageObservable;
import client.model.SetMembsService;
import client.model.StrategyClient1_1;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 */

public class MainUIObserver extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ChatUIObserverStrategy1 chatUI;
	private final TimerUIObserverStrategy timerUI;
	private final UserListUI userListUI;
	private SetMembsService setTeamMembs;
	private JPanel mainPanel;
	//setMessage = 0
	//setTimeStamp = 1
	//newChat = 2
	public MainUIObserver(IClientService[] services, SetMembsService setTeamMembs, StrategyClient1_1 client, int index) {
		super();
		this.setTeamMembs = setTeamMembs;
		setTeamMembs.addObserver(this);
		this.chatUI = new ChatUIObserverStrategy1((MessageObservable)services[0].getAttribute(index), client);
		this.timerUI = new TimerUIObserverStrategy((MessageObservable)services[1].getAttribute(index));
		this.userListUI = new UserListUI();
		super.setSize(650, 650);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		//It works like a listener
		NewChatLauncher newChatLauncher = new NewChatLauncher(services[2], client, services[0], services[1]);
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		mainPanel.add(chatUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(timerUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		lim.insets = new Insets(5, 10, 5, 10);
		mainPanel.add(userListUI, lim);
		super.getContentPane().setLayout(new BorderLayout(50, 10));
		super.getContentPane().add(mainPanel);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}
	
	public void setUserListUi(ActionListener actionListener) {
		userListUI.setButtonAction(actionListener);
	}

	public TimerUIObserverStrategy getTimerUI() {
		return timerUI;
	}

	public ChatUIObserverStrategy1 getChatUI() {
		return chatUI;
	}

	public void setMembersList(String[] nicks) {
		super.getContentPane().remove(mainPanel);
		mainPanel.remove(userListUI);
		userListUI.setNicknames(nicks);
		GridBagConstraints lim = new GridBagConstraints();
		lim.insets = new Insets(10, 30, 10, 10);
		lim.gridx = 1;
		lim.gridy = 0;
		mainPanel.add(userListUI, lim);
		super.getContentPane().add(mainPanel);
	}
	
	public UserListUI getUserListUI() {
		return userListUI;
	}
	
	public void refresh() {
	//	super.getContentPane().invalidate();
		super.getContentPane().revalidate();
	//	super.getContentPane().repaint();
		super.revalidate();
	//	super.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		
	//	if(o instanceof SetMembsService) {
			System.out.println("******************************** " + MainUIObserver.class );
			setMembersList(setTeamMembs.getMembs());
			refresh();
			//NEL BOTTONE TODO
//		} else {
//			Runnable runnable = new Runnable() {
//				
//				@Override
//				public void run() {
//					final String nickname = Formatter.formatNickname(client.getNickname());
////					IClientService serviceMessage = new SetMessageService();
////					IClientService serviceTimeStamp = new SetTimeStampService();
//					UIObserverStrategy1 ui = new UIObserverStrategy1(setMessage, setTimeStamp, client);
//					final ChatUIObserverStrategy1 chatUI = ui.getChatUI();
//					final TimerUIObserverStrategy timerUI = ui.getTimerUI();
//					
//					
//					final int index = Integer.parseInt(setNewChatService.getMembs(null)[0]);
//					client.sendMessageToServer(JsonMaker.chatRequest("- " +client.getNickname() + " added to the team -", ""+index));
//					
//					System.err.println("L' indice della chat è : " + index + " ["+ MainUIObserver.class + "]");
//					//Controlla conferma data dal server in caso è fallito l'add...
//					
//
//					
//					final String teamName = client.getTeamName();
//					ui.setChatUI(new ActionListener() {
//
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							client.sendMessageToServer(JsonMaker.chatRequest(
//									teamName,
//									client.getNickname()));
//							chatUI.emptyMessageArea();
//						}
//					});
//					
//					
//					final String indexString = String.valueOf(index);
//					
//					ui.setTimerUI(new ActionListener() {
//
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							if (timerUI.isTimeStampValid(timerUI.getTimeStamp())) {
//								int[] time = TimerFormatter.getMinSec(timerUI
//										.getTimeStamp());
//								timerUI.setTimerEditable(false);// TODO se è connesso...
//								client.sendMessageToServer(JsonMaker.timerRequest(indexString,
//										time[0], time[1]));
//							}
//						}
//					});
//
//					chatUI.setEnterListener(new KeyListener() {
//						@Override
//						public void keyTyped(KeyEvent e) {}
//						@Override
//						public void keyReleased(KeyEvent e) {}
//						@Override
//						public void keyPressed(KeyEvent e) {
//
//							if(e.getKeyCode() == KeyEvent.VK_ENTER){
//								e.consume();
//								client.sendMessageToServer(JsonMaker.chatRequest(
//										
//										nickname + chatUI.getMessage()	, ""+index));
//								chatUI.emptyMessageArea();
//								//chat.getMessageArea().setCaretPosition(0);
//							}
//						}
//					});
//				}
//			};
//			Thread thread = new Thread(runnable);
//			thread.start();
//		}
	}

}
