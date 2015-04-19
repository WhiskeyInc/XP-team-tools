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

	private static final int CREATION_EVENT_ID = 0;
	private static final String CREATION_EVENT = "creation";

	private HashMap<Integer, Event> events = new HashMap<Integer, Event>();
	private int nextEventId = 1;

	public ConcreteTimeline() {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar
				.getInstance();
		Event event = new Event(CREATION_EVENT, creationDate, false);
		event.setId(CREATION_EVENT_ID);
		events.put(event.getId(), event);
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
	public void addEvent(Event event)
			throws InvalidDateException {
		event.setId(nextEventId);
		validateDate(event.getDate());
		this.events.put(event.getId(), event);
		updateNextEventId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#dropEvent(java.lang.String)
	 */
	@Override
	public void deleteEvent(int eventId) throws NoSuchEventException {
		this.events.remove(this.getEvent(eventId).getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#moveEvent(java.lang.String,
	 * java.util.GregorianCalendar)
	 */
	@Override
	public void moveEvent(int eventId, GregorianCalendar newDate)
			throws UnEditableEventException, NoSuchEventException,
			InvalidDateException {
		this.validateDate(newDate);
		this.getEvent(eventId).setDate(newDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvent(java.lang.String)
	 */
	@Override
	public Event getEvent(int eventId) throws NoSuchEventException {
		this.validateEvent(eventId);
		return this.events.get(eventId);
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

	private void validateEvent(int eventId) throws NoSuchEventException {
		if (!EventExists(eventId)) {
			throw new NoSuchEventException(eventId);
		}
	}

	private boolean EventExists(int eventId) {
		return this.events.containsKey(eventId);
	}

	private void validateDate(GregorianCalendar date)
			throws InvalidDateException {
		if (dateBeforeCreation(date)) {
			throw new InvalidDateException(date);
		}
	}

	private boolean dateBeforeCreation(GregorianCalendar date) {
		try {
			return date.before(this.getEvent(CREATION_EVENT_ID).getDate());
		} catch (NoSuchEventException e) {
			throw new RuntimeException("Fatal error: creation not found");
		}
	}

	private void updateNextEventId() {
		this.nextEventId = this.nextEventId + 1;
	}

}
