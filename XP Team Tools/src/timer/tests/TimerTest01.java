package timer.tests;

import javax.swing.JFrame;

import sounds.SoundPlayer;
import ui.TimerUI;
import ui.tests.FramesUtils;

public class TimerTest01 {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("Timer", 500, 200);
		TimerUI timer = new TimerUI();
		frame.getContentPane().add(timer);
		timer.setSecond(5);
		timer.setMinute(0);
		timer.setPauseMinute(0);
		timer.setPauseSecond(3);
		SoundPlayer player = new SoundPlayer("sounds/cannon.wav");
		timer.setPlayer(player);
		frame.setVisible(true);
	}
	

}