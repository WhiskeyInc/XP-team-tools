package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.actions.timeline.EventAdder;
import control.actions.timeline.EventDeleter;
import control.actions.timeline.EventMover;
import control.actions.timeline.EventParticipantAdder;

/**
 * This HttpServlet performs any action required to manage a Timeline and its
 * events
 */
@WebServlet("/TimelineController")
public class TimelineController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimelineController() {
		super();
		this.initializeMap();
	}

	private void initializeMap() {
		this.actions.put("addition", new EventAdder());
		this.actions.put("changeDate", new EventMover());
		this.actions.put("deletion", new EventDeleter());
		this.actions.put("addParticipant", new EventParticipantAdder());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(request.getParameter("action")).perform(request,
				response);
	}

}
