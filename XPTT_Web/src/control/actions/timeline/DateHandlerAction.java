package control.actions.timeline;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

public abstract class DateHandlerAction extends TimelineAction {

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
