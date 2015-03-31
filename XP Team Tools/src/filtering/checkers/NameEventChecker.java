package filtering.checkers;

import timeline.Event;
import filtering.Checker;

public class NameEventChecker implements Checker<Event> {

	private String name;

	public NameEventChecker(String name) {
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
