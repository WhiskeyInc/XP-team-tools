package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.UserStory;

public class ConcreteTeamManager implements TeamManager {

	private Timeline timeline;
	private TeamSettings settings;

	public ConcreteTeamManager(TeamSettings settings, Timeline timeline) {
		this.settings = settings;
		this.timeline = timeline;
	}

	@Override
	public void taskAdded(Task task) {
		this.timeline.addEvent(generateAutomaticEvent("Created task: "
				+ task.toString()));
	}

	@Override
	public void taskDeleted(Task task) {
		Event event = generateAutomaticEvent("Deleted task: " + task.toString());
		event.addParticipants(task.getDevelopers());
		timeline.addEvent(event);
	}

	@Override
	public void taskStateChanged(Task task, String newState) {
		Event event = generateAutomaticEvent("Changed state of task "
				+ task.toString() + ". Now it is " + newState);
		event.addParticipants(task.getDevelopers());
		this.timeline.addEvent(event);
	}

	@Override
	public void developersAdded(Task task, String... developers) {
		String developerNames = "";
		for (String developer : developers) {
			developerNames = developerNames + developer + " ";
		}
		Event event = generateAutomaticEvent("Developers added to task " + task.toString() + ": " + developerNames);
		for (String developer : developers) {
			event.addParticipant(developer);
		}
		timeline.addEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryAdded(java.lang.String)
	 */
	@Override
	public void userStoryAdded(UserStory userStory) {
		this.timeline.addEvent(generateAutomaticEvent("Created userstory: "
				+ userStory.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryDeleted(java.lang.String)
	 */
	@Override
	public void userStoryDeleted(UserStory userStory) {
		timeline.addEvent(generateAutomaticEvent("Deleted userstory: "
				+ userStory.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryStateChanged(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void userStoryStateChanged(UserStory userStory, String newState) {
		this.timeline
				.addEvent(generateAutomaticEvent("Changed state of userstory "
						+ userStory.toString() + ": now it is " + newState));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryPriorityChanged(java.lang.String)
	 */
	@Override
	public void userStoryPriorityChanged(UserStory userStory) {
		timeline.addEvent(generateAutomaticEvent("Changed priority of userstory: "
				+ userStory.toString()));
	}
	
	@Override
	public boolean isValidMember(String member) {
		return settings.getTeamMembers().contains(member);
	}
	
	@Override
	public boolean isValidTaskState(String state){
		return settings.getPossibleTaskStates().contains(state);
	}
	
	@Override
	public boolean isValidUserStoryState(String state){
		return settings.getPossibleUserStoryStates().contains(state);
	}
	
	private GregorianCalendar getCurrentDate() {
		return (GregorianCalendar) Calendar.getInstance();
	}

	private Event generateAutomaticEvent(String eventName) {
		return new Event(eventName, getCurrentDate(), false);
	}

}
