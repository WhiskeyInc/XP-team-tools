package boards.UserStoryBoard;

import java.util.ArrayList;
import java.util.HashMap;

import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;
import filtering.Filter;

public class ConcreteUserStoriesManager implements UserStoriesManager {

	private HashMap<String, UserStory> stories = new HashMap<String, UserStory>();
	private ArrayList<UserStory> sortedStories = new ArrayList<UserStory>();

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#addUserStory(java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserStory(String userStoryName, String description) throws NameAlreadyInUseException {
		validateName(userStoryName);
		UserStory userStory = new UserStory(userStoryName, description);
		stories.put(userStory.toString(), userStory);
		sortedStories.add(userStory);
	}

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#deleteUserStory(java.lang.String)
	 */
	@Override
	public void deleteUserStory(String userStoryName) throws NoSuchUserStoryException{
		validateExistance(userStoryName);
		sortedStories.remove(stories.get(userStoryName));
		stories.remove(userStoryName);
	}
	
	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#getSortedStories()
	 */
	@Override
	public ArrayList<UserStory> getSortedStories() {
		return sortedStories;
	}

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#changeStoryPriority(java.lang.String, int)
	 */
	@Override
	public void changeStoryPriority(String userStoryName, int newPriority) throws NoSuchUserStoryException {
		validateExistance(userStoryName);
		sortedStories.remove(stories.get(userStoryName));
		sortedStories.add(newPriority, stories.get(userStoryName));
	}

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#getUserStory(java.lang.String)
	 */
	@Override
	public UserStory getUserStory(String storyName) {
		return stories.get(storyName);
	}

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#moveStoryToState(java.lang.String, java.lang.String)
	 */
	@Override
	public void moveUserStoryToState(String storyName, String targetState)
			throws NoSuchUserStoryException {
		validateExistance(storyName);
		this.stories.get(storyName).setState(targetState);
	}
	
	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#getUserStories(filtering.Filter)
	 */
	@Override
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter) {
		return filter.filter(this.getAllUserStories());
	}
	
	private ArrayList<UserStory> getAllUserStories() {
		ArrayList<UserStory> userStoriesList = new ArrayList<UserStory>();
		userStoriesList.addAll(this.stories.values());
		return userStoriesList;
	}
	
	private void validateName(String storyName) throws NameAlreadyInUseException {
		if(storyExists(storyName)){
			throw new NameAlreadyInUseException(storyName);
		}
	}
	
	private void validateExistance(String storyName) throws NoSuchUserStoryException {
		if(!storyExists(storyName)){
			throw new NoSuchUserStoryException(storyName);
		}
	}

	private boolean storyExists(String title) {
		return stories.containsKey(title);
	}
	
}
