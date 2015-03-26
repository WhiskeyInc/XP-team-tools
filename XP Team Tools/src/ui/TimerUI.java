package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton timerButton;
	private JTextField timerArea;
	private int second;
	private int minute;
	private String time;
	private Timer timer;

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
				if (minute == 0 && second == 0) {
					time = "Stop working";
					timerArea.setText(time);
					timer.stop();
				} else if (second == 0) {
					second = 60;
					minute--;
				}
				second--;
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
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}
