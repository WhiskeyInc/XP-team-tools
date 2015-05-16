package server.model.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import server.events.IEventActionRequest;
/**
 * 
 *
 */
public class EventService implements IService{

	private IEventActionRequest eventSender;

	public EventService(IEventActionRequest eventSender) {
		super();
		this.eventSender = eventSender;
	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		
		eventSender.sendJson(line);
	}
	

}
