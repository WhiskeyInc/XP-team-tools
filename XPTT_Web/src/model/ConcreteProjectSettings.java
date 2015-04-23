package model;

import java.util.ArrayList;

import model.exceptions.NameAlreadyInUseException;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.Task;

/**
 * This implementation of {@link ProjectSettings} interface provides
 * implementation for each inherited method, but also adds mehods to change
 * settings. In order to notify these changes, this class can use an instance of
 * {@link ProjectManager} to communicate with other project elements
 * 
 * @author simone
 * @see ProjectManager
 *
 */
public class ConcreteProjectSettings implements ProjectSettings {

	private ArrayList<TeamComponent> teamMembers = new ArrayList<TeamComponent>();
	private ArrayList<String> possibleTasksStates = new ArrayList<String>();
	private ArrayList<String> possibleUserStoriesStates = new ArrayList<String>();
	private ProjectManager manager;

	/**
	 * Sets the available states for an instance of {@link Task}
	 * 
	 * @param possibleStates
	 *            : the available states
	 */
	public void setPossibleTasksStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleTasksStates.add(state);
		}
	}

	/**
	 * Sets the available states for an instance of {@link UserStory}
	 * 
	 * @param possibleStates
	 *            : the available states
	 */
	public void setPossibleUserStoriesStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleUserStoriesStates.add(state);
		}
	}

	/**
	 * Sets the {@link ProjectManager} to use for settings changes notifications
	 * 
	 * @param manager
	 *            : the instance of {@link ProjectManager} to use for notifications
	 */
	public void setManager(ProjectManager manager) {
		this.manager = manager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamSettings#getTeamMembers()
	 */
	@Override
	public ArrayList<TeamComponent> getTeamMembers() {
		return teamMembers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamSettings#getPossibleTasksStates()
	 */
	@Override
	public ArrayList<String> getPossibleTaskStates() {
		return possibleTasksStates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamSettings#getPossibleUserStoriesStates()
	 */
	@Override
	public ArrayList<String> getPossibleUserStoryStates() {
		return possibleUserStoriesStates;
	}

	/**
	 * Registers a {@link TeamComponent} instance as member of this project
	 * 
	 * @param member
	 *            : the {@link TeamComponent} to be added
	 * @throws NameAlreadyInUseException
	 *             : if the component is already registered
	 */
	public void addTeamMember(TeamComponent... member)
			throws NameAlreadyInUseException {
		for (int i = 0; i < member.length; i++) {
			checkMemberName(member[i]);
			this.teamMembers.add(member[i]);
		}
		if (manager != null) {
			manager.membersAdded(member);
		}
	}

	private void checkMemberName(TeamComponent member)
			throws NameAlreadyInUseException {
		if (this.teamMembers.contains(member)) {
			throw new NameAlreadyInUseException(member.toString());
		}

	}

}