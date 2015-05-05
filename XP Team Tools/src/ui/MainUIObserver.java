package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import protocol.JsonMaker;
import string.formatter.Formatter;
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
	private StrategyClient1_1 client;
	//setMessage = 0
	//setTimeStamp = 1
	//newChat = 2
	public MainUIObserver(final IClientService[] services, SetMembsService setTeamMembs, final StrategyClient1_1 client, int index) {
		super();
		this.setTeamMembs = setTeamMembs;
		setTeamMembs.addObserver(this);
		this.client = client;
		this.chatUI = new ChatUIObserverStrategy1((MessageObservable)services[0].getAttribute(index), client);
		this.timerUI = new TimerUIObserverStrategy((MessageObservable)services[1].getAttribute(index));
		this.userListUI = new UserListUI();
		super.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        client.sendMessageToServer(JsonMaker.disconnect(client.getClientDetails()));
		        System.exit(0);
		    }
		});
		System.err.println(EventQueue.isDispatchThread() +" " + MainUIObserver.class);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		//It works like a listener
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				NewChatLauncher newChatListener = new NewChatLauncher(services[2], client, services[0], services[1]);
				
			}
		};
		try {
			SwingUtilities.invokeAndWait(runnable);
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logLabel = new JLabel("Logged as "+Formatter.formatNickname(client.getNickname()));
		logLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
		logLabel.setForeground(Color.RED);
		logLabel.setBackground(Color.white);
		logLabel.setBorder((BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
		         null, "Log-in panel",
		         TitledBorder.DEFAULT_JUSTIFICATION,
		         TitledBorder.DEFAULT_POSITION,
		         new java.awt.Font("Verdana", 1, 8)
		      ),
		      BorderFactory.createEmptyBorder(1, 1, 1, 1)
		   )));
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(5, 10, 0, 10);
		mainPanel.add(logLabel, lim);
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 1;
		mainPanel.add(chatUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		lim.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(timerUI, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(0, 10, 5, 10);
		lim.gridheight = 2;
		mainPanel.add(userListUI, lim);
		mainPanel.setBackground(new Color(235, 234, 243));
		super.getContentPane().setLayout(new BorderLayout(50, 10));
		super.getContentPane().setBackground(Color.black);
		super.getContentPane().add(mainPanel);
		super.setVisible(true);
		
		setMembersList(nicksFilter(client));
		refresh();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();

	}
	
	public void setMeetingButtonAction(ActionListener actionListener) {
		chatUI.setButtonMeeting(actionListener);
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
		lim.insets = new Insets(0, 30, 10, 10);
		lim.gridx = 0;
		lim.gridy = 1;
		lim.gridheight = 2;
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
	
	private String[] nicksFilter(StrategyClient1_1 client) {
		String[] membs = setTeamMembs.getMembs();
		String[] membsWithoutMe = new String[membs.length-1];
		int i = 0;
		for (String nick : membs) {
			if(!nick.equals(client.getNickname())) {
				membsWithoutMe[i] = nick;
				i++;
			}
		}
		return membsWithoutMe;
	}

	@Override
	public void update(Observable o, Object arg) {
		
			System.out.println("******************************** " + MainUIObserver.class );
			setMembersList(nicksFilter(client));
			refresh();

	}

}
