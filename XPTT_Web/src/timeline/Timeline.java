package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import filtering.Filter;

public interface Timeline {

	public int getEventsNumber();

	public void addEvent(String eventName, boolean editable,
			GregorianCalendar date, ArrayList<String> participants)
			throws InvalidDateException;

	public void deleteEvent(int eventId) throws NoSuchEventException;

	public void moveEvent(int eventId, GregorianCalendar newDate)
			throws UnEditableEventException, NoSuchEventException,
			InvalidDateException;

	public Event getEvent(int eventId) throws NoSuchEventException;

	public ArrayList<Event> getEvents(Filter<Event> filter);

}