package control.actions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Action;
import model.exceptions.InvalidDateException;
import timeline.Event;
import timeline.Timeline;

public class EventAdder implements Action {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		Event event = createEventFromRequest(request);
		addEventToTimeline(request, event);
		try {
			redirect(response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void redirect(HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("timeline.jsp");
	}

	private void addEventToTimeline(HttpServletRequest request, Event event) {
		try {
			((Timeline) request.getServletContext().getAttribute("timeline"))
					.addEvent(event);
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
	}

	private Event createEventFromRequest(HttpServletRequest request) {
		GregorianCalendar date = generateEventDate(request);
		Event event = new Event(request.getParameter("eventName"), date);
		return event;
	}

	private GregorianCalendar generateEventDate(HttpServletRequest request) {
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
