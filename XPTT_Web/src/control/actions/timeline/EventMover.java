package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;
import timeline.Timeline;

public class EventMover extends DateHandlerAction{

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Timeline timeline = super.getTimeline(request);
		try {
			timeline.moveEvent(Integer.parseInt(request.getParameter("eventId")),
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
