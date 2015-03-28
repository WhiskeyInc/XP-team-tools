package filtering;

import boards.Task;

public class DeveloperTaskFilter implements Checker<Task> {

	private String[] targetMembers;

	public DeveloperTaskFilter(String... targetMembers) {
		this.targetMembers = new String[targetMembers.length];
		this.targetMembers = targetMembers;
	}

	@Override
	public boolean check(Task tobeChecked) {
		for (int i = 0; i < targetMembers.length; i++) {
			for (String developer : tobeChecked.getDevelopers()) {
				if (developer.compareToIgnoreCase(targetMembers[i]) == 0) {
					return true;
				}
			}
		}
		return false;
	}
}