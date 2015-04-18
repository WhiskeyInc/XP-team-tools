package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import timeline.Timeline;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.Task;

public class ConcreteTeamManager implements TeamManager {

	private Timeline timeline;
	private TeamSettings settings;

	public ConcreteTeamManager(TeamSettings settings, Timeline timeline) {
		this.settings = settings;
		this.timeline = timeline;
	}

	@Override
	public void taskAdded(Task task) {
		try {
			this.timeline.addEvent("Created task: " + task.toString(), false,
					(GregorianCalendar) Calendar.getInstance(), null);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	public void taskDeleted(Task task) {
		try {
			timeline.addEvent("Deleted task: " + task.toString(), false,
					(GregorianCalendar) Calendar.getInstance(),
					task.getDevelopers());
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	public void taskStateChanged(Task task, String newState) {
		try {
			this.timeline.addEvent("Changed state of task " + task.toString()
					+ ". Now it is " + newState, false,
					(GregorianCalendar) Calendar.getInstance(),
					task.getDevelopers());
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	public void developersAdded(Task task, String... developers) {
		String developerNames = "";
		for (String developer : developers) {
			developerNames = developerNames + developer + " ";
		}
		ArrayList<String> developerslist = new ArrayList<String>();
		for (String developer : developers) {
			developerslist.add(developer);
		}
		try {
			timeline.addEvent("Developers added to task " + task.toString()
					+ ": " + developerNames, false,
					(GregorianCalendar) Calendar.getInstance(), developerslist);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
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
			this.timeline.addEvent(
					"Created userstory: " + userStory.toString(), false,
					(GregorianCalendar) Calendar.getInstance(), null);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
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
			timeline.addEvent("Deleted userstory: " + userStory.toString(),
					false, (GregorianCalendar) Calendar.getInstance(), null);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
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
			this.timeline.addEvent(
					"Changed state of userstory " + userStory.toString()
							+ ": now it is " + newState, false,
					(GregorianCalendar) Calendar.getInstance(), null);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
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
			timeline.addEvent(
					"Changed priority of userstory: " + userStory.toString()
							+ ": now it is " + newPriority, false,
					(GregorianCalendar) Calendar.getInstance(), null);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	public boolean isValidMember(String member) {
		return settings.getTeamMembers().contains(member);
	}

	@Override
	public boolean isValidTaskState(String state) {
		return settings.getPossibleTaskStates().contains(state);
	}

	@Override
	public boolean isValidUserStoryState(String state) {
		return settings.getPossibleUserStoryStates().contains(state);
	}

	@Override
	public boolean isValidUserStoryPriority(int priority) {
		return settings.isValidUserStoryPriority(priority);
	}

	@Override
	public void membersAdded(String[] member) {
		try {
			ArrayList<String> members = new ArrayList<String>();
			for (String string : member) {
				members.add(string);
			}
			timeline.addEvent("Added members to the team", false,
					(GregorianCalendar) Calendar.getInstance(), members);
		} catch (InvalidDateException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

}
