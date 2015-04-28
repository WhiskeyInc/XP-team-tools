package control.actions.timeline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import timeline.Timeline;
import control.HttpAction;

public abstract class TimelineAction implements HttpAction {

	protected void redirect(HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("timeline.jsp");
	}

	protected Timeline getTimeline(HttpServletRequest request) {
		Project currentProject =(Project) request.getSession().getAttribute("currentProject");
		if(currentProject == null){
			currentProject =(Project) request.getServletContext().getAttribute("defaultProject");
		}
		return currentProject.getTimeline();
	}
}
