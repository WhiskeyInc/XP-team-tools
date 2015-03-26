package timer.tests;

import javax.swing.JFrame;

import ui.TimerUI;
import ui.tests.FramesUtils;

public class TimerTest01 {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("Timer", 200, 200);
		TimerUI timer = new TimerUI();
		frame.getContentPane().add(timer);
		timer.setSecond(3);
		timer.setMinute(1);
		frame.setVisible(true);
	}
	

}
