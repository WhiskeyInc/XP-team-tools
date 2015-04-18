package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.exceptions.UnEditableEventException;

/**
 * This class represent an event which has five characteristics: an
 * id(exclusive), a name (mandatory), a date, stored with the precision of a
 * second, a list of participant and a flag which indicates whether its date can
 * be changed or not. The default values for this flag is TRUE: it means the
 * date can be modified if not differently specified in the constructor.
 * 
 * This class implements {@link Comparable}: two Events are compared according
 * to their date
 * 
 * @author lele, simo, ale, andre
 *
 */
public class Event implements Comparable<Event> {

	private int id;
	private ArrayList<String> participants = new ArrayList<String>();
	private GregorianCalendar date;
	private String name;
	private boolean editable = true;

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
	public Event(String name, int id, GregorianCalendar date) {
		this.id = id;
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
	 * @param editable
	 *            : if its value is TRUE, the event can be modified, otherwise
	 *            it will be forever as specified in the constructor.
	 */
	public Event(String name, int id, GregorianCalendar date, boolean editable) {
		this.id = id;
		this.date = date;
		this.name = name;
		this.editable = editable;
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
	 * Changes the date of this event
	 * 
	 * @param newDate
	 *            : the date to set
	 * @throws UnEditableEventException
	 *             : if this event is fixed
	 * @see {@link Event}.isMovable()
	 */
	public void setDate(GregorianCalendar newDate)
			throws UnEditableEventException {
		if (!isEditable()) {
			throw new UnEditableEventException(this.name);
		}
		this.date = newDate;

	}

	/**
	 * Lets you know if the date of this event can be changed
	 * 
	 * @return TRUE if the date of this event can be changed, else FALSE
	 */
	public boolean isEditable() {
		return this.editable;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Event otherevent) {
		return -(this.getDate().compareTo(otherevent.getDate()));
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

	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
}
