package timer;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class TimerModel {
	
	private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			System.out.println("Stop working");
			Toolkit.getDefaultToolkit().beep();
		}
	};
	
	public TimerTask getTask() {
		return task;
	}
	
	public Timer getTimer() {
		return timer;
	}
}
