package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import server.events.IEventActionRequest;
/**
 * It allow the server to add a Team
 * @author alberto
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
		//line è il json ricevuto dal client
		
		//gli aggiungo i partecipanti
		String newJson;
		newJson = JsonMaker.addParticipantsToEventJson(line, null);
		
		//e lo giro all'altro server
		eventSender.sendEventAction(newJson);
	}
	

}
