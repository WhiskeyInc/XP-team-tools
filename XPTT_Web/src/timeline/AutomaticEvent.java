package timeline;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AutomaticEvent extends Event {

	public AutomaticEvent(String name) {
		super(name, (GregorianCalendar) Calendar.getInstance(TimeZone
				.getTimeZone("Europe/Rome")), false);
	}

}
