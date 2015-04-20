package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.NameEventChecker;

public class NameEventCheckerGenerator extends EventCheckerAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<Event> checker = new NameEventChecker(
				request.getParameter("event"));
		super.returnChecker(request, checker);
		super.redirect(response);
	}

}
