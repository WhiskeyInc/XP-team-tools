package filtering.checkers;

import java.util.GregorianCalendar;

import timeline.Event;
import filtering.Checker;

public class PeriodEventChecker implements Checker<Event> {

	private GregorianCalendar fromdate;
	private GregorianCalendar todate;

	public PeriodEventChecker(GregorianCalendar fromdate,GregorianCalendar todate) {
		this.fromdate = fromdate;
		this.todate = todate;
	}

	@Override
	public boolean check(Event tobeChecked) {
		if((tobeChecked.getDate().after(fromdate)) && (tobeChecked.getDate().before(todate))){
			return true;
		}
		return false;
	}
}
