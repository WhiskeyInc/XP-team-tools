package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;

import org.json.simple.JSONObject;

import timeline.AutomaticEvent;
import timeline.Event;
import timeline.Timeline;

public class AutomaticEventAdderFromJSON extends JSONAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json = super.getJSONFromRequest(request, response);
		String name = super.getFieldFromJson(json, "event_name");
		Event event = new AutomaticEvent(name, TimeZone.getDefault());
		super.addParticipantsToEvent(json, event);
		Timeline timeline = super.getTimeline(request, json);
		try {
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
