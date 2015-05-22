package filtering.checkers;

import boards.UserStoryBoard.UserStory;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides
 * {@link UserStory} validation over state comparison. A user story is
 * considered valid if its state matches the specified one.
 * 
 * @author simone, lele, incre, andre
 *
 */
public class StateUserStoryChecker implements Checker<UserStory> {

	private String state;

	/**
	 * Creates a new instance of this filter
	 * 
	 * @param state
	 *            : the state of a valid user story
	 */
	public StateUserStoryChecker(String state) {
		this.state = state;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(UserStory tobeChecked) {
		if (tobeChecked.getState().compareTo(state) == 0) {
			return true;
		}
		return false;
	}

}
