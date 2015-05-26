package control.actions.timeline.JSONActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.ProjectsCollector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import timeline.Event;
import timeline.Timeline;
import util.serialization.SerializerCollector;
import control.HttpAction;

/**
 * This class provides an abstraction for all the actions which deals with json.
 * Its methods parse a {@link JSONObject} and makes the information available to
 * other classes or insert fields into a JSONObject. The names of the expected
 * json's field are specified in static constants.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}
 *
 */
public abstract class JSONAction implements HttpAction {

	public static final String USER_FIELD = "user";
	public static final String PARTICIPANTS_FIELD = "participants";

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public abstract void perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	/**
	 * Return the timeline in the default project of a user specified in the
	 * JSON.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @param json
	 *            : the {@link JSONObject to be parsed}
	 * @return the {@link Timeline} instance
	 */
	@SuppressWarnings("unchecked")
	protected Timeline getTimeline(HttpServletRequest request, JSONObject json) {
		HashMap<String, ProjectsCollector> environviments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		String user = this.getFieldFromJson(json, JSONAction.USER_FIELD);
		ProjectsCollector collector = environviments.get(user);
		Timeline timeline = collector.getProject(SerializerCollector.FIRST_ID)
				.getTimeline();
		return timeline;
	}

	/**
	 * Returns a specific field of a {@link JSONObject}.
	 * 
	 * @param json
	 *            : the {@link JSONObject} to be parsed
	 * @param field
	 *            : the name of the field to be parsed
	 * @return
	 */
	protected String getFieldFromJson(JSONObject json, String field) {
		return (String) json.get(field);
	}

	/**
	 * Returns a JSONObject taken from the body of a {@link HttpServletRequest}.
	 * It send an internal error code in the response if the operation fails.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @param response
	 *            : the response for a specific {@link HttpServletRequest}
	 * @return the JSONObject instance
	 * @throws IOException
	 */
	protected JSONObject getJSONFromRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		BufferedReader reader = request.getReader();
		reader.reset();
		String jsonString = reader.readLine();
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			return json;
		} catch (ParseException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	/**
	 * Add to an event the participants taken form a json.
	 * 
	 * @param json
	 *            : the {@link JSONObject} to be parsed
	 * @param event
	 *            : the event participants should be added to
	 */
	protected void addParticipantsToEvent(JSONObject json, Event event) {
		JSONArray participants = (JSONArray) json
				.get(JSONAction.PARTICIPANTS_FIELD);
		for (int i = 0; i < participants.size(); i++) {
			event.addParticipant((String) participants.get(i));
		}
	}

}
