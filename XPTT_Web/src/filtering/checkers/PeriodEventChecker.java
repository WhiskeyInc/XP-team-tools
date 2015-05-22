package filtering.checkers;

import java.util.GregorianCalendar;

import timeline.Event;
import filtering.Checker;

/**
 * This simple implementation of {@link Checker} interface provides
 * {@link Event} validation over dates range names comparison. An event is
 * considered valid if its date is inside the specified range.
 * 
 * @author simone, lele, incre, andre
 *
 */
public class PeriodEventChecker implements Checker<Event> {

	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;

	/**
	 * Creates a new instance of this filter
	 * @param fromdate: the starting date of the range where an event is valid
	 * @param todate: the ending date of the range where an event is valid
	 */
	public PeriodEventChecker(GregorianCalendar fromdate,
			GregorianCalendar todate) {
		this.fromDate = fromdate;
		this.toDate = todate;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see filtering.Checker#check(java.lang.Object)
	 */
	public boolean check(Event tobeChecked) {
		if ((tobeChecked.getDate().after(fromDate))
				&& (tobeChecked.getDate().before(toDate))) {
			return true;
		}
		return false;
	}
}
