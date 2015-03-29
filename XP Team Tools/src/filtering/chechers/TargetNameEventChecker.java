package filtering.chechers;

import timeline.Event;
import filtering.Checker;

public class TargetNameEventChecker implements Checker<Event> {

	private String name;

	public TargetNameEventChecker(String name) {
		this.name = name;
	}

	@Override
	public boolean check(Event tobeChecked) {
		if (tobeChecked.toString().contains(name)) {
			return true;
		}
		return false;
	}

}
