package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Timeline {

	private HashMap<String, Event> events = new HashMap<String, Event>();

	public Timeline() {
		String creationDate = creationDate();
		Event event = new Event("creation", creationDate);
		events.put(event.toString(), event);
	}

	public int getEventsNumber() {
		return this.events.keySet().size();
	}

	public void addEvent(Event event) {
		this.events.put(event.toString(), event);
	}

	public void dropEvent(String eventName) {
		this.events.remove(eventName);
	}

	public void moveEvent(String eventName, String newDate) {
		this.events.remove(eventName);
		Event newEvent = new Event(eventName, newDate);
		this.events.put(newEvent.toString(), newEvent);
	}

	public Event getEvent(String eventName){
		return this.events.get(eventName);
	}
	
	private String creationDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MM yyyy");
		Calendar currentCalendar = Calendar.getInstance();
		String creationDate = dateFormatter.format(currentCalendar.getTime());
		return creationDate;
	}
}
