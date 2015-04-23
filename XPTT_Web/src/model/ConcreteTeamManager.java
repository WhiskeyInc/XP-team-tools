package model;

import java.util.ArrayList;
import java.util.TimeZone;

import model.exceptions.InvalidDateException;
import timeline.AutomaticEvent;
import timeline.Event;
import timeline.Timeline;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.Task;

/**
 * This implementation of {@link ProjectManager} interface provides project
 * events notification through an instance of {@link Timeline} and data control
 * over an implementation of {@link ProjectSettings} interface
 * 
 * @author simone
 *
 */
public class ConcreteTeamManager implements ProjectManager {

	private Timeline timeline;
	private TimeZone locale = TimeZone.getTimeZone("Europe/Rome");
	private ProjectSettings settings;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param settings
	 *            : the {@link ProjectSettings} to use for data consistency
	 * @param timeline
	 *            : the {@link Timeline} object through wich notify some events
	 *            related to the project
	 */
	public ConcreteTeamManager(ProjectSettings settings, Timeline timeline) {
		this.settings = settings;
		this.timeline = timeline;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#taskAdded(boards.taskBoard.Task)
	 */
	public void taskAdded(Task task) {
		try {
			Event event = new AutomaticEvent(
					"Created task: " + task.toString(),
					TimeZone.getTimeZone("Europe/Rome"));
			event.addParticipants(task.getDevelopers());
			this.timeline.addEvent(event);
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#taskDeleted(boards.taskBoard.Task)
	 */
	public void taskDeleted(Task task) {
		try {
			Event event = new AutomaticEvent(
					"Deleted task: " + task.toString(), locale);
			event.addParticipants(task.getDevelopers());
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#taskStateChanged(boards.taskBoard.Task,
	 * java.lang.String)
	 */
	public void taskStateChanged(Task task, String newState) {
		try {
			Event event = new AutomaticEvent("Changed state of task "
					+ task.toString() + ". Now it is " + newState, locale);
			event.addParticipants(task.getDevelopers());
			this.timeline.addEvent(event);
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#developersAdded(boards.taskBoard.Task,
	 * java.lang.String[])
	 */
	public void developersAdded(Task task, String... developers) {
		String developerNames = "";
		for (String developer : developers) {
			developerNames = developerNames + developer + " ";
		}
		try {
			Event event = new AutomaticEvent("Developers added to task "
					+ task.toString() + ": " + developerNames, locale);
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryAdded(java.lang.String)
	 */
	@Override
	public void userStoryAdded(UserStory userStory) {
		try {
			this.timeline.addEvent(new AutomaticEvent("Created userstory: "
					+ userStory.toString(), locale));
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryDeleted(java.lang.String)
	 */
	@Override
	public void userStoryDeleted(UserStory userStory) {
		try {
			timeline.addEvent(new AutomaticEvent("Deleted userstory: "
					+ userStory.toString(), locale));
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryStateChanged(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void userStoryStateChanged(UserStory userStory, String newState) {
		try {
			this.timeline.addEvent(new AutomaticEvent(
					"Changed state of userstory " + userStory.toString()
							+ ": now it is " + newState, locale));
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamManager#userStoryPriorityChanged(java.lang.String)
	 */
	@Override
	public void userStoryPriorityChanged(UserStory userStory, int newPriority) {
		try {
			timeline.addEvent(new AutomaticEvent(
					"Changed priority of userstory: " + userStory.toString()
							+ ": now it is " + newPriority, locale));
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#membersAdded(model.TeamComponent[])
	 */
	public void membersAdded(TeamComponent[] members) {
		try {
			ArrayList<String> membersList = new ArrayList<String>();
			for (TeamComponent member : members) {
				membersList.add(member.toString());
			}
			Event event = new AutomaticEvent("Added members to the team",
					locale);
			event.addParticipants(membersList);
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			throwAutomaticEventDateRunTimeException();
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#isValidMember(java.lang.String)
	 */
	public boolean isValidMember(String member) {
		ArrayList<String> list = new ArrayList<String>();
		for (TeamComponent memberObject : settings.getTeamMembers()) {
			list.add(memberObject.toString());
		}
		return list.contains(member);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#isValidTaskState(java.lang.String)
	 */
	public boolean isValidTaskState(String state) {
		return settings.getPossibleTaskStates().contains(state);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ProjectManager#isValidUserStoryState(java.lang.String)
	 */
	public boolean isValidUserStoryState(String state) {
		return settings.getPossibleUserStoryStates().contains(state);
	}

	private void throwAutomaticEventDateRunTimeException() {
		throw new RuntimeException(
				"Fatal Error: the automatic event has not a valid date! The program is not properly working.");
	}

}
