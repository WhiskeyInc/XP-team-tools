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

public abstract class JSONAction implements HttpAction {

	@Override
	public abstract void perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	@SuppressWarnings("unchecked")
	protected Timeline getTimeline(HttpServletRequest request, JSONObject json) {
		HashMap<String, ProjectsCollector> environviments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		String user = this.getFieldFromJson(json, "user");
		ProjectsCollector collector = environviments.get(user);
		Timeline timeline = collector.getProject(SerializerCollector.FIRST_ID)
				.getTimeline();
		return timeline;
	}

	protected String getFieldFromJson(JSONObject json, String field) {
		return (String) json.get(field);
	}

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

	protected void addParticipantsToEvent(JSONObject json, Event event) {
		JSONArray participants = (JSONArray) json.get("participants");
		for (int i = 0; i < participants.size(); i++) {
			event.addParticipant((String) participants.get(i));
		}
	}

}
