package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import sounds.SoundPlayer;

/**
 * The UI of the Timer, with the StartButton and a display that shows the
 * countdown
 * 
 * @author alessandro B.
 * 
 */
public class TimerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton timerButton;
	private JTextField timerArea;
	private int second;
	private int initialSecond;
	private int minute;
	private int initialMinute;
	private String time;
	private Timer timer;
	private SoundPlayer player;

	public TimerUI() {
		GridBagLayout layout = new GridBagLayout();
		super.setLayout(layout);
		createTimerButton();
		createTimerArea();

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showCountdown();
			}

			private void showCountdown() {
				setDisplay();
				if (minute == 0 && second == 0) {
					time = "Stop working";
					timerArea.setText(time);
					timer.stop();
					player.playSong();
					minute = initialMinute;
					second = initialSecond + 1;
				} else if (second == 0) {
					second = 60;
					minute--;
				}
				second--;
			}

			private void setDisplay() {
				if (minute < 10 && second < 10)
					time = "0" + String.valueOf(minute) + " : 0"
							+ String.valueOf(second);
				if (minute >= 10 && second < 10)
					time = String.valueOf(minute) + " : 0"
							+ String.valueOf(second);
				if (minute < 10 && second >= 10)
					time = "0" + String.valueOf(minute) + " : "
							+ String.valueOf(second);
				if (minute >= 10 && second >= 10)
					time = String.valueOf(minute) + " : "
							+ String.valueOf(second);
				timerArea.setText(time);
			}
		});

		timer.setInitialDelay(0);
	}

	private void createTimerButton() {
		timerButton = new JButton("Start");
		timerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				timer.start();

			}
		});
		Dimension dim = new Dimension(75, 50);
		timerButton.setPreferredSize(dim);
		super.add(timerButton);
	}

	private void createTimerArea() {
		timerArea = new JTextField("");
		timerArea.setEditable(false);
		timerArea.setHorizontalAlignment(JTextField.CENTER);
		timerArea.setBackground(new Color(248, 244, 255));
		timerArea.setBorder(BorderFactory.createEtchedBorder());
		Dimension dim = new Dimension(100, 50);
		timerArea.setPreferredSize(dim);
		super.add(timerArea);
	}

	public void setSecond(int second) {
		this.second = second;
		initialSecond = second;
	}

	public void setMinute(int minute) {
		this.minute = minute;
		initialMinute = minute;
	}

	public void setPlayer(SoundPlayer player) {
		this.player = player;
	}

}
