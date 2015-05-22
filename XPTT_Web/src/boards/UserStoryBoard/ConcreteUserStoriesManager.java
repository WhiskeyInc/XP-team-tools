package boards.UserStoryBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.exceptions.InvalidPriorityException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;
import filtering.Filter;

/**
 * A simple implementation of {@link UserStoriesManager} interface. It does
 * implement existence control when editing a story, valid name control when
 * adding a new one, but does not implement any kind of further validation (i.e
 * {@link InvalidStateException} and {@link InvalidPriorityException} are never
 * thrown)
 * 
 * @author simone, lele, usk, incre
 * @see UserStoriesManager
 *
 */
public class ConcreteUserStoriesManager implements UserStoriesManager {

	private HashMap<String, UserStory> stories = new HashMap<String, UserStory>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#addUserStory(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void addUserStory(UserStory userStory)
			throws NameAlreadyInUseException {
		validateName(userStory.toString());
		stories.put(userStory.toString(), userStory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#deleteUserStory(java.lang.String)
	 */
	@Override
	public void deleteUserStory(String userStoryName)
			throws NoSuchUserStoryException {
		validateExistance(userStoryName);
		stories.remove(userStoryName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#changeStoryPriority(java.lang.String, int)
	 */
	@Override
	public void changeStoryPriority(String userStoryName, int newPriority)
			throws NoSuchUserStoryException, InvalidPriorityException {
		validateExistance(userStoryName);
		this.stories.get(userStoryName).setPriority(newPriority);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#getUserStory(java.lang.String)
	 */
	@Override
	public UserStory getUserStory(String storyName) {
		return stories.get(storyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#moveStoryToState(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void moveUserStoryToState(String storyName, String targetState)
			throws NoSuchUserStoryException {
		validateExistance(storyName);
		this.stories.get(storyName).moveToState(targetState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#getUserStories(filtering.Filter)
	 */
	@Override
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter) {
		return filter.filter(this.getAllUserStories());
	}

	private ArrayList<UserStory> getAllUserStories() {
		ArrayList<UserStory> userStoriesList = new ArrayList<UserStory>();
		userStoriesList.addAll(this.stories.values());
		Collections.sort(userStoriesList);
		return userStoriesList;
	}

	private void validateName(String storyName)
			throws NameAlreadyInUseException {
		if (storyExists(storyName)) {
			throw new NameAlreadyInUseException(storyName);
		}
	}

	private void validateExistance(String storyName)
			throws NoSuchUserStoryException {
		if (!storyExists(storyName)) {
			throw new NoSuchUserStoryException(storyName);
		}
	}

	private boolean storyExists(String title) {
		return stories.containsKey(title);
	}

}
