package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.PeriodEventChecker;

public class EventPeriodFilterGenerator extends EventCheckerAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO: generate checker
		super.returnChecker(request, null);
		super.redirect(response);
	}

}
