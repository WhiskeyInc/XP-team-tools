package filtering.checkers;

import boards.taskBoard.Task;
import filtering.Checker;

/**
 * This implementation of {@link Checker} interface provides {@link Task}
 * validation over a set of developers. The considered task is valid only if
 * each target developer is working on it. If just one target developer is not
 * working on it, the task is indeed considered invalid
 * 
 * @author simone, lele, incre, usk
 * @see Task, {@link Checker}
 *
 */
public class DevelopersTaskChecker implements Checker<Task> {

	private String[] targetMembers;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param targetMembers
	 *            : one or more strings representing target developers.
	 */
	public DevelopersTaskChecker(String... targetMembers) {
		this.targetMembers = new String[targetMembers.length];
		this.targetMembers = targetMembers;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(Task tobeChecked) {
		for (int i = 0; i < targetMembers.length; i++) {
			if (!tobeChecked.getDevelopers().contains(targetMembers[i])) {
				return false;
			}
		}
		return true;
	}
}