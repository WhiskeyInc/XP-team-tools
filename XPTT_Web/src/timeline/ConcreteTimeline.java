package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import util.serialization.SerializerCollector;
import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import filtering.Filter;

public class ConcreteTimeline extends SerializerCollector<Event> implements
		Timeline {

	public static final String CREATION_EVENT = "creation";

	public ConcreteTimeline() {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar
				.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		Event event = new Event(CREATION_EVENT, creationDate, false);
		super.addItem(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEventsNumber()
	 */
	@Override
	public int getEventsNumber() {
		return super.getItems().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#addEvent(timeline.Event)
	 */
	@Override
	public void addEvent(Event event) throws InvalidDateException {
		validateDate(event.getDate());
		super.addItem(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#dropEvent(java.lang.String)
	 */
	@Override
	public void deleteEvent(int eventId) throws NoSuchEventException{
		this.validateEvent(eventId);
		super.deleteItem(eventId);
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
		super.getItem(eventId).setDate(newDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvent(java.lang.String)
	 */
	@Override
	public Event getEvent(int eventId) throws NoSuchEventException {
		this.validateEvent(eventId);
		return super.getItem(eventId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timeline.Timeline#getEvents(filtering.Filter)
	 */
	@Override
	public ArrayList<Event> getEvents(Filter<Event> filter) {
		ArrayList<Event> filteredAndSortedEvents = new ArrayList<Event>();
		filteredAndSortedEvents.addAll(super.getItems());
		filteredAndSortedEvents = filter.filter(filteredAndSortedEvents);
		Collections.sort(filteredAndSortedEvents);
		return filteredAndSortedEvents;
	}

	private void validateEvent(int eventId) throws NoSuchEventException {
		if (!eventExists(eventId)) {
			throw new NoSuchEventException(eventId);
		}
	}

	private boolean eventExists(int eventId) {
		return (super.getItem(eventId) != null);
	}

	private void validateDate(GregorianCalendar date)
			throws InvalidDateException {
		if (dateBeforeCreation(date)) {
			throw new InvalidDateException(date);
		}
	}

	private boolean dateBeforeCreation(GregorianCalendar date) {
		try {
			return date.before(this.getEvent(SerializerCollector.FIRST_ID).getDate());
		} catch (NoSuchEventException e) {
			throw new RuntimeException("Fatal error: creation not found");
		}
	}
}
