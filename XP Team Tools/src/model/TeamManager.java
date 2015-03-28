package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.TasksManager;
import boards.UserStoriesManager;
import boards.UserStory;
import filtering.Filter;

public class TeamManager {

	private final static String GENERAL = "GENERAL";

	private TasksManager generalTaskManager = new TasksManager();
	private UserStoriesManager userStoryManager = new UserStoriesManager();
	private Timeline timeline = new Timeline();
	private TeamSettings settings;
	

	public TeamManager(TeamSettings settings) {
		this.settings = settings;
	}
	
	
	
	public void addTask(String taskName, String title) throws NameAlreadyInUseException {
		this.addTask(taskName, "", title);
		this.timeline.addEvent(new Event("Created task: " + taskName + " for: "
				+ title, getCurrentDate(), false));
	}

	public void addTask(String taskName, String description, String title) throws NameAlreadyInUseException {
		this.getTaskManager(title).addTask(taskName, description);
		this.timeline.addEvent(new Event("Created task: " + taskName + " for: "
				+ title, getCurrentDate(), false));
	}

	public void deleteTask(String taskName, String title) {
		Event event = new Event(
				"Deleted task: " + taskName + " from: " + title,
				getCurrentDate(), false);
		addDevelopersToEvent(taskName, event, title);
		getTaskManager(title).deleteTask(taskName);
		timeline.addEvent(event);
	}
	
	public void moveTaskToState(String taskName, String targetState,
			String title) throws InvalidStateException {
		checkTaskState(targetState);
		getTaskManager(title).moveTaskToState(taskName, targetState);
		Event event = new Event("Changed state of task " + taskName + " of: "
				+ title + ". Now it is " + targetState, this.getCurrentDate(), false);
		addDevelopersToEvent(taskName, event, title);
		this.timeline.addEvent(event);
	}
	
	public void addDeveloperToTask(String taskName, String developer, String title)
			throws InvalidMemberException, NameAlreadyInUseException {
		checkTask(taskName, title);
		checkMember(developer);
		getTaskManager(title).getTask(taskName).addDeveloper(developer);
		Event event = new Event("Added " + developer + " to task: " + taskName
				+ " of: " + title, getCurrentDate(), false);
		event.addParticipant(developer);
		timeline.addEvent(event);
	}
	
	public ArrayList<Task> getTasks(String title, Filter<Task> filter) {
		return userStoryManager.getUserStory(title).getTasksManager().getTasks(filter);
	}
	
	
	
	
	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}

	public Event getEvent(String eventName) {
		return this.timeline.getEvent(eventName);
	}


	public ArrayList<Event> getEvents(Filter<Event> filter) {
		return timeline.getEvents(filter);
	}

	
	
	


	public void addUserStory(String title, String description) throws NameAlreadyInUseException {
		this.userStoryManager.addUserStory(title, description);
		this.timeline.addEvent(new Event("Created userstory: " + title,
				getCurrentDate(), false));
	}

	public void addUserStory(String title) throws NameAlreadyInUseException {
		this.addUserStory(title, "");
		this.timeline.addEvent(new Event("Created userstory: " + title,
				getCurrentDate(), false));
	}

	public void deleteUserStory(String title) {
		Event event = new Event("Deleted userstory: " + title, getCurrentDate(), false);
		this.userStoryManager.deleteUserStory(title);
		timeline.addEvent(event);
	}

	public void moveStoryToState(String title, String targetState)
			throws InvalidStateException {
		checkUserStoryState(targetState);
		this.userStoryManager.moveStoryToState(title, targetState);
		Event event = new Event("Changed state of userstory " + title
				+ ": now it is " + targetState, this.getCurrentDate(), false);
		this.timeline.addEvent(event);
	}
	
	public ArrayList<UserStory> getUserStory(Filter<UserStory> filter) {
		return userStoryManager.getUserStories(filter);
	}
	
	
	
	
	
	private void checkTaskState(String targetState) throws InvalidStateException {
		if (!settings.getPossibleTasksStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}
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
	
	private void addDevelopersToEvent(String taskName, Event event, String title) {
		event.addParticipants(getTaskManager(title).getTask(taskName)
				.getDevelopers());
	}

	private GregorianCalendar getCurrentDate() {
		return (GregorianCalendar) Calendar.getInstance();
	}
	
	private void checkUserStoryState(String targetState) throws InvalidStateException {
		if (!settings.getPossibleUserStoriesStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}
	}

	private TasksManager getTaskManager(String title) {
		if (title.compareTo(GENERAL) == 0) {
			return this.generalTaskManager;
		} else {
			return userStoryManager.getUserStory(title).getTasksManager();
		}
	}
	
}
