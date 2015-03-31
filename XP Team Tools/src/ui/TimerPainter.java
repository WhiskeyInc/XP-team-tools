package ui;

import java.awt.Color;

import javax.swing.JButton;

public class TimerPainter {
	
	private JButton button1;
	private JButton button2;
	
	public TimerPainter(JButton button1, JButton button2) {
		this.button1 = button1;
		this.button2 = button2;
	}
	
	public void colorWorkingTimeBackground() {
		button1.setBackground(Color.GREEN);
		button2.setBackground(Color.GREEN);
	}

	public void colorPauseTimeBackground() {
		button1.setBackground(Color.RED);
		button2.setBackground(Color.RED);
	}

	public void colorStopWorkingTimeBackGround() {
		button1.setBackground(Color.ORANGE);
		button2.setBackground(Color.ORANGE);
	}

}
