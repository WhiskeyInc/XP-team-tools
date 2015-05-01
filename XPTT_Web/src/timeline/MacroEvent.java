package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import filtering.Filter;

public class MacroEvent extends Event implements Timeline {

	private Timeline timeline;

	public MacroEvent(String name, GregorianCalendar fromDate,
			GregorianCalendar toDate, Timeline timeline) {
		super(name, toDate, false);
		this.timeline = timeline;
	}

	@Override
	public void addEvent(Event event) throws InvalidDateException {
		this.timeline.addEvent(event);
	}

	@Override
	public void deleteEvent(int eventId) throws NoSuchEventException,
			UnEditableEventException {
		this.timeline.deleteEvent(eventId);
	}

	@Override
	public void moveEvent(int eventId, GregorianCalendar newDate)
			throws UnEditableEventException, NoSuchEventException,
			InvalidDateException {
		this.timeline.moveEvent(eventId, newDate);
	}

	@Override
	public Event getEvent(int eventId) throws NoSuchEventException {
		return this.timeline.getEvent(eventId);
	}

	@Override
	public ArrayList<Event> getEvents(Filter<Event> filter) {
		return this.timeline.getEvents(filter);
	}

	@Override
	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}
}
