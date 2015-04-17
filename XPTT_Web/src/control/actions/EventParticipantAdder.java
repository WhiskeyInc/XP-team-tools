/**
 * 
 */
package control.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NoSuchEventException;
import timeline.Timeline;
import control.HttpAction;

/**
 * @author lele
 *
 */
public class EventParticipantAdder extends EventHttpAction implements HttpAction {

	/* (non-Javadoc)
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = super.getTimelineFromRequest(request);
		try {
			timeline.getEvent(request.getParameter("event")).addParticipant(request.getParameter("participant"));
		} catch (NoSuchEventException e) {
			e.printStackTrace();
		}
		super.redirect(response);
	}

}
