package ui.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.TimerUI;

public class TimerUITest {
	public static void main(String[] args) {
		final TimerUI timerUI = new TimerUI();
		timerUI.setButtonTimerListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(timerUI.getTimeStamp());
			}
		});
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.getContentPane().add(timerUI);
		frame.setVisible(true);

	}

}