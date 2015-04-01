package filtering.checkers;

import filtering.Checker;
import boards.UserStoryBoard.UserStory;

public class PriorityUserStoryChecker implements Checker<UserStory> {

	private String priority;
	
	public PriorityUserStoryChecker(String priority) {
		this.priority=priority;
	}
	
	@Override
	public boolean check(UserStory tobeChecked) {
		if(tobeChecked.getPriority().compareTo(priority)==0){
			return true;
		}
		return false;
	}

	
}
