package model;

import java.util.ArrayList;

public class Task {

	private final static String STATE1 = "TODO";
	private final static String STATE2 = "IN PROGRESS";
	private final static String STATE3 = "DONE";

	private String name;
	private String description;
	private String state;
	private ArrayList<String> partecipants = new ArrayList<String>();

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.state = STATE1;
	}

	public void addPartecipant(String partecipant) {
		this.partecipants.add(partecipant);
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

	public void toInProgress() {
		this.state = STATE2;
	}

	public void toDone() {
		this.state = STATE3;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
