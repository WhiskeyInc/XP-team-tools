package control.actions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import timeline.Timeline;
import control.Action;

public class EventMover implements Action {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Timeline timeline = (Timeline) request.getServletContext()
				.getAttribute("timeline");
		try {
			timeline.moveEvent(request.getParameter("event"), this.generateEventDate(request));
		} catch (UnEditableEventException e) {
			e.printStackTrace();
		} catch (NoSuchEventException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (InvalidDateException e) {
			// TODO: gestire l'eccezione
			e.printStackTrace();
		}
		this.redirect(response);

	}

	private void redirect(HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("timeline.jsp");
	}

	private GregorianCalendar generateEventDate(HttpServletRequest request) {
		int year = Integer.parseInt(request.getParameter("eventYear"));
		int month = Integer.parseInt(request.getParameter("eventMonth"));
		int day = Integer.parseInt(request.getParameter("eventDay"));
		int hour = Integer.parseInt(request.getParameter("eventHour"));
		int min = Integer.parseInt(request.getParameter("eventMin"));
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day,
				hour, min, 0);
		return date;

	}

}
