package model;

import java.util.ArrayList;

import model.exceptions.NameAlreadyInUseException;

public class ConcreteTeamSettings implements TeamSettings {

	private ArrayList<String> teamMembers = new ArrayList<String>();
	private ArrayList<String> possibleTasksStates = new ArrayList<String>();
	private ArrayList<String> possibleUserStoriesStates = new ArrayList<String>();
	private TeamManager manager;

	public void setPossibleTasksStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleTasksStates.add(state);
		}
	}

	public void setPossibleUserStoriesStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleUserStoriesStates.add(state);
		}
	}
	
	public void setManager(TeamManager manager) {
		this.manager = manager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.TeamSettings#getTeamMembers()
	 */
	@Override
	public ArrayList<String> getTeamMembers() {
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

	@Override
	/*
	 * (non-Javadoc)
	 * @see model.TeamSettings#isValidUserStoryPriority(int)
	 */
	public boolean isValidUserStoryPriority(int priority) {
		if ((priority >= boards.UserStoryBoard.UserStory.MINPRIORITY)
				&& (priority <= boards.UserStoryBoard.UserStory.MAXPRIORITY)) {
			return true;
		}
		return false;
	}

	public void addTeamMember(String... member)
			throws NameAlreadyInUseException {
		for (int i = 0; i < member.length; i++) {
			checkMemberName(member[i]);
			this.teamMembers.add(member[i]);
		}
		manager.membersAdded(member);
	}

	private void checkMemberName(String member)
			throws NameAlreadyInUseException {
		if (this.teamMembers.contains(member)) {
			throw new NameAlreadyInUseException(member);
		}

	}

}