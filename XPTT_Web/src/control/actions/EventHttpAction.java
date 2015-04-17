package control.actions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Timeline;
import control.HttpAction;

public abstract class EventHttpAction implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.Action#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public abstract void perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	protected void redirect(HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("timeline.jsp");
	}

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
	
	protected Timeline getTimelineFromRequest(HttpServletRequest request) {
		return (Timeline) request.getServletContext().getAttribute("timeline");
	}

}
