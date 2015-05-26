package control.actions.filtering.events;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.PeriodEventChecker;

/**
 * This class generates and sets in the session context an instance of
 * {@link PeriodEventChecker} class. The time range where an event is considered
 * valid is got from the request. To work properly, this class requires that the
 * following attributes are properly set in the request: fromEventYear,
 * toEventYear, fromEventMonth, toEventMonth, fromEventDay, toEventDay,
 * fromEventHour, toEventHour, fromEventMin, toEventMin. These attributes are
 * assumed to contain the information required to build the checker.
 * 
 * @author lele, simo, incre, andre
 * @see {@link EventCheckerAction}, {@link PeriodEventChecker}
 */
public class EventPeriodFilterGenerator extends EventCheckerAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<Event> checker;
		try {
			checker = new PeriodEventChecker(generateFromDate(request),
					generateTODate(request));
			super.setSessionChecker(request, checker);
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		super.redirect(response);
	}

	private GregorianCalendar generateFromDate(HttpServletRequest request) {
		return generateDate("from", request);
	}

	private GregorianCalendar generateTODate(HttpServletRequest request) {
		return generateDate("to", request);
	}

	private GregorianCalendar generateDate(String subString,
			HttpServletRequest request) {
		int year = Integer.parseInt(request.getParameter(subString
				+ "EventYear"));
		int month = Integer.parseInt(request.getParameter(subString
				+ "EventMonth"));
		int day = Integer
				.parseInt(request.getParameter(subString + "EventDay"));
		int hour = Integer.parseInt(request.getParameter(subString
				+ "EventHour"));
		int min = Integer
				.parseInt(request.getParameter(subString + "EventMin"));
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day,
				hour, min, 0);
		return date;

	}

}
