package model;

import java.util.ArrayList;

public class Task {

	private final static String TODO = "TODO";
	private final static String IN_PROGRESS = "IN PROGRESS";
	private final static String DONE = "DONE";

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

	public void addPartecipant(String partecipant) {
		this.participants.add(partecipant);
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
		this.state = IN_PROGRESS;
	}

	public void toDone() {
		this.state = DONE;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
