package control;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import timeline.Event;
import timeline.Timeline;

/**
 * Servlet implementation class EventAdder
 */
@WebServlet("/eventAdder")
public class EventAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventAdder() {
		super();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Event event = createEventFromRequest(request);
		addEventToTimeline(request, event);
		redirect(response);
	}

	private void redirect(HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/XTream/timeline.jsp");
	}

	private void addEventToTimeline(HttpServletRequest request, Event event) {
		try {
			((Timeline) super.getServletContext().getAttribute("timeline"))
					.addEvent(event);
		} catch (InvalidDateException e) {}
	}

	private Event createEventFromRequest(HttpServletRequest request) {
		Event event = new Event(request.getParameter("eventName"),
				new GregorianCalendar());
		return event;
	}

}
