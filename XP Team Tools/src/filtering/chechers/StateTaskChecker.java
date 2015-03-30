package filtering.chechers;

import filtering.Checker;
import boards.Task;

public class StateTaskChecker implements Checker<Task> {

	private String state;
	
	public StateTaskChecker(String state) {
		this.state=state;
	}
	
	@Override
	public boolean check(Task tobeChecked) {
		if(tobeChecked.getState().compareTo(state)==0){
			return true;
		}
		return false;
	}

	
}
