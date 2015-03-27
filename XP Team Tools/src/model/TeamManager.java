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

	private TasksManager generalTaskManager = new TasksManager();
	private UserStoriesManager userstoryManager = new UserStoriesManager();
	private Timeline timeline = new Timeline();
	private TeamSettings settings;

	public TeamManager(TeamSettings settings) {
		this.settings = settings;
	}

	public void addTaskToUserStory(String taskName, String description,
			String relatedUserStoryName) throws NameAlreadyInUseException {
		if (taskExists(taskName)) {
			throw new NameAlreadyInUseException(taskName);
		}
		if (relatedUserStoryName == null) {
			generalTaskManager.addTask(taskName, description);
			this.timeline.addEvent(new Event("Created task: " + taskName, this
					.getCurrentDate()));
		} else {
			userstoryManager.getUserStory(relatedUserStoryName)
					.getTasksManager().addTask(taskName, description);
			this.timeline.addEvent(new Event("Created task: " + taskName
					+ " for: " + relatedUserStoryName, getCurrentDate()));
		}

	}

	public void addTaskToUserStory(String taskName, String relatedUserStoryName)
			throws NameAlreadyInUseException {
		this.addTaskToUserStory(taskName, "", relatedUserStoryName);
	}

	public void addGeneralTask(String taskName, String description)
			throws NameAlreadyInUseException {
		this.addTaskToUserStory(taskName, description, null);
	}

	public void addGeneralTask(String taskName)
			throws NameAlreadyInUseException {
		this.addGeneralTask(taskName, "");
	}

	public void deleteTask(String taskName) throws NoSuchTaskException {
		if (!taskExists(taskName)) {
			throw new NoSuchTaskException(taskName);
		}
		Event event;
		if (this.getUserStory(taskName) != null) {
			event = new Event("Deleted task: " + taskName + " from: "
					+ getUserStory(taskName), getCurrentDate());
		} else {
			event = new Event("Deleted task: " + taskName, getCurrentDate());
		}
		timeline.addEvent(event);
		addTaskDevelopersToRelatedEvent(taskName, event);
		this.getTaskManager(taskName).deleteTask(taskName);
	}

	public void moveTaskToState(String taskName, String targetState)
			throws InvalidStateException, NoSuchTaskException {
		if (!taskExists(taskName)) {
			throw new NoSuchTaskException(taskName);
		}
		checkTasksState(targetState);
		getTaskManager(taskName).moveTaskToState(taskName, targetState);
		Event event;
		if (this.getUserStory(taskName) != null) {
			event = new Event("Changed state of task " + taskName + " of: "
					+ getUserStory(taskName) + ". Now it is " + targetState,
					this.getCurrentDate());
		} else {
			event = new Event("Changed state of task " + taskName,
					getCurrentDate());
		}
		this.timeline.addEvent(event);
		addTaskDevelopersToRelatedEvent(taskName, event);
	}

	public void addDeveloperTo(String taskName, String developer)
			throws InvalidMemberException, NoSuchTaskException {
		if (!taskExists(taskName)) {
			throw new NoSuchTaskException(taskName);
		}
		checkMember(developer);
		getTaskManager(taskName).getTask(taskName).addDeveloper(developer);
		Event event;
		if (this.getUserStory(taskName) != null) {
			event = new Event("Added " + developer + " to task: " + taskName
					+ " of: " + taskName, getCurrentDate());
		} else {
			event = new Event("Added " + developer + " to task: " + taskName,
					getCurrentDate());
		}
		event.addParticipant(developer);
		timeline.addEvent(event);
	}

	public void addUserStory(String title, String description)
			throws NameAlreadyInUseException {
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

	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}

	public Event getEvent(String eventName) {
		return this.timeline.getEvent(eventName);
	}

	public ArrayList<Event> getEvents(Filter<Event> filter) {
		return timeline.getEvents(filter);
	}

	private void checkUserStoriesState(String targetState)
			throws InvalidStateException {
		if (!settings.getPossibleUserStoriesStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}
	}

	private TasksManager getTaskManager(String taskName) {
		if (generalTaskManager.getTask(taskName) != null) {
			return generalTaskManager;
		} else {
			UserStory story = this.getUserStory(taskName);
			if (story != null) {
				return story.getTasksManager();
			}
		}
		return null;
	}

	private void checkTasksState(String targetState)
			throws InvalidStateException {
		if (!settings.getPossibleTasksStates().contains(targetState)) {
			throw new InvalidStateException(targetState);
		}
	}

	private boolean taskExists(String taskName) {
		if (this.getTaskManager(taskName) != null) {
			return true;
		}
		return false;
	}

	private void addTaskDevelopersToRelatedEvent(String taskName, Event event) {
		if (this.getTaskManager(taskName) == null) {
			System.err.println("cazzo");
		}
		event.addParticipants(getTaskManager(taskName).getTask(taskName)
				.getDevelopers());
	}

	private void checkMember(String member) throws InvalidMemberException {
		if (!settings.getTeamMembers().contains(member)) {
			throw new InvalidMemberException(member);
		}
	}

	private GregorianCalendar getCurrentDate() {
		return (GregorianCalendar) Calendar.getInstance();
	}

	private UserStory getUserStory(String taskName) {
		for (UserStory userStory : userstoryManager.getSortedStories()) {
			for (Task task : userStory.getTasksManager().getTasks()) {
				if (task.toString().equals(taskName)) {
					return userStory;
				}
			}
		}
		return null;
	}
}
