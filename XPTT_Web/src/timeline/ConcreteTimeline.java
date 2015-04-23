package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import util.serialization.Serializable;
import util.serialization.SerializerCollector;
import filtering.Filter;

/**
 * This implementation of {@link Timeline} interface provides uniqueness control
 * for the events to be collected. This is guaranteed by the inherited methods
 * of {@link SerializerCollector} superclass.
 * 
 * @author simone
 * @see Event, {@link Timeline}, {@link SerializerCollector},
 *      {@link Serializable}
 *
 */
public class ConcreteTimeline extends SerializerCollector<Event> implements
		Timeline {

	/**
	 * The name of the event matching with this object's creation itself
	 */
	public static final String CREATION_EVENT = "creation";

	/**
	 * Creates a new instance of this class. When created, it will be
	 * automatically added and event to take note of this creation itself
	 */
	public ConcreteTimeline(TimeZone locale) {
		GregorianCalendar creationDate = (GregorianCalendar) Calendar
				.getInstance(locale);
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
	public void deleteEvent(int eventId) throws NoSuchEventException,
			UnEditableEventException {
		this.validateEvent(eventId);
		Event event = super.getItem(eventId);
		if (event.isEditable()) {
			super.deleteItem(eventId);
			return;
		}
		throw new UnEditableEventException(event.toString());
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
		this.validateEvent(eventId);
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
			return date.before(this.getEvent(SerializerCollector.FIRST_ID)
					.getDate());
		} catch (NoSuchEventException e) {
			throw new RuntimeException("Fatal error: creation not found");
		}
	}
}
