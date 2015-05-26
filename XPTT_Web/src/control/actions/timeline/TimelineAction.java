package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import timeline.Timeline;
import control.HttpAction;

/**
 * This class provides an abstraction for every action which involves a
 * {@link Timeline}. It provides the basic methods to get the correct timeline
 * and redirect the response to the correct resource after the action is
 * performed.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}
 *
 */
public abstract class TimelineAction implements HttpAction {

	/**
	 * Redirect the response to "timeline.jsp"
	 * 
	 * @param response
	 *            : the {@link HttpServletResponse} for the specific request
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void redirect(HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("timeline.jsp");
	}

	/**
	 * Return the timeline of the {@link Project} project the user is currently
	 * visualizing - ie the one set in the session context. If no project is
	 * set, a default project is used.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return the correct instance of {@link Timeline}
	 */
	protected Timeline getTimeline(HttpServletRequest request) {
		Project currentProject = (Project) request.getSession().getAttribute(
				"currentProject");
		if (currentProject == null) {
			currentProject = (Project) request.getServletContext()
					.getAttribute("defaultProject");
		}
		return currentProject.getTimeline();
	}
}
