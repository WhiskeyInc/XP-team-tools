package filtering;

import java.util.GregorianCalendar;

import timeline.Event;

public class DateEventFilter implements Checker<Event> {

	private GregorianCalendar fromdate;
	private GregorianCalendar todate;

	public DateEventFilter(GregorianCalendar fromdate,GregorianCalendar todate) {
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
