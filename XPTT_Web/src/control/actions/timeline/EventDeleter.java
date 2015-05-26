package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;

/**
 * This class deletes an event to the timeline of the project the user is
 * visualizing. To work properly, this class requires that the following
 * attributes are properly set in the request: eventId - the unique identifier
 * of the event.
 * 
 * @author lele, simo, incre, andre
 * @see {@link TimelineAction}, {@link Event}
 *
 */
public class EventDeleter extends TimelineAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			super.getTimeline(request).deleteEvent(
					Integer.parseInt(request.getParameter("eventId")));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		super.redirect(response);
	}
}
