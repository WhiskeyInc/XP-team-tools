package filtering.checkers;

import boards.UserStoryBoard.UserStory;
import filtering.Checker;

public class StateUserStoryChecker implements Checker<UserStory> {

	private String state;
	
	public StateUserStoryChecker(String state) {
		this.state=state;
	}
	
	@Override
	public boolean check(UserStory tobeChecked) {
		if(tobeChecked.getState().compareTo(state)==0){
			return true;
		}
		return false;
	}

	
}
