package boards;

import java.util.ArrayList;

import model.TeamManager;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import filtering.Filter;

public class TeamUserStoriesManager implements UserStoriesManager {

	private UserStoriesManager userStoriesManager;
	private TeamManager teamManager;
	
	public TeamUserStoriesManager(UserStoriesManager userStoriesManager,
			TeamManager teamManager) {
		super();
		this.userStoriesManager = userStoriesManager;
		this.teamManager = teamManager;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see boards.UserStoriesManager#addUserStory(java.lang.String, java.lang.String)
	 */
	public void addUserStory(String storyName, String description)
			throws NameAlreadyInUseException {
		userStoriesManager.addUserStory(storyName, description);
		teamManager.userStoryAdded(this.getUserStory(storyName));

	}

	@Override
	public void deleteUserStory(String storyName) {
		UserStory userStory = this.getUserStory(storyName);
		userStoriesManager.deleteUserStory(storyName);
		teamManager.userStoryDeleted(userStory);

	}

	@Override
	public ArrayList<UserStory> getSortedStories() {
		return userStoriesManager.getSortedStories();
	}

	@Override
	public void changeStoryPriority(String storyName, int newPriority) {
		userStoriesManager.changeStoryPriority(storyName, newPriority);
		teamManager.userStoryPriorityChanged(this.getUserStory(storyName));
	}

	@Override
	public UserStory getUserStory(String storyName) {
		return userStoriesManager.getUserStory(storyName);
	}

	@Override
	public void moveUserStoryToState(String storyName, String targetState) throws InvalidStateException {
		if (!this.teamManager.isValidUserStoryState(targetState)) {
			throw new InvalidStateException(targetState);
		}
		this.userStoriesManager.moveUserStoryToState(storyName, targetState);
		teamManager.userStoryStateChanged(this.getUserStory(storyName), targetState);
	}

	@Override
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter) {
		return userStoriesManager.getUserStories(filter);
	}

}
