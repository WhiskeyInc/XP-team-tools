package timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Event implements Comparable<Event> {

	private ArrayList<String> participants = new ArrayList<String>();
	private GregorianCalendar date;
	private String name;

	public Event(String name, GregorianCalendar date) {
		this.date = date;
		this.name = name;
	}

	public void addParticipant(String participant) {
		this.participants.add(participant);		
	}

	public ArrayList<String> getParticipants() {
		return this.participants;
	}

	public GregorianCalendar getDate() {
		return this.date;
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}

	public void addParticipants(ArrayList<String> developers) {
		this.participants.addAll(developers);		
	}

	@Override
	public int compareTo(Event otherevent) {
		return this.getDate().compareTo(otherevent.getDate());
	}

}
