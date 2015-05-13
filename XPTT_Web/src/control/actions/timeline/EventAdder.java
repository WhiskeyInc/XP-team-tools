package control.actions.timeline;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.MacroEvent;
import util.serialization.LocalIdentifiabilitySerializer;

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
		addEventToTimeline(request);
		super.redirect(response);
	}

	private void addEventToTimeline(HttpServletRequest request) {
		String eventName = request.getParameter("eventName");
		try {
			if (eventName.trim().equals("")) {
				throw new Exception("You should give a name to your event");
			}
			if (!request.getParameter("macro").equals("on")) {
				super.getTimeline(request).addEvent(
						new Event(request.getParameter("eventName"), super
								.generateEventDate(request), true));
			} else {
				super.getTimeline(request).addEvent(
						new MacroEvent(eventName, this
								.generatefromEventDate(request), super
								.generateEventDate(request),
								new ConcreteTimeline(TimeZone
										.getTimeZone("Europe/Rome"),
										new LocalIdentifiabilitySerializer())));
			}
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
	}

	private GregorianCalendar generatefromEventDate(HttpServletRequest request) {
		int year = Integer.parseInt(request.getParameter("fromEventYear"));
		int month = Integer.parseInt(request.getParameter("fromEventMonth"));
		int day = Integer.parseInt(request.getParameter("fromEventDay"));
		int hour = Integer.parseInt(request.getParameter("fromEventHour"));
		int min = Integer.parseInt(request.getParameter("fromEventMin"));
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day,
				hour, min, 0);
		return date;
	}
}
