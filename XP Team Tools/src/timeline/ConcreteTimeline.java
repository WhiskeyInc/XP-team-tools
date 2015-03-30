package timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

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

	/* (non-Javadoc)
	 * @see timeline.Timeline#getEventsNumber()
	 */
	@Override
	public int getEventsNumber() {
		return this.events.keySet().size();
	}

	/* (non-Javadoc)
	 * @see timeline.Timeline#addEvent(timeline.Event)
	 */
	@Override
	public void addEvent(Event event) {
		this.events.put(event.toString(), event);
	}

	/* (non-Javadoc)
	 * @see timeline.Timeline#dropEvent(java.lang.String)
	 */
	@Override
	public void deleteEvent(String eventName) {
		this.events.remove(eventName);
	}

	/* (non-Javadoc)
	 * @see timeline.Timeline#moveEvent(java.lang.String, java.util.GregorianCalendar)
	 */
	@Override
	public void moveEvent(String eventName, GregorianCalendar newDate) throws UnmovableEventException{
		this.events.get(eventName).setDate(newDate);
	}

	/* (non-Javadoc)
	 * @see timeline.Timeline#getEvent(java.lang.String)
	 */
	@Override
	public Event getEvent(String eventName) {
		return this.events.get(eventName);
	}

	/* (non-Javadoc)
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
}
