package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import filtering.Filter;
import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.TasksManager;

public class TeamManager {

	private TasksManager taskManager = new TasksManager();
	private Timeline timeline = new Timeline();
	private TeamSettings settings;
	
	public TeamManager(TeamSettings settings) {
		this.settings = settings;
	}

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
		if (!settings.getPossibleStates().contains(targetState)) {
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

	private void checkMember(String member) throws InvalidMemberException {
		if (!settings.getTeamMembers().contains(member)) {
			throw new InvalidMemberException(member);
		}
	}

	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		return creationDate;
	}
	
	public ArrayList<Event> getEvents(Filter<Event> filter) {
		return filter.filter(timeline.getEvents());
	}	

	private void addDevelopersToEvent(String taskName, Event event) {
		event.addParticipants(taskManager.getTask(taskName).getDevelopers());
	}

	public void addTask(String taskName) {
		this.addTask(taskName, "");		
	}
}
