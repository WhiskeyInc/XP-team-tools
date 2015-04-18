package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;

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
		try {
			super.getTimeline(request).addEvent(
					request.getParameter("eventName"), true,
					super.generateEventDate(request), null);
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
	}
}
