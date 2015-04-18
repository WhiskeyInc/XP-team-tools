package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerModel {

	private Timer timer;
	private String timeStamp;
	public static final int TOTAL_SECONDS = 1000;
	private long totalTime;
	private boolean isChanged = false;
	
	public TimerModel(long totalTime) {
		this.totalTime = totalTime;
	}

	public void startTimer() {
		

		
		timer = new Timer(TOTAL_SECONDS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(totalTime==0) timer.stop();
				timeStamp = TimerFormatter.getTimeStamp(totalTime);
				isChanged = true;
				totalTime -= TOTAL_SECONDS;
				System.out.println(timeStamp);
			}
		});

		timer.setInitialDelay(0);
		timer.start();
		
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

}
