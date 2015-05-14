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

import client.model.IClientService;
import client.model.MessageObservable;
import client.model.SessionManager;
import client.model.StrategyClient1_1;

/**
 * A part of UI of the chat: it's made of a @ChatUI and a @TimerUI
 * 
 */

public class UIObserverStrategy1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChatUI chatUI;
	private TimerUI timerUI;
	private LoadingPanel lp = new LoadingPanel("Loading");
	private JPanel panel = new JPanel();
	private JPanel loadingPanel = new JPanel();
	
	public UIObserverStrategy1(final IClientService setMessage,
			final IClientService setTimeStamp, final StrategyClient1_1 client,
			 final int index) {
		super();
		
		chatUI = new ChatUI(
				(MessageObservable) setMessage.getAttribute(index),
				client);
		timerUI = new TimerUI(
				(MessageObservable) setTimeStamp.getAttribute(index));
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
	
	public void setMeetingButtonAction(ActionListener actionListener) {
		chatUI.setButtonMeeting(actionListener);
	}

	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}

	public TimerUI getTimerUI() {
		return timerUI;
	}

	public ChatUI getChatUI() {
		return chatUI;
	}
	
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
