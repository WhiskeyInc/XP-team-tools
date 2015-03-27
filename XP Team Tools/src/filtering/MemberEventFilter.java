package filtering;

import timeline.Event;

public class MemberFilter implements Checker<Event> {
	
	private String targetMember;
	
	public MemberFilter(String targetMember) {
		this.targetMember = targetMember;
	}

	@Override
	public boolean check(Event tobeChecked) {
		for (String participant : tobeChecked.getParticipants()) {
			if (participant.compareToIgnoreCase(targetMember)==0) {
				return true;
			}
		}
		return false;
	}

}
