package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import timeline.Timeline;

/**
 * This class changes the date of a specific event from the timeline of the
 * project the user is visualizing. To work properly, this class requires that
 * the following attributes are properly set in the request: eventId - the
 * unique identifier of the event, all the attributes {@link DateHandlerAction}
 * requires to build the new date.
 * 
 * @author lele, simo, incre, andre
 * @see {@link DateHandlerAction}, {@link Event}
 *
 */
public class EventMover extends DateHandlerAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = super.getTimeline(request);
		try {
			timeline.moveEvent(
					Integer.parseInt(request.getParameter("eventId")),
					super.generateEventDate(request));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		super.redirect(response);

	}
}
