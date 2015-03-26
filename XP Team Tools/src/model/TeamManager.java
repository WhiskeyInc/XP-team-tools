package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.TasksManager;

public class TeamManager {

	private TasksManager taskManager = new TasksManager();
	private Timeline timeline = new Timeline();
	private ArrayList<String> members = new ArrayList<String>();
	private ArrayList<String> possibleStates = new ArrayList<String>();

	public void addTask(String taskName, String description) {
		this.taskManager.addTask(taskName, description);
		this.timeline.addEvent(new Event("Created task: " + taskName,
				getCurrentDate()));
	}

	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}

	public void deleteTask(String taskName) {
		Event event = new Event("Deleted task: " + taskName, getCurrentDate());
		addDevelopersToEvent(taskName, event);
		this.taskManager.deleteTask(taskName);
		timeline.addEvent(event);
	}

	public Event getEvent(String eventName) {
		return this.timeline.getEvent(eventName);
	}

	public void moveTaskToState(String taskName, String targetState) throws InvalidStateException {
		checkState(targetState);
		this.taskManager.moveTaskToState(taskName, targetState);
		Event event = new Event("Changed state of task " + taskName
				+ ": now it is " + targetState, this.getCurrentDate());
		addDevelopersToEvent(taskName, event);
		this.timeline.addEvent(event);
	}

	private void checkState(String targetState) throws InvalidStateException {
		if (!possibleStates.contains(targetState)) {
			throw new InvalidStateException(targetState);
		}		
	}

	public void addDeveloperTo(String taskName, String developer)
			throws InvalidMemberException {
		checkTask(taskName);
		checkMember(developer);
		taskManager.getTask(taskName).addDeveloper(developer);
		Event event = new Event("Added " + developer + " to task: " + taskName,
				getCurrentDate());
		event.addParticipant(developer);
		timeline.addEvent(event);
	}

	private void checkTask(String taskName) {
		Task task = taskManager.getTask(taskName);
		if (task == null) {
			taskManager.addTask(taskName);
		}
	}

	public void addMember(String member) {
		members.add(member);
		Event event = new Event("Added member: " + member, getCurrentDate());
		event.addParticipant(member);
		timeline.addEvent(event);
	}

	public ArrayList<Event> getEvents(String member) throws InvalidMemberException {
		checkMember(member);
		return timeline.getEvents(member);
	}

	private void checkMember(String member) throws InvalidMemberException {
		if (!members.contains(member)) {
			throw new InvalidMemberException(member);
		}
	}

	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		return creationDate;
	}
	

	private void addDevelopersToEvent(String taskName, Event event) {
		event.addParticipants(taskManager.getTask(taskName).getDevelopers());
	}

	public void addTask(String taskName) {
		this.addTask(taskName, "");		
	}

	public void setPossibleStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleStates.add(state);
		}	
	}

	public ArrayList<Event> getEvents() {
		return this.timeline.getEvents();
	}
}
