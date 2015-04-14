package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NoSuchEventException;
import timeline.Timeline;

/**
 * Servlet implementation class Eventdeleter
 */
@WebServlet("/EventDeleter")
public class EventDeleter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDeleter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Timeline timeline = (Timeline) request.getServletContext().getAttribute("timeline");
		try {
			timeline.deleteEvent(request.getParameter("event"));
			response.sendRedirect("timeline.jsp");
		} catch (NoSuchEventException e) {
			e.printStackTrace();
		}
	}

}
