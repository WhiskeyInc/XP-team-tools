package timer;

public class TimerFormatter {
	
	public static int[] getTimeStamp(long millis){
		Integer secondiTot = (int)millis / 1000;
		
		int[] vett = new int[2];
		vett[0] = secondiTot / 60;
		vett[1] = secondiTot % 60;
		
		return vett;
	}
	
	public static String getDisplay(int minute, int second) {
		String display = "";
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
	
	public static long getMillis(int minutes, int seconds) {
		return (minutes*60 + seconds)*1000;
	}

}
