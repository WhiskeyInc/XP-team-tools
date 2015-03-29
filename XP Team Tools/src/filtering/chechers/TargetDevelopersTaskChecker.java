package filtering.chechers;

import filtering.Checker;
import boards.Task;

public class TargetDevelopersTaskChecker implements Checker<Task> {

	private String[] targetMembers;

	public TargetDevelopersTaskChecker(String... targetMembers) {
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