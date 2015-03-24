package timeline;

import java.util.ArrayList;

public class Event {

	private ArrayList<String> participants = new ArrayList<String>();
	private String date;
	private String name;

	public Event(String name, String date) {
		this.date = date;
		this.name = name;
	}

	public void addParticipant(String participant) {
		this.participants.add(participant);		
	}

	public ArrayList<String> getParticipants() {
		return this.participants;
	}

	public String getDate() {
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

}
