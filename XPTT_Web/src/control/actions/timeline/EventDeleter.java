package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventDeleter extends TimelineAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			super.getTimeline(request).deleteEvent(Integer.parseInt(request.getParameter("eventId")));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}		
		super.redirect(response);
	}
}
