/**
 * 
 */
package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NoSuchEventException;
import timeline.Timeline;

/**
 * @author lele
 *
 */
public class EventParticipantAdder extends TimelineAction {

	/* (non-Javadoc)
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = super.getTimeline(request);
		try {
			timeline.getEvent(Integer.parseInt(request.getParameter("eventId"))).addParticipant(request.getParameter("participant"));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		super.redirect(response);
	}

}
