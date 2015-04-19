package timeline;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AutomaticEvent extends Event {

	public AutomaticEvent(String name) {
		super(name, (GregorianCalendar) Calendar.getInstance(),false);
	}

}
