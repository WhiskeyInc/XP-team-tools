package filtering.chechers;

import filtering.Checker;
import boards.Task;

public class DevelopersTaskChecker implements Checker<Task> {

	private String[] targetMembers;

	public DevelopersTaskChecker(String... targetMembers) {
		this.targetMembers = new String[targetMembers.length];
		this.targetMembers = targetMembers;
	}

	@Override
	public boolean check(Task tobeChecked) {
		for (int i = 0; i < targetMembers.length; i++) {
			if(!tobeChecked.getDevelopers().contains(targetMembers[i])){
				return false;
			}
		}
		return true;
	}
}