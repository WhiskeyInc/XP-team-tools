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
 * countdown. A "semaphor" helps the user to identify immediately the time.
 * GREEN: working time YELLOW: the user must make a choice, he can take a short
 * break or restart the timer RED: break time
 * 
 * @author alessandro B.
 * 
 */
public class TimerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton timerButton;
	private JTextField timerArea;
	private JButton pauseButton;
	private int second;
	private int initialSecond;
	private int minute;
	private int initialMinute;
	private int pauseSecond;
	private int pauseMinute;
	private String time;
	private Timer timer;
	private SoundPlayer player;
	private TimerPainter painter;
	private boolean timeOver;
	private boolean PauseIndex;

	public TimerUI() {
		GridBagLayout layout = new GridBagLayout();
		super.setLayout(layout);
		createTimerButton();
		createTimerArea();
		createPauseButton();
		painter = new TimerPainter(timerButton, pauseButton);

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showCountdown();
			}

			private void showCountdown() {
				setDisplay();
				if (minute == 0 && second == 0) {
					timeOverActions();
				} else if (second == 0) {
					second = 60;
					minute--;
				}
				second--;
			}
		});

		timer.setInitialDelay(0);
	}

	private void setDisplay() {
		if (minute < 10 && second < 10)
			time = "0" + String.valueOf(minute) + " : 0"
					+ String.valueOf(second);
		if (minute >= 10 && second < 10)
			time = String.valueOf(minute) + " : 0" + String.valueOf(second);
		if (minute < 10 && second >= 10)
			time = "0" + String.valueOf(minute) + " : "
					+ String.valueOf(second);
		if (minute >= 10 && second >= 10)
			time = String.valueOf(minute) + " : " + String.valueOf(second);
		timerArea.setText(time);
	}

	private void timeOverActions() {
		if (!PauseIndex) {
			workingTimeEndingActions();
		}
		timerArea.setText(time);
		timer.stop();
		player.playSong();
		minute = initialMinute;
		second = initialSecond + 1;
		timeOver = true;
		setButtonText();
		if (PauseIndex) {
			pauseEndingActions();
		}
	}

	private void pauseEndingActions() {
		PauseIndex = false;
		timeOver = false;
		timer.start();
		deleteButtonText();
		painter.colorWorkingTimeBackground();
	}

	private void workingTimeEndingActions() {
		time = "Stop working";
		painter.colorStopWorkingTimeBackGround();
	}

	private void setButtonText() {
		timerButton.setText("Restart");
		pauseButton.setText("Take a break!!!");
	}

	private void createPauseButton() {
		pauseButton = new JButton("");
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (timeOver) {
					second = pauseSecond;
					minute = pauseMinute;
					timer.start();
					PauseIndex = true;
					deleteButtonText();
					timeOver = false;
					painter.colorPauseTimeBackground();
				}
			}
		});
		Dimension dim = new Dimension(180, 50);
		pauseButton.setPreferredSize(dim);
		super.add(pauseButton);
	}

	private void createTimerButton() {
		timerButton = new JButton("Start");
		timerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				timerButtonAction();
			}

			private void timerButtonAction() {
				timer.start();
				deleteButtonText();
				timeOver = false;
				if (!PauseIndex) {
					painter.colorWorkingTimeBackground();
				}
			}

		});
		Dimension dim = new Dimension(180, 50);
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

	public void setPauseMinute(int pauseMinute) {
		this.pauseMinute = pauseMinute;
	}

	public void setPauseSecond(int pauseSecond) {
		this.pauseSecond = pauseSecond;
	}

	public void setPlayer(SoundPlayer player) {
		this.player = player;
	}

	private void deleteButtonText() {
		timerButton.setText("");
		pauseButton.setText("");
	}

}
