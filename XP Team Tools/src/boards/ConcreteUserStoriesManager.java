package boards;

import java.util.ArrayList;
import java.util.HashMap;

import model.exceptions.NameAlreadyInUseException;
import filtering.Filter;

public class ConcreteUserStoriesManager implements UserStoriesManager {

	private HashMap<String, UserStory> stories = new HashMap<String, UserStory>();
	private ArrayList<UserStory> sortedStories = new ArrayList<UserStory>();

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#addUserStory(java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserStory(String title, String description) throws NameAlreadyInUseException {
		checkUserStoryName(title);
		UserStory userStory = new UserStory(title, description);
		stories.put(userStory.toString(), userStory);
		sortedStories.add(userStory);
	}

	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#deleteUserStory(java.lang.String)
	 */
	@Override
	public void deleteUserStory(String title){
		sortedStories.remove(stories.get(title));
		stories.remove(title);
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
	public void changeStoryPriority(String storyName, int newPriority) {
		sortedStories.remove(stories.get(storyName));
		sortedStories.add(newPriority, stories.get(storyName));
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
	public void moveUserStoryToState(String storyName, String targetState) {
		this.stories.get(storyName).setState(targetState);
	}
	
	/* (non-Javadoc)
	 * @see boards.UserStoriesManager#getUserStories(filtering.Filter)
	 */
	@Override
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter) {
		return filter.filter(this.getUserStories());
	}
	
	private ArrayList<UserStory> getUserStories() {
		ArrayList<UserStory> userStoriesList = new ArrayList<UserStory>();
		userStoriesList.addAll(this.stories.values());
		return userStoriesList;
	}
	
	private void checkUserStoryName(String title) throws NameAlreadyInUseException {
		if(stories.containsKey(title)){
			throw new NameAlreadyInUseException(title);
		}
	}
	
}
