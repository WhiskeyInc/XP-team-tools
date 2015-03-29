package filtering.chechers;

import filtering.Checker;
import timeline.Event;

public class TargetMembersEventChecker implements Checker<Event> {
	
	private String[] targetMembers;
	
	public TargetMembersEventChecker(String ... targetMembers) {
		this.targetMembers = new String[targetMembers.length];
		this.targetMembers = targetMembers;
	}

	@Override
	public boolean check(Event tobeChecked) {
		for(int i =0;i<targetMembers.length;i++) {
			if(!tobeChecked.getParticipants().contains(targetMembers[i])){
				return false;
			}
		}
		return true;
	}

}
