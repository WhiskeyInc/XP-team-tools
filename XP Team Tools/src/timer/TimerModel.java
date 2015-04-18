package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerModel {

	private Timer timer;
	private String timeStamp;
	public static final int TOTAL_MILLIS = 1000;
	private long totalMillis;
	private boolean isChanged = false;
	
	public TimerModel(long totalMillis) {
		this.totalMillis = totalMillis;
	}

	public void startTimer(String teamName) {
		
		timer = new Timer(TOTAL_MILLIS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(totalMillis==0) {
					timer.stop();
				}
			//	timeStamp = TimerFormatter.getTimeStamp(totalMillis);
				isChanged = true;
				totalMillis -= TOTAL_MILLIS;
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
