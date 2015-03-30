package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.UnmovableEventException;
import filtering.Filter;

public interface Timeline {

	public int getEventsNumber();

	public void addEvent(Event event);

	public void deleteEvent(String eventName);

	public void moveEvent(String eventName, GregorianCalendar newDate)
			throws UnmovableEventException;

	public Event getEvent(String eventName);

	public ArrayList<Event> getEvents(Filter<Event> filter);

}