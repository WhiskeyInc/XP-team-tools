package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import control.actions.filtering.CheckerAction;

/**
 * This class represents an abstraction of a generic action dealing with
 * filtering of Event instances. It inherits the methods of
 * {@link CheckerAction} and adds the redirection to a proper resource.
 * 
 * @author lele, simo, incre, andre
 * @see {@link CheckerAction}
 */
public abstract class EventCheckerAction extends CheckerAction<Event> {

	/**
	 * Redirect the response to "timeline.jsp"
	 * 
	 * @param response
	 *            : the {@link HttpServletResponse} for the specific request
	 * @throws IOException
	 */
	protected void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("timeline.jsp");
	}

}
