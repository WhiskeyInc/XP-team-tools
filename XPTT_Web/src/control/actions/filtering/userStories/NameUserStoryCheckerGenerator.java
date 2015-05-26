package control.actions.filtering.userStories;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.UserStoryBoard.UserStory;
import control.HttpAction;
import filtering.Checker;
import filtering.checkers.NameUserStoryChecker;

/**
 * This class generates and sets in the session context an instance of
 * {@link NameUserStoryChecker} class. To properly work, this class requires
 * that the following attributes are properly set in the request: title. These
 * attributes are assumed to contain the information required to build the
 * checker.
 * 
 * @author lele, simo, incre, andre
 * @see {@link UserStoryCheckerAction}, {@link NameUserStoryChecker}
 */

public class NameUserStoryCheckerGenerator extends UserStoryCheckerAction
		implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<UserStory> checker = new NameUserStoryChecker(
				request.getParameter("title"));
		super.setSessionChecker(request, checker);
		super.redirect(response);
	}

}
