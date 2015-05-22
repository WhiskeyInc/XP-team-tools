package model;

import boards.UserStoryBoard.UserStory;
import boards.taskBoard.Task;

/**
 * ProjectManager interface provides generic methods to notify the system that
 * something related to the project has happened. It also provides data checking
 * to ensure consistency
 * 
 * @author simone, lele, incre, andre
 *
 */
public interface ProjectManager {

	/**
	 * Notifies the system that a user story has been created for this project
	 * 
	 * @param userStory
	 *            : the added {@link UserStory}
	 */
	public void userStoryAdded(UserStory userStory);

	/**
	 * Notifies the system that a user story of this project has been deleted
	 * 
	 * @param userStory
	 *            : the deleted {@link UserStory}
	 */
	public void userStoryDeleted(UserStory userStory);

	/**
	 * Notifies the system that a user story has changed its state
	 * 
	 * @param userStory
	 *            : the referring story
	 * @param newState
	 *            : the state the story has been moved to
	 */
	public void userStoryStateChanged(UserStory userStory, String newState);

	/**
	 * Notifies the system that a user story has changed its priority
	 * 
	 * @param userStory
	 *            : the referring user story
	 * @param newPriority
	 *            : the updated priority
	 */
	public void userStoryPriorityChanged(UserStory userStory, int newPriority);

	/**
	 * Checks a state and returns whether or not it is available for a story
	 * 
	 * @param state
	 *            : the state to be checked
	 * @return: true if it is available, false otherwise
	 */
	public boolean isValidUserStoryState(String state);

	/**
	 * Checks a state and returns whether or not it is available for a task
	 * 
	 * @param state
	 *            : the state to be checked
	 * @return: true if it is available, false otherwise
	 */
	public boolean isValidTaskState(String state);

	/**
	 * Checks a member and controls that he is indeed registered as contributor
	 * of this project
	 * 
	 * @param member
	 *            : the member to be checked
	 * @return: true if he is, false otherwise
	 */
	public boolean isValidMember(String member);

	/**
	 * Notifies the system that one or more developers have been added to a
	 * project task
	 * 
	 * @param task
	 *            : the referring {@link Task} instance
	 * @param developers
	 *            : the developers added to the task
	 */
	public void developersAdded(Task task, String... developers);

	/**
	 * Notifies the system that a task has changed its state
	 * 
	 * @param task
	 *            : the referring task
	 * @param newState
	 *            : the state the task has been moved to
	 */
	public void taskStateChanged(Task task, String newState);

	/**
	 * Notifies the system that a task of this project has been deleted
	 * 
	 * @param task
	 *            : the deleted {@link Task}
	 */
	public void taskDeleted(Task task);

	/**
	 * Notifies the system that a task has been created for this project
	 * 
	 * @param task
	 *            : the added {@link Task}
	 */
	public void taskAdded(Task task);

	/**
	 * Notifies the system that one or more {@link TeamComponent} instances are
	 * registered as contributors to this project
	 * 
	 * @param member
	 *            : the members added
	 */
	public void membersAdded(TeamComponent[] member);

}