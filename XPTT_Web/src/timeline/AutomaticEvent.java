package timeline;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * AutomaticEvent class represents a particular event that happens to be created
 * automatically.
 * 
 * @author simone
 * @see Event
 *
 */
public class AutomaticEvent extends Event {

	/**
	 * Creates a new instance of this class. The effect will be just the same of
	 * calling {@link Event#Event(String, GregorianCalendar, boolean)} but the
	 * date will be deduced for the automatic creation of this object and the
	 * editable attribute will always be set to false.
	 * 
	 * @param name
	 *            : the name of this event
	 * @param locale
	 *            : the {@link TimeZone} instance to be used to deduce current
	 *            date
	 * @see Event#Event(String, GregorianCalendar, boolean)
	 */
	public AutomaticEvent(String name, TimeZone locale) {
		super(name, (GregorianCalendar) Calendar.getInstance(locale), false);
	}

}
