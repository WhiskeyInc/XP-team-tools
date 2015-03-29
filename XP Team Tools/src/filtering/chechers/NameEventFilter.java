package filtering.chechers;

import timeline.Event;
import filtering.Checker;

public class NameEventFilter implements Checker<Event> {

	private String name;

	public NameEventFilter(String name) {
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
