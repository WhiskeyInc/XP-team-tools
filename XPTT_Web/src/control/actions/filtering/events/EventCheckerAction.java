package control.actions.filtering.events;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import control.actions.filtering.CheckerAction;

public abstract class EventCheckerAction extends CheckerAction<Event> {

	protected void redirect(HttpServletResponse response) throws IOException{
		response.sendRedirect("timeline.jsp");
	}

}
