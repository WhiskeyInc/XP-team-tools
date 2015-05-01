package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import filtering.Filter;

public class MacroEvent extends Event implements Timeline {

	private Timeline timeline;
	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;

	public MacroEvent(String name, GregorianCalendar fromDate,
			GregorianCalendar toDate, Timeline timeline) throws InvalidDateException {
		super(name, toDate, false);
		this.timeline = timeline;
		this.validateFromDate(fromDate);
		this.fromDate = fromDate;
		this.validateToDate(toDate);
		this.toDate = toDate;
	}

	@Override
	public void addEvent(Event event) throws InvalidDateException {
		validateEvent(event);
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
	
	private void validateEvent(Event event) throws InvalidDateException {
		if (isValidDate(event)) {
			throw new InvalidDateException(event.getDate());
		}
	}

	private boolean isValidDate(Event event) {
		return !(event.getDate().after(this.fromDate) && event.getDate().before(
				this.toDate));
	}
	
	private void validateFromDate(GregorianCalendar fromDate)
			throws InvalidDateException {
		if(fromDate.before(GregorianCalendar.getInstance())){
			throw new InvalidDateException(fromDate);
		}
	}
	
	private void validateToDate(GregorianCalendar toDate) throws InvalidDateException {
		if(toDate.before(this.fromDate)){
			throw new InvalidDateException(fromDate);
		}
		
	}
}
