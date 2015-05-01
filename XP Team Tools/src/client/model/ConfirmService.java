package client.model;

import java.util.Observer;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;

public class ConfirmService implements IClientService {

	private volatile boolean isConfirmed;
	
	@Override
	public void setAttribute(String request) {
		try {
			isConfirmed = JsonParser.parseConfirm(request);
		} catch (ParseException e) {
			isConfirmed = false;
			e.printStackTrace();
		}
	}

	@Override
	public String[] getAttribute() {
		return null;//String.valueOf(isConfirmed);
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

}
