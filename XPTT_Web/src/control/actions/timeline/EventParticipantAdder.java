/**
 * 
 */
package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import timeline.Timeline;

/**
 * This class adds a participant an event to the timeline of the project the
 * user is visualizing. To work properly, this class requires that the following
 * attributes are properly set in the request: eventId - the unique identifier
 * of the event, participant - the name of the participant to be added.
 * 
 * @author lele, simo, incre, andre
 * @see {@link TimelineAction}, {@link Event}
 *
 */
public class EventParticipantAdder extends TimelineAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = super.getTimeline(request);
		try {
			String participant = request.getParameter("participant");
			if (participant.trim().equals("")) {
				throw new Exception("For input string: \"\"");
			}
			timeline.getEvent(Integer.parseInt(request.getParameter("eventId")))
					.addParticipant(participant);
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		super.redirect(response);
	}

}
