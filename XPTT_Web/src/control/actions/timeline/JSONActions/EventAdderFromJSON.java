package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;

import org.json.simple.JSONObject;

import timeline.Event;
import timeline.Timeline;

public class EventAdderFromJSON extends JSONAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json = super.getJSONFromRequest(request, response);
		String name = super.getFieldFromJson(json, "event_name");
		Event event = new Event(name, this.getDateFromJSON(json));
		super.addParticipantsToEvent(json, event);
		Timeline timeline = super.getTimeline(request, json);
		try {
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private GregorianCalendar getDateFromJSON(JSONObject json) {
		int year = Integer.parseInt((String) this
				.getFieldFromJson(json, "year"));
		int month = Integer.parseInt((String) this.getFieldFromJson(json,
				"month"));
		int day = Integer.parseInt((String) this.getFieldFromJson(json, "day"));
		int hour = Integer.parseInt((String) this
				.getFieldFromJson(json, "hour"));
		int min = Integer.parseInt((String) this.getFieldFromJson(json, "min"));
		GregorianCalendar date = new GregorianCalendar(year, month, day, hour,
				min);
		return date;
	}

}