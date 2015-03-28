package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class represent an event which has four characteristics: a name
 * (mandatory), a date, stored with the precision of a second, a list of
 * participant and a flag which indicates whether its date can be changed or
 * not. The default values for this flag is TRUE: it means the date can be
 * modified if not differently specified in the constructor.
 * 
 * This class implements {@link Comparable}: two Events are compared according
 * to their date
 * 
 * @author lele, simo, ale, andre
 *
 */
public class Event implements Comparable<Event> {

	private ArrayList<String> participants = new ArrayList<String>();
	private GregorianCalendar date;
	private String name;
	private boolean movable = true;

	/**
	 * Creates an Event and assigns it a name and a date. Default value is
	 * assumed to be valid for the other attributes, so that the Event can be
	 * moved to another date.
	 * 
	 * @param name
	 *            : the name of the Event.
	 * @param date
	 *            : the date the Event is assumed to take place at.
	 */
	public Event(String name, GregorianCalendar date) {
		this.date = date;
		this.name = name;
	}

	/**
	 * Creates an Event and assigns it a name and a date. Moreover, lets you
	 * choose if its date can be modified or not.
	 * 
	 * @param name
	 *            : the name of the Event.
	 * @param date
	 *            : the date the Event is assumed to take place at.
	 * @param movable
	 *            : if its value is TRUE, the date can be modified, otherwise it
	 *            will be forever the one specified in the constructor.
	 */
	public Event(String name, GregorianCalendar date, boolean movable) {
		this.date = date;
		this.name = name;
		this.movable = movable;
	}

	/**
	 * Adds a participant to the Event. It is identified by its name.
	 * 
	 * @param participant
	 *            : the name of the participant.
	 */
	public void addParticipant(String participant) {
		this.participants.add(participant);
	}

	/**
	 * Adds all the people in the list to the Event. Any participant is
	 * identified by its name.
	 * 
	 * @param participant
	 *            : the name of the participant.
	 */
	public void addParticipants(ArrayList<String> developers) {
		this.participants.addAll(developers);
	}

	/**
	 * Returns all the participants to the Event.
	 * 
	 * @return the list with every participant.
	 */
	public ArrayList<String> getParticipants() {
		return this.participants;
	}

	/**
	 * Returns the date of the Event with a precision up to one second
	 * 
	 * @return the date of the event
	 */
	public GregorianCalendar getDate() {
		return this.date;
	}

	/**
	 * Lets yuo know if the date of this event can be changed
	 * 
	 * @return TRUE if the date of this event can be changed, else FALSE
	 */
	public boolean isMovable() {
		return this.movable;
	}

	@Override
	public int compareTo(Event otherevent) {
		return this.getDate().compareTo(otherevent.getDate());
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}

}
