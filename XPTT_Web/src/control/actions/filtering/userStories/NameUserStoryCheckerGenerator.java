package control.actions.filtering.userStories;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.UserStoryBoard.UserStory;
import control.HttpAction;
import filtering.Checker;
import filtering.checkers.nameUserStoryChecker;

public class NameUserStoryCheckerGenerator extends UserStoryCheckerAction
		implements HttpAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Checker<UserStory> checker = new nameUserStoryChecker(
				request.getParameter("title"));
		super.returnChecker(request, checker);
		super.redirect(response);
	}

}
