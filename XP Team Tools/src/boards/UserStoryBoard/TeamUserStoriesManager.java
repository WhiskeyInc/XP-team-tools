package boards.UserStoryBoard;

import java.util.ArrayList;

import boards.taskBoard.TaskManager;
import model.TeamManager;
import model.exceptions.InvalidPriorityException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;
import filtering.Filter;

/**
 * A DP Decorator oriented implementation of {@link UserStoriesManager}
 * interface. This class encapsulates a concrete implementation of that
 * interface but adds team functionalities, like parameters validation (over
 * {@link TeamManager} rules) and event notification
 * 
 * @author simone, lele, usk, incre
 * @see {@link UserStoriesManage}r, {@link TeamManager}
 * @since 1.0
 *
 */
public class TeamUserStoriesManager implements UserStoriesManager {

	private UserStoriesManager userStoriesManager;
	private TeamManager teamManager;

	/**
	 * Creates a new istance of this class
	 * 
	 * @param userStoriesManager
	 *            : the concrete implementation of {@link UserStoriesManager} to
	 *            encapsulate
	 * @param teamManager
	 *            : the {@link TeamManager} istance that will implement team
	 *            functionalities
	 */
	public TeamUserStoriesManager(UserStoriesManager userStoriesManager,
			TeamManager teamManager) {
		super();
		this.userStoriesManager = userStoriesManager;
		this.teamManager = teamManager;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.UserStoriesManager#addUserStory(java.lang.String,
	 * java.lang.String)
	 */
	public void addUserStory(String storyName, String description)
			throws NameAlreadyInUseException {
		userStoriesManager.addUserStory(storyName, description);
		teamManager.userStoryAdded(this.getUserStory(storyName));

	}

	@Override
	public void deleteUserStory(String storyName)
			throws NoSuchUserStoryException {
		UserStory userStory = this.getUserStory(storyName);
		userStoriesManager.deleteUserStory(storyName);
		teamManager.userStoryDeleted(userStory);

	}

	@Override
	public void changeStoryPriority(String storyName, String targetPriority)
			throws NoSuchUserStoryException, InvalidPriorityException {
		if (!this.teamManager.isValidUserStoryPriority(targetPriority)) {
			throw new InvalidPriorityException(targetPriority);
		}
		userStoriesManager.changeStoryPriority(storyName, targetPriority);
		teamManager.userStoryPriorityChanged(this.getUserStory(storyName),
				targetPriority);
	}

	@Override
	public UserStory getUserStory(String storyName) {
		return userStoriesManager.getUserStory(storyName);
	}

	@Override
	public void moveUserStoryToState(String storyName, String targetState)
			throws InvalidStateException, NoSuchUserStoryException {
		if (!this.teamManager.isValidUserStoryState(targetState)) {
			throw new InvalidStateException(targetState);
		}
		this.userStoriesManager.moveUserStoryToState(storyName, targetState);
		teamManager.userStoryStateChanged(this.getUserStory(storyName),
				targetState);
	}

	@Override
	public ArrayList<UserStory> getUserStories(Filter<UserStory> filter) {
		return userStoriesManager.getUserStories(filter);
	}

}
