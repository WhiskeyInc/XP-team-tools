package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NoSuchEventException;
import timeline.Timeline;

public class EventDeleter extends TimelineAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			super.getTimeline(request).deleteEvent(request.getParameter("event"));
		} catch (NoSuchEventException e) {
			e.printStackTrace();
		}		
		super.redirect(response);
	}
}
