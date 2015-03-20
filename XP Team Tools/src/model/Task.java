package model;

import java.util.ArrayList;

public class Task {

	private final static String TODO = "TODO";

	private String name;
	private String description;
	private String state;
	private ArrayList<String> participants = new ArrayList<String>();

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.state = TODO;
	}

	public void addParticipant(String participant) {
		this.participants.add(participant);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void changeState(String targetState) {
		this.state = targetState;		
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
