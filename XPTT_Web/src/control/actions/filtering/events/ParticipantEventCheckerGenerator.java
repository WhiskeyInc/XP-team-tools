package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import filtering.Checker;
import filtering.checkers.ParticipantsEventChecker;

/**
 * This class generates and sets in the session context an instance of
 * {@link ParticipantsEventChecker} class. To properly work, this class requires
 * that the following attributes are properly set in the request: participant.
 * These attributes are assumed to contain the information required to build the
 * checker.
 * 
 * @author lele, simo, incre, andre
 * @see {@link EventCheckerAction}, {@link ParticipantsEventChecker}
 */
public class ParticipantEventCheckerGenerator extends EventCheckerAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<Event> checker = new ParticipantsEventChecker(
				request.getParameter("participant"));
		super.setSessionChecker(request, checker);
		super.redirect(response);
	}

}
