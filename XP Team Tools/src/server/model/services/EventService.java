package server.model.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import events.IEventActionRequest;
/**
 * An implementation of @IService for the various events, it overrides doAction 
 * method and propagate a certain event request to second server
 * 
 * @author nicola
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
