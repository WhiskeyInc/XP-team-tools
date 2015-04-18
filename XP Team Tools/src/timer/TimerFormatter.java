package timer;

public class TimerFormatter {
	
	public static String getTimeStamp(long millis){
		long secondiTot = millis / 1000;
		long minuti = secondiTot / 60;
		long secondi = secondiTot % 60;
		
		return getDisplay(minuti, secondi);
	}
	
	private static String getDisplay(long minute, long second) {
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

}
