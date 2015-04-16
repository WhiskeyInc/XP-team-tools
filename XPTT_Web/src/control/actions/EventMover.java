package control.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import timeline.Timeline;
import control.HttpAction;

public class EventMover extends EventHttpAction implements HttpAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = (Timeline) request.getServletContext()
				.getAttribute("timeline");
		try {
			timeline.moveEvent(request.getParameter("event"),
					super.generateEventDate(request));
		} catch (UnEditableEventException e) {
			e.printStackTrace();
		} catch (NoSuchEventException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (InvalidDateException e) {
			// TODO: gestire l'eccezione
			e.printStackTrace();
		}
		super.redirect(response);

	}
}
