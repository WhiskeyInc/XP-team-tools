package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sounds.SoundPlayer;
import timer.TimerFormatter;
import client.model.IClientService;

public class TimerUIObserverStrategy extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField timerArea;
	private JButton startButton;
	public static final String ENDTIMER = "00:00";
	private SoundPlayer player = new SoundPlayer("sounds/cannon.wav");
	private IClientService service;

	public TimerUIObserverStrategy(IClientService service) {
		super();
		
		this.service = service;
		service.addObserver(this);
		
		timerArea = new JTextField();
		Dimension dim = new Dimension();
		dim.setSize(200, 120);
		timerArea.setPreferredSize(dim);
		timerArea.setMinimumSize(dim);
		timerArea.setFont(new Font("TimesRoman", Font.BOLD, 55));
		timerArea.setDocument(new FixedSizeDocument());
		timerArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				alertEndTimer();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				alertEndTimer();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				alertEndTimer();

			}
		});
		timerArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(null, "Tomato",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font(
								"Verdana", 1, 12)), BorderFactory
				.createEmptyBorder(1, 1, 1, 1)));
		timerArea.setText("00:10");
		dim = new Dimension();
		dim.setSize(200, 40);
		startButton = new JButton("Start");
		startButton.setBackground(Color.GREEN);
		startButton.setPreferredSize(dim);
		startButton.setMinimumSize(dim);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		super.setLayout(layout);
		super.add(timerArea, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		super.add(startButton, lim);
	}

	public void setButtonTimerListener(ActionListener actionListener) {
		startButton.addActionListener(actionListener);
	}

	public void setTimer(String timeStamp) {
		timerArea.setText(timeStamp);
	}

	public void setButtomTimerClickable(boolean isClickable) {
		startButton.setEnabled(isClickable);
	}

	public String getTimeStamp() {
		return timerArea.getText();
	}

	public void setTimerEditable(boolean isEditable) {
		timerArea.setEnabled(isEditable);
	}

	public boolean isTimeStampValid(String timeStamp) {
		return TimerFormatter.isTimeStampValid(timeStamp);
	}

	private void alertEndTimer() {
		if (timerArea.getText().equals(ENDTIMER)) {
			player.playSong();
			setTimerEditable(true);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		setTimer(service.getAttribute());
		if(!service.getAttribute().equals(TimerUIObserverStrategy.ENDTIMER)) {
			setTimerEditable(false);
		}
	}

}
