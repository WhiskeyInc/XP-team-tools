package boards.UserStoryBoard;

import java.util.ArrayList;

import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;
import filtering.Filter;

public interface UserStoriesManager {

	public void addUserStory(String storyName, String description)
			throws NameAlreadyInUseException;

	public void deleteUserStory(String storyName)
			throws NoSuchUserStoryException;

	public ArrayList<UserStory> getSortedStories(); // TODO

	public void changeStoryPriority(String storyName, int newPriority)
			throws NoSuchUserStoryException; // TODO

	public UserStory getUserStory(String storyName);

	public void moveUserStoryToState(String storyName, String targetState)
			throws NoSuchUserStoryException, InvalidStateException;

	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter);

}