package control.actions.filtering.events;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.PeriodEventChecker;

public class EventPeriodFilterGenerator extends EventCheckerAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<Event> checker;
		try {
			checker = new PeriodEventChecker(generateFromDate(request),
					generateTODate(request));
			super.returnChecker(request, checker);
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
