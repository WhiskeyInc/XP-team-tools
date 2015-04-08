package filtering.checkers;

import timeline.Event;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides
 * {@link Event} validation over name's comparison. An event is considered as
 * valid if its name contains or is equal to a target string
 * 
 * @author simone
 *
 */
public class NameEventChecker implements Checker<Event> {

	private String name;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param targetString
	 *            : the string that will be used to perform validation
	 */
	public NameEventChecker(String targetString) {
		this.name = targetString;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(Event tobeChecked) {
		if (tobeChecked.toString().contains(name)) {
			return true;
		}
		return false;
	}

}
