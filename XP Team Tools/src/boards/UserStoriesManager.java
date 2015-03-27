package boards;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InvalidNameException;

import model.NameAlreadyInUseException;

public class UserStoriesManager {

	private HashMap<String, UserStory> stories = new HashMap<String, UserStory>();
	private ArrayList<UserStory> sortedStories = new ArrayList<UserStory>();

	public void addUserStory(String title, String description) throws NameAlreadyInUseException {
		checkUserStoryName(title);
		UserStory userStory = new UserStory(title, description);
		stories.put(userStory.toString(), userStory);
		sortedStories.add(userStory);
	}

	private void checkUserStoryName(String title) throws NameAlreadyInUseException {
		if(stories.containsKey(title)){
			throw new NameAlreadyInUseException(title);
		}
	}

	public void deleteUserStory(String title){
		sortedStories.remove(stories.get(title));
		stories.remove(title);
	}
	
	public ArrayList<UserStory> getSortedStories() {
		return sortedStories;
	}

	public void changeStoryPriority(String storyName, int newPriority) {
		sortedStories.remove(stories.get(storyName));
		sortedStories.add(newPriority, stories.get(storyName));
	}

	public UserStory getUserStory(String storyName) {
		return stories.get(storyName);
	}

	public void moveStoryToState(String storyName, String targetState) {
		this.stories.get(storyName).setState(targetState);
	}

	
}
