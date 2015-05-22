package filtering.checkers;

import boards.taskBoard.Task;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides {@link Task}
 * validation over state comparison. A task is considered valid if its state
 * matches the specified one.
 * 
 * @author simone, lele, incre, andre
 *
 */
public class StateTaskChecker implements Checker<Task> {

	private String state;

	/**
	 * Creates a new instance of this filter
	 * 
	 * @param state
	 *            : the state of a valid event
	 */
	public StateTaskChecker(String state) {
		this.state = state;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(Task tobeChecked) {
		if (tobeChecked.getState().compareTo(state) == 0) {
			return true;
		}
		return false;
	}

}
