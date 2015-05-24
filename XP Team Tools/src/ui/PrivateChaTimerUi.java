package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import client.model.Client;
import client.model.MacroEvents;
import client.model.MessageObservable;
import client.model.SessionManager;
import client.model.service.IClientService;

/**
 * The UI for private chats, which is shown when a user select a list of team-members and
 * starts the chat
 * 
 * @author ALberto
 */

public class PrivateChaTimerUi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChatUI chatUI;
	private TimerUI timerUI;
	private LoadingPanel lp = new LoadingPanel("Loading");
	private JPanel panel = new JPanel();
	private JPanel loadingPanel = new JPanel();
	
	public PrivateChaTimerUi(final IClientService setMessage,
			final IClientService setTimeStamp, final Client client,
			 final int index, MacroEvents events) {
		super();
		
		chatUI = new ChatUI(
				(MessageObservable) setMessage.getAttribute(index),
				client);
		timerUI = new TimerUI(
				(MessageObservable) setTimeStamp.getAttribute(index), events);	
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
			

				JPanel panel = new JPanel();
				panel.setLayout(new GridBagLayout());
				GridBagConstraints lim = new GridBagConstraints();
				lim.gridx = 0;
				lim.gridy = 1;
				panel.add(chatUI, lim);
				lim = new GridBagConstraints();
				lim.gridx = 0;
				lim.gridy = 0;
				lim.insets = new Insets(10, 10, 10, 10);
				panel.add(timerUI, lim);
				getContentPane().setLayout(new BorderLayout(50, 10));
				getContentPane().add(panel);
				setVisible(true);
				pack();
				//It is necessary to guarantee the method waitObservers of MessageObservable class
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent windowEvent) {
						chatUI.removeObservers();
						SessionManager.getInstance().setChatClosed(index);
					}
				});
			}
		};

		SwingUtilities.invokeLater(runnable);

	}
	
	/**
	 * Sets the @ActionListener of the meeting button
	 * @param actionListener
	 */
	public void setMeetingButtonAction(ActionListener actionListener) {
		chatUI.setButtonMeeting(actionListener);
	}

	/**
	 * Sets the @ActionListener of the Chat Area
	 * @param actionListener to be setted
	 */
	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	/**
	 * Sets the @ActionListener of the Timer Button
	 * @param actionListener to be setted
	 */
	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}

	public TimerUI getTimerUI() {
		return timerUI;
	}

	public ChatUI getChatUI() {
		return chatUI;
	}
	
	/**
	 * Inserts a loadingPanel during loading operation
	 * TODO!!!
	 */
	public void insertLoadingPanel(){
		loadingPanel.add(lp);
		Timer timer1 = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lp.getLabelText().contains("...")){
					lp.setLabelText("Loading");
				}
				lp.setLabelText(lp.getLabelText()+".");
				getContentPane().add(loadingPanel);
				setVisible(true);
			}
		});
		
		timer1.start();
		
		setVisible(true);
	}
	
	public void removeLoadingPanel(){
		getContentPane().add(panel);
		setVisible(true);
	}
	
	

}