package timer.tests;

import javax.swing.JFrame;

import sounds.SoundPlayer;
import ui.TimerUI;
import ui.tests.FramesUtils;

public class TimerTest01 {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("Timer", 200, 200);
		TimerUI timer = new TimerUI();
		frame.getContentPane().add(timer);
		timer.setSecond(0);
		timer.setMinute(1);
		SoundPlayer player = new SoundPlayer("sounds/TempleBell.wav");
		timer.setPlayer(player);
		frame.setVisible(true);
	}
	

}
