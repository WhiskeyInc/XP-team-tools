package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import client.model.IClientService;
import client.model.MessageObservable;
import client.model.StrategyClient1_1;

/**
 * The UI of the chat: it's composed of a {@link ChatUI} and a {@link TimerUI}
 * 
 */

public class UIObserverStrategy1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChatUIObserverStrategy1 chatUI;
	private TimerUIObserverStrategy timerUI;

	public UIObserverStrategy1(final IClientService setMessage,
			final IClientService setTimeStamp, final StrategyClient1_1 client,
			final int index) {
		super();
		chatUI = new ChatUIObserverStrategy1(
				(MessageObservable) setMessage.getAttribute(index),
				client);
		timerUI = new TimerUIObserverStrategy(
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
			}
		};

		SwingUtilities.invokeLater(runnable);

		// System.err.println(EventQueue.isDispatchThread() + " " +
		// UIObserverStrategy1.class);

		// super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setChatUI(ActionListener actionListener) {
		chatUI.setButtonAction(actionListener);
	}

	public void setTimerUI(ActionListener actionListener) {
		timerUI.setButtonTimerListener(actionListener);
	}

	public TimerUIObserverStrategy getTimerUI() {
		return timerUI;
	}

	public ChatUIObserverStrategy1 getChatUI() {
		return chatUI;
	}

}
