package timer;

/**
 * A class that manages the countdown to be shown on the display
 * 
 * @author alessandro B
 *
 */
public class TimerManager {
	
	private int second;
	private int initialSecond;
	private int minute;
	private int initialMinute;
	private int pauseSecond;
	private int pauseMinute;
	private String display;
	
	public String getDisplay() {
		if (minute < 10 && second < 10)
			display = "0" + String.valueOf(minute) + " : 0"
					+ String.valueOf(second);
		if (minute >= 10 && second < 10)
			display = String.valueOf(minute) + " : 0" + String.valueOf(second);
		if (minute < 10 && second >= 10)
			display = "0" + String.valueOf(minute) + " : "
					+ String.valueOf(second);
		if (minute >= 10 && second >= 10)
			display = String.valueOf(minute) + " : " + String.valueOf(second);
		return display;
	}
	
	public void minuteTerminatedActions(){
		second = 60;
		minute--;
	}
	
	public void secondDecrementer(){
		second--;
	}

	public void setTimer(){
		second = initialSecond + 1;
		minute = initialMinute;
	}
	
	public void setPause(){
		second = pauseSecond;
		minute = pauseMinute;
	}
	
	public void setInitialSecond(int initialSecond) {
		this.initialSecond = initialSecond;
		second = initialSecond;
	}
	
	public void setInitialMinute(int initialMinute) {
		this.initialMinute = initialMinute;
		minute = initialMinute;
	}

	public void setPauseMinute(int pauseMinute) {
		this.pauseMinute = pauseMinute;
	}

	public void setPauseSecond(int pauseSecond) {
		this.pauseSecond = pauseSecond;
	}
	
	public int getSecond() {
		return second;
	}
	
	public int getMinute() {
		return minute;
	}
}
