package boards.UserStoryBoard;

import java.util.ArrayList;

import model.exceptions.InvalidPriorityException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;
import filtering.Filter;

/**
 * UserStoriesManager interface represents a generic manager for a collection of
 * user stories. It provides item addition, deletion and editing, within some
 * logic controls. UserStory istances are identified by their name, so it is
 * impossible to add two stories with the same name
 * 
 * @author simone, lele, usk, incre
 * @since 1.0
 * @see UserStory
 */
public interface UserStoriesManager {

	/**
	 * Adds a new story to the collection
	 * 
	 * @param storyName
	 *            : the name of the story
	 * @param description
	 *            : a short string description to make details more clear
	 * @throws NameAlreadyInUseException
	 *             : if storyName matches with an existing story
	 */
	public void addUserStory(String storyName, String description)
			throws NameAlreadyInUseException;

	/**
	 * Deletes an existing story from this collection
	 * 
	 * @param storyName
	 *            : the name of the story to be deleted
	 * @throws NoSuchUserStoryException
	 *             : if storyName does not match with an existing story
	 */
	public void deleteUserStory(String storyName)
			throws NoSuchUserStoryException;

	/**
	 * Changes the priority of a specific user story
	 * 
	 * @param storyName
	 *            : the name of the story
	 * @param newPriority
	 *            : a string representing the updated priority for that story
	 * @throws NoSuchUserStoryException
	 *             : if storyName does not match with an existing story
	 * @throws InvalidPriorityException
	 *             : if newPriority is considered invalid
	 */
	public void changeStoryPriority(String storyName, String newPriority)
			throws NoSuchUserStoryException, InvalidPriorityException;

	/**
	 * Returns a specific story from this collection
	 * 
	 * @param storyName
	 *            : the name of the desired story
	 * @return: the {@link UserStory} instance whose name matches with storyName
	 */
	public UserStory getUserStory(String storyName);

	/**
	 * Changes the state of a specific user story
	 * 
	 * @param storyName
	 *            : the name of the story
	 * @param targetState
	 *            : a string representing the updated state for that story
	 * @throws NoSuchUserStoryException
	 *             : if storyName does not match with an existing story
	 * @throws InvalidStateException
	 *             : if targetState is considered invalid
	 */
	public void moveUserStoryToState(String storyName, String targetState)
			throws NoSuchUserStoryException, InvalidStateException;

	/**
	 * Returns a list of specific user stories
	 * 
	 * @param filter
	 *            : the rule to choose whether or not a story should be included
	 *            in the returned list
	 * @return: the filtered list of stories
	 * @see Filter
	 */
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter);

}