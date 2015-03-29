package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

import tests.FixedEventException;
import filtering.Filter;

public class Timeline {

	private HashMap<String, Event> events = new HashMap<String, Event>();
	private static final String CREATION_EVENT = "creation";

	public Timeline() {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar
				.getInstance();
		Event event = new Event(CREATION_EVENT, creationDate, false);
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

	public void moveEvent(String eventName, GregorianCalendar newDate) throws FixedEventException{
		this.events.get(eventName).setDate(newDate);
	}

	public Event getEvent(String eventName) {
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
