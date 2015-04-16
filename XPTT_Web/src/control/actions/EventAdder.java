package control.actions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import timeline.Event;
import timeline.Timeline;
import control.HttpAction;

public class EventAdder extends EventHttpAction implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * control.actions.EventHttpAction#perform(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		Event event = createEventFromRequest(request);
		addEventToTimeline(request, event);
		try {
			super.redirect(response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		GregorianCalendar date = super.generateEventDate(request);
		Event event = new Event(request.getParameter("eventName"), date);
		return event;
	}
}
