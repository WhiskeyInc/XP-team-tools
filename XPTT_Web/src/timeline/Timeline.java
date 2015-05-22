package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import util.serialization.Serializable;
import filtering.Filter;

/**
 * Timeline interface represents a generic timeline object, which means a
 * collection of {@link Event} instances. It provides basic operations and date
 * changing. It also provides filtering over an implementation of {@link Filter}
 * interface. Since {@link Event} extends {@link Serializable} the access to a
 * collected event is made by its identifier.
 * 
 * @author simone, lele, andre, incre
 * @see Event
 *
 */
public interface Timeline {

	/**
	 * Adds an {@link Event} instance to the collection.
	 * 
	 * @param event
	 *            : the object to be added.
	 * @throws InvalidDateException
	 *             : if the date is considered as invalid.
	 */
	public void addEvent(Event event) throws InvalidDateException;

	/**
	 * Removes an event from this collection
	 * 
	 * @param eventId
	 *            : the identifier of the event.
	 * @throws NoSuchEventException
	 *             : if there is no such event in the collection, and thus cannot be removed.
	 * @throws UnEditableEventException
	 *             : if the event is not editable, and thus cannot be removed.
	 * 
	 * @see {@link Event#isEditable()}
	 */
	public void deleteEvent(int eventId) throws NoSuchEventException,
			UnEditableEventException;

	/**
	 * Moves a particular event from a date to another
	 * 
	 * @param eventId
	 *            : the identifier of the event
	 * @param newDate
	 *            : the new date of this event
	 * @throws UnEditableEventException
	 *             : if this event actually cannot be moved
	 * @throws NoSuchEventException
	 *             : if there is no such event in the collection
	 * @throws InvalidDateException
	 *             : if the new date is considered invalid
	 */
	public void moveEvent(int eventId, GregorianCalendar newDate)
			throws UnEditableEventException, NoSuchEventException,
			InvalidDateException;

	/**
	 * Returns a single event
	 * 
	 * @param eventId
	 *            : the identifier of the event
	 * @return the instance of {@link Event} whose id matches the parameter
	 * @throws NoSuchEventException
	 *             : if no event matches the parameter
	 */
	public Event getEvent(int eventId) throws NoSuchEventException;

	/**
	 * Returns a filtered list of events from the collection
	 * 
	 * @param filter
	 *            : the algorithm to perform filtering
	 * @return: an {@link ArrayList} of events
	 * @see Filter
	 */
	public ArrayList<Event> getEvents(Filter<Event> filter);

	/**
	 * Returns the number of events currently collected
	 * 
	 * @return: an integer representing the number of events in the Timeline
	 */
	public int getEventsNumber();

}