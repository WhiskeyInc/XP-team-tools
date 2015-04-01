package timer.tests;

import javax.swing.JFrame;

import sounds.SoundPlayer;
import timer.utils.TimerIndexManager;
import timer.utils.TimerManager;
import ui.TimerUI;
import ui.tests.FramesUtils;

public class TimerTest01 {
	
	public static void main(String[] args) {
		
		JFrame frame = FramesUtils.createFrame("Timer", 500, 200);
		TimerUI timer = new TimerUI();
		TimerManager manager = new TimerManager();
		frame.getContentPane().add(timer);
		manager.setInitialMinute(0);
		manager.setInitialSecond(5);
		manager.setPauseMinute(0);
		manager.setPauseSecond(3);
		timer.setManager(manager);
		timer.setIndexManager(new TimerIndexManager(manager));
		SoundPlayer player = new SoundPlayer("sounds/cannon.wav");
		timer.setPlayer(player);
		frame.setVisible(true);
	}
	

}