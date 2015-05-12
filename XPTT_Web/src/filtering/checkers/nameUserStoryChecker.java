package filtering.checkers;

import boards.UserStoryBoard.UserStory;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides
 * {@link UserStory} validation over name's comparison. A user story is considered as
 * valid if its name contains or is equal to a target string
 * 
 * @author simone
 *
 */
public class nameUserStoryChecker implements Checker<UserStory> {

	private String name;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param targetString
	 *            : the string that will be used to perform validation
	 */
	public nameUserStoryChecker(String targetString) {
		this.name = targetString;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(UserStory tobeChecked) {
		if (tobeChecked.toString().contains(name)) {
			return true;
		}
		return false;
	}

}
