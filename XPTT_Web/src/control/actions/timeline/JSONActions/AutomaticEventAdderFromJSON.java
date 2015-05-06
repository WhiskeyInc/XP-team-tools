package control.actions.timeline.JSONActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import timeline.AutomaticEvent;
import timeline.Event;
import timeline.Timeline;
import control.HttpAction;
import control.actions.timeline.TimelineAction;

public class AutomaticEventAdderFromJSON extends TimelineAction implements HttpAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		reader.reset();
		String jsonString = reader.readLine();
		Event event = createEventFromJson(jsonString);
		Timeline timeline = super.getTimeline(request);
		try {
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Event createEventFromJson(String jsonString) {
		JSONParser parser = new JSONParser();
		Event event = null;
		try {
			JSONObject json = (JSONObject) parser.parse(jsonString);
			String name = (String) json.get("event_name");
			event = new AutomaticEvent(name, TimeZone.getDefault());
			this.addParticipantsToEvent(json, event);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	private void addParticipantsToEvent(JSONObject json, Event event) {
		JSONArray participants = (JSONArray) json.get("participants");
		for (int i = 0; i < participants.size(); i++) {
			event.addParticipant((String) participants.get(i));
		}
	}

}
