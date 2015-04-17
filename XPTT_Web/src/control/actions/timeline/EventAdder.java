package control.actions.timeline;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import timeline.Event;

public class EventAdder extends DateHandlerAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * control.actions.EventHttpAction#perform(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Event event = createEventFromRequest(request);
		addEventToTimeline(request, event);
		super.redirect(response);
	}

	private void addEventToTimeline(HttpServletRequest request, Event event) {
		try {
			super.getTimeline(request).addEvent(event);
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
