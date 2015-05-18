package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;

import org.json.simple.JSONObject;

import timeline.Event;
import timeline.Timeline;
import timeline.events.AutomaticEvent;
import timeline.events.MacroEvent;

public class AutomaticEventAdderFromJSON extends JSONAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject json = super.getJSONFromRequest(request, response);
		String name = super.getFieldFromJson(json, "event_name");
		int id = Integer.parseInt(super.getFieldFromJson(json, "id"));
		Event event = new AutomaticEvent(name, TimeZone.getDefault());
		super.addParticipantsToEvent(json, event);
		Timeline timeline = super.getTimeline(request, json);
		try {
			MacroEvent macroEvent = (MacroEvent) timeline.getEvent(id);
			macroEvent.addEvent(event);			
		} catch (InvalidDateException | NoSuchEventException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
