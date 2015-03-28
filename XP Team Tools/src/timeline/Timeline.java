package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

import filtering.Filter;

public class Timeline {

	private HashMap<String, Event> events = new HashMap<String, Event>();

	public Timeline() {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar.getInstance();
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

	public void moveEvent(String eventName, GregorianCalendar newDate) {
		this.events.remove(eventName);
		Event newEvent = new Event(eventName, newDate);
		this.events.put(newEvent.toString(), newEvent);
	}

	public Event getEvent(String eventName){
		return this.events.get(eventName);
	}

	public ArrayList<Event> getEvents(Filter<Event> filter) {
		ArrayList<Event> filteredAndSortedEvents = new ArrayList<Event>();
		filteredAndSortedEvents.addAll(this.events.values());
		filteredAndSortedEvents = filter.filter(filteredAndSortedEvents);
		Collections.sort(filteredAndSortedEvents);
		return filteredAndSortedEvents;
	}
}
