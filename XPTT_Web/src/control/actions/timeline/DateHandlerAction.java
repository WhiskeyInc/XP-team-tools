package control.actions.timeline;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

/**
 * This class represent an abstraction for every {@link TimelineAction} which
 * handles dates. It provides the method to get a date form a request.
 * 
 * @author lele, simo, incre, andre
 * @see {@link TimelineAction}, {@link GregorianCalendar}
 *
 */
public abstract class DateHandlerAction extends TimelineAction {

	/**
	 * To work properly, this class requires that the following attributes are
	 * properly set in the request: eventYear, eventMonth, eventDay, eventHour,
	 * eventMin.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return the date specified in the request in a GregorianCalendar object
	 */
	protected GregorianCalendar generateEventDate(HttpServletRequest request) {
		int year = Integer.parseInt(request.getParameter("eventYear"));
		int month = Integer.parseInt(request.getParameter("eventMonth"));
		int day = Integer.parseInt(request.getParameter("eventDay"));
		int hour = Integer.parseInt(request.getParameter("eventHour"));
		int min = Integer.parseInt(request.getParameter("eventMin"));
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day,
				hour, min, 0);
		return date;

	}

}
