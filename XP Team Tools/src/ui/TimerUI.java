package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sounds.SoundPlayer;
import timer.TimerFormatter;
import client.model.MacroEvents;
import client.model.MessageObservable;
/** 
	 * The UI of the timer: it includes a display that shows the countdown and a button for
	 * timer's start, this class is an Observer of @MessageObservable 
	 * 
	 * @author alessandro B, Alberto
	 *
	 */
public class TimerUI extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	
	private JTextField timerArea;
	private JGradientButton startButton;
	private JComboBox<String> choiseList;
	public static final String ENDTIMER = "00:00";
	private SoundPlayer player = new SoundPlayer("sounds/cannon.wav");
	private MessageObservable messageObs;
	private MacroEvents events;
	
	public TimerUI(MessageObservable messageObs, MacroEvents events) {
		super();
		this.messageObs = messageObs;
		this.events = events;
		messageObs.addObserver(this);
		
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
		startButton = new JGradientButton("Start");
		startButton.setColor(Color.GREEN);
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
		
		
		choiseList = new JComboBox<String>();
		ArrayList<String> arr = events.getNames();
		for (String string : arr) {
			choiseList.addItem(string);
		}
		
		choiseList.setPreferredSize(new Dimension(200,30));
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 2;
		lim.insets = new Insets(10, 0,0,0);
		super.add(choiseList,lim);
		
		super.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
		         null, "Tomatoes panel",
		         TitledBorder.DEFAULT_JUSTIFICATION,
		         TitledBorder.DEFAULT_POSITION,
		         new java.awt.Font("Verdana", 1, 8)
		      ),
		      BorderFactory.createEmptyBorder(1, 1, 1, 1)
		   ));
	}

	/**
	 * Sets the @ActionListener of startTimer button
	 * @param actionListener
	 */
	public void setButtonTimerListener(ActionListener actionListener) {
		startButton.addActionListener(actionListener);
	}

	/**
	 * Sets the timestamp in the display area
	 * @param timeStamp to be shown in display
	 */
	public void setTimer(String timeStamp) {
		timerArea.setText(timeStamp);
	}

	/**
	 * Sets if the button timer is clickable or not
	 * @param isClickable
	 */
	public void setButtomTimerClickable(boolean isClickable) {
		startButton.setEnabled(isClickable);
	}

	public String getTimeStamp() {
		return timerArea.getText();
	}
	
	/**
	 * allows the user to set the countdown
	 * @param isEditable
	 */
	public void setTimerEditable(boolean isEditable) {
		timerArea.setEnabled(isEditable);
		startButton.setEnabled(isEditable);
	}

	/**
	 * checks if a valid Time Stamp has been insert
	 * @param timeStamp
	 * @return
	 */
	public boolean isTimeStampValid(String timeStamp) {
		return TimerFormatter.isTimeStampValid(timeStamp);
	}
	
	/**
	 * Set enabled/disabled the combo box of tomato choises
	 */
	public void setChoisesComboEnabled(boolean enabled){
		choiseList.setEnabled(enabled);
	}
	
	public String getChosenCombo(){
		return (String) choiseList.getSelectedItem();
	}

	/**
	 * plays a sound alarm when the countdown's end is reached
	 */
	private void alertEndTimer() {
		if (timerArea.getText().equals(ENDTIMER)) {
			player.playSong();
			setTimerEditable(true);
			setButtomTimerClickable(true);
			setChoisesComboEnabled(true);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		String message = messageObs.getMessage();
		StringTokenizer tok = new StringTokenizer(message,":");
		String time = tok.nextToken() + ":" + tok.nextToken();
		setTimer(time);
		if(!time.equals(TimerUI.ENDTIMER)) {
			setTimerEditable(false);
			setChoisesComboEnabled(false);
		}
		
		String name = events.getNameFromId((String) tok.nextToken());
		choiseList.setSelectedItem(name);
		
	}

}
