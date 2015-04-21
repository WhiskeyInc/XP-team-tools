package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Timeline;

public class EventMover extends DateHandlerAction {

	@Override
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
