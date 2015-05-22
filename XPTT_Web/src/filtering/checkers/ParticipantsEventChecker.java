package filtering.checkers;

import timeline.Event;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides
 * {@link Event} validation over participants' names comparison. An event is
 * considered valid if it contains all the specified participants.
 * 
 * 
 * @author simone, lele, incre, andre
 *
 */
public class ParticipantsEventChecker implements Checker<Event> {

	private String[] targetMembers;

	/**
	 * Creates a new instance of this filter
	 * 
	 * @param targetMembers
	 *            : the members to be searched
	 */
	public ParticipantsEventChecker(String... targetMembers) {
		this.targetMembers = new String[targetMembers.length];
		this.targetMembers = targetMembers;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(Event tobeChecked) {
		for (int i = 0; i < targetMembers.length; i++) {
			if (!tobeChecked.getParticipants().contains(targetMembers[i])) {
				return false;
			}
		}
		return true;
	}

}
