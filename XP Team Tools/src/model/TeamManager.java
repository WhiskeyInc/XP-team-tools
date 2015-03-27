package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.TasksManager;
import boards.UserStoriesManager;
import filtering.Filter;

public class TeamManager {

	private final static String GENERAL = "GENERAL";

	private TasksManager generalTaskManager = new TasksManager();
	private UserStoriesManager userstoryManager = new UserStoriesManager();
	private Timeline timeline = new Timeline();
	private TeamSettings settings;

	public TeamManager(TeamSettings settings) {
		this.settings = settings;
	}

	public void addTask(String taskName, String description, String title) throws NameAlreadyInUseException {
		getTaskManager(title).addTask(taskName, description);
		this.timeline.addEvent(new Event("Created task: " + taskName + " for: "
				+ title, getCurrentDate()));
	}

	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}

	public void deleteTask(String taskName, String title) {
		Event event = new Event(
				"Deleted task: " + taskName + " from: " + title,
				getCurrentDate());
		addDevelopersToEvent(taskName, event, title);
		getTaskManager(title).deleteTask(taskName);
		timeline.addEvent(event);
	}

	public Event getEvent(String eventName) {
		return this.timeline.getEvent(eventName);
	}

	public void moveTaskToState(String taskName, String targetState,
			String title) throws InvalidStateException {
		checkTasksState(targetState);
		getTaskManager(title).moveTaskToState(taskName, targetState);
		Event event = new Event("Changed state of task " + taskName + " of: "
				+ title + ". Now it is " + targetState, this.getCurrentDate());
		addDevelopersToEvent(taskName, event, title);
		this.timeline.addEvent(event);
	}

	private void checkTasksState(String targetState) throws InvalidStateException {
		if (!settings.getPossibleTasksStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}
	}

	public void addDeveloperTo(String taskName, String developer, String title)
			throws InvalidMemberException, NameAlreadyInUseException {
		checkTask(taskName, title);
		checkMember(developer);
		getTaskManager(title).getTask(taskName).addDeveloper(developer);
		Event event = new Event("Added " + developer + " to task: " + taskName
				+ " of: " + title, getCurrentDate());
		event.addParticipant(developer);
		timeline.addEvent(event);
	}

	private void checkTask(String taskName, String title) throws NameAlreadyInUseException {
		Task task = getTaskManager(title).getTask(taskName);
		if (task == null) {
			getTaskManager(title).addTask(taskName);
		}
	}

	private void checkMember(String member) throws InvalidMemberException {
		if (!settings.getTeamMembers().contains(member)) {
			throw new InvalidMemberException(member);
		}
	}

	private GregorianCalendar getCurrentDate() {
		return (GregorianCalendar) Calendar.getInstance();
	}

	public ArrayList<Event> getEvents(Filter<Event> filter) {
		return timeline.getEvents(filter);
	}

	private void addDevelopersToEvent(String taskName, Event event, String title) {
		event.addParticipants(getTaskManager(title).getTask(taskName)
				.getDevelopers());
	}

	public void addTask(String taskName, String title ) throws NameAlreadyInUseException {
		this.addTask(taskName, "", title);
	}

	public void addUserStory(String title, String description) throws NameAlreadyInUseException {
		this.userstoryManager.addUserStory(title, description);
		this.timeline.addEvent(new Event("Created userstory: " + title,
				getCurrentDate()));
	}

	public void addUserStory(String title) throws NameAlreadyInUseException {
		this.addUserStory(title, "");
	}

	public void deleteUserStory(String title) {
		Event event = new Event("Deleted userstory: " + title, getCurrentDate());
		this.userstoryManager.deleteUserStory(title);
		;
		timeline.addEvent(event);
	}

	public void moveStoryToState(String title, String targetState)
			throws InvalidStateException {
		checkUserStoriesState(targetState);
		this.userstoryManager.moveStoryToState(title, targetState);
		Event event = new Event("Changed state of userstory " + title
				+ ": now it is " + targetState, this.getCurrentDate());
		this.timeline.addEvent(event);
	}

	private void checkUserStoriesState(String targetState) throws InvalidStateException {
		if (!settings.getPossibleUserStoriesStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}		
	}

	private TasksManager getTaskManager(String title) {
		if (title.compareTo(GENERAL) == 0) {
			return this.generalTaskManager;
		} else {
			return userstoryManager.getUserStory(title).getTasksManager();
		}
	}
}
