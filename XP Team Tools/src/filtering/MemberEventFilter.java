package filtering;

import timeline.Event;

public class MemberEventFilter implements Checker<Event> {
	
	private String[] targetMembers;
	
	public MemberEventFilter(String ... targetMembers) {
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
