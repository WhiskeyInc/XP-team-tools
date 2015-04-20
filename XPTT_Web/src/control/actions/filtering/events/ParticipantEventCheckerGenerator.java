package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.ParticipantsEventChecker;

public class ParticipantEventCheckerGenerator extends EventCheckerAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<Event> checker = new ParticipantsEventChecker(
				request.getParameter("participant"));
		super.returnChecker(request, checker);
		super.redirect(response);
	}

}
