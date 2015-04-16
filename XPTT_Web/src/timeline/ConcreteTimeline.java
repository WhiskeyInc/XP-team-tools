package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
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
		validateDate(event.getDate());
		this.events.put(event.toString(), event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#dropEvent(java.lang.String)
	 */
	@Override
	public void deleteEvent(String eventName) throws NoSuchEventException {
		this.events.remove(this.getEvent(eventName).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#moveEvent(java.lang.String,
	 * java.util.GregorianCalendar)
	 */
	@Override
	public void moveEvent(String eventName, GregorianCalendar newDate)
			throws UnEditableEventException, NoSuchEventException, InvalidDateException {
		this.validateDate(newDate);
		this.getEvent(eventName).setDate(newDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvent(java.lang.String)
	 */
	@Override
	public Event getEvent(String eventName) throws NoSuchEventException {
		this.validateEvent(eventName);
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
	
	private void validateEvent(String eventName) throws NoSuchEventException {
		if(!EventExists(eventName)){
			throw new NoSuchEventException(eventName);
		}
	}

	private boolean EventExists(String eventName) {
		return this.events.containsKey(eventName);
	}
	
	private void validateDate(GregorianCalendar date) throws InvalidDateException {
		if(dateBeforeCreation(date)){
			throw new InvalidDateException(date);
		}
	}

	private boolean dateBeforeCreation(GregorianCalendar date) {
		try {
			return date.before(this.getEvent(CREATION_EVENT).getDate());
		} catch (NoSuchEventException e) {
			throw new RuntimeException("Fatal error: creation not found");
		}
	}
}
