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
import timer.TimerIndexManager;
import timer.TimerManager;
import timer.TimerPainter;

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
	private Timer timer;
	private SoundPlayer player;
	private TimerPainter painter;
	private TimerManager manager;
	private TimerIndexManager indexManager;

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
				timerArea.setText(manager.getDisplay());
				if (indexManager.isTimeTerminated()) {
					timeOverActions();
				} else if (indexManager.isMinuteTerminated()) {
					manager.minuteTerminatedActions();
				}
				manager.secondDecrementer();
			}
		});

		timer.setInitialDelay(0);
	}


	private void timeOverActions() {
		if (!indexManager.isPauseIndex()) {
			workingTimeEndingActions();
		}
		timer.stop();
		player.playSong();
		manager.setTimer();
		indexManager.setTimeOver(true);
		setButtonText();
		if (indexManager.isPauseIndex()) {
			pauseEndingActions();
		}
	}

	private void pauseEndingActions() {
		indexManager.setPauseIndex(false);
		indexManager.setTimeOver(false);
		timer.start();
		deleteButtonText();
		painter.colorWorkingTimeBackground();
	}

	private void workingTimeEndingActions() {
		timerArea.setText("Stop working");
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

				if (indexManager.isTimeOver()) {
					manager.setPause();
					timer.start();
					indexManager.setPauseIndex(true);
					deleteButtonText();
					indexManager.setTimeOver(false);
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
				indexManager.setTimeOver(false);
				if (!indexManager.isPauseIndex()) {
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

	public void setPlayer(SoundPlayer player) {
		this.player = player;
	}
	
	public void setManager(TimerManager manager) {
		this.manager = manager;
	}
	public void setIndexManager(TimerIndexManager indexManager) {
		this.indexManager = indexManager;
	}

	private void deleteButtonText() {
		timerButton.setText("");
		pauseButton.setText("");
	}

}
