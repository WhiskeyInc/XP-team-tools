package boards;

import java.util.ArrayList;

import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import filtering.Filter;

public interface UserStoriesManager {

	public void addUserStory(String storyName, String description)
			throws NameAlreadyInUseException;

	public void deleteUserStory(String storyName);

	public ArrayList<UserStory> getSortedStories(); //TODO

	public void changeStoryPriority(String storyName, int newPriority); //TODO

	public UserStory getUserStory(String storyName);

	public void moveUserStoryToState(String storyName, String targetState) throws InvalidStateException;

	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter);

}