package client.model;

import java.util.Observable;

import org.json.simple.parser.ParseException;

import server.model.JsonParser;
import timer.TimerFormatter;

public class SetTimeStampService extends Observable implements IClientService {

	private String currentTimestamp = "00:10";

	@Override
	public void setAttribute(String attribute) {
		try {
			String[] lines = JsonParser.parseTimerRequest(attribute);
			currentTimestamp = TimerFormatter.getDisplay(
					Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
			update();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAttribute() {
		return currentTimestamp;
	}

	private void update() {
		setChanged();
		notifyObservers();
	}
}
