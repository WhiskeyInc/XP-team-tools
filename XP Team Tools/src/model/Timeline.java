package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Timeline {

	private HashMap<String, String> dates = new HashMap<String, String>();

	public Timeline() {
		String creationDate = creationDate();
		dates.put("creation", creationDate);
	}

	private String creationDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MM yyyy");
		Calendar currentCalendar = Calendar.getInstance();
		String creationDate = dateFormatter.format(currentCalendar.getTime());
		return creationDate;
	}

	public int getEventsNumber() {
		return this.dates.keySet().size();
	}

	public void newEvent(String event, String date) {
		this.dates.put(event, date);
	}

	public void dropEvent(String event) {
		this.dates.remove(event);
	}

	public String getEventDate(String string) {
		return this.dates.get(string);
	}
}
