package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnmovableEventException;
import filtering.Filter;

public class ConcreteTimeline implements Timeline {

	private HashMap<String, Event> events = new HashMap<String, Event>();
	private static final String CREATION_EVENT = "creation";

	public ConcreteTimeline() {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar
				.getInstance();
		Event event = new Event(CREATION_EVENT, creationDate, false);
		events.put(event.toString(), event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEventsNumber()
	 */
	@Override
	public int getEventsNumber() {
		return this.events.keySet().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#addEvent(timeline.Event)
	 */
	@Override
	public void addEvent(Event event) throws InvalidDateException {
		try {
			if (event.getDate().before(this.getEvent(CREATION_EVENT).getDate())) {
				throw new InvalidDateException(event.getDate());
			}
		} catch (NoSuchEventException e) {}
		this.events.put(event.toString(), event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#dropEvent(java.lang.String)
	 */
	@Override
	public void deleteEvent(String eventName) throws NoSuchEventException {
		if (!isAnExistingEvent(eventName)) {
			throw new NoSuchEventException(eventName);
		}
		this.events.remove(eventName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#moveEvent(java.lang.String,
	 * java.util.GregorianCalendar)
	 */
	@Override
	public void moveEvent(String eventName, GregorianCalendar newDate)
			throws UnmovableEventException, NoSuchEventException {
		if (!isAnExistingEvent(eventName)) {
			throw new NoSuchEventException(eventName);
		}
		this.events.get(eventName).setDate(newDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvent(java.lang.String)
	 */
	@Override
	public Event getEvent(String eventName) throws NoSuchEventException {
		if (!isAnExistingEvent(eventName)) {
			throw new NoSuchEventException(eventName);
		}
		return this.events.get(eventName);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvents(filtering.Filter)
	 */
	@Override
	public ArrayList<Event> getEvents(Filter<Event> filter) {
		ArrayList<Event> filteredAndSortedEvents = new ArrayList<Event>();
		filteredAndSortedEvents.addAll(this.events.values());
		filteredAndSortedEvents = filter.filter(filteredAndSortedEvents);
		Collections.sort(filteredAndSortedEvents);
		return filteredAndSortedEvents;
	}
	
	private boolean isAnExistingEvent(String eventName) {
		boolean found = false;
		for (Event event : this.getEvents()) {
			if (event.toString().compareTo(eventName)==0) {
				found = true;
			}
		}
		return found;
	}

	private ArrayList<Event> getEvents() {
		ArrayList<Event> list = new ArrayList<Event>();
		list.addAll(this.events.values());
		return list;
	}
}
