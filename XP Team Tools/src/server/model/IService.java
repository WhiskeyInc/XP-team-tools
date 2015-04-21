package server.model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

/**
 * General service served from the server
 * @author nicola
 */
public interface IService {

	/**
	 * Function to offer the service when it's asked
	 * 
	 * @param line
	 * @throws IOException
	 * @throws ParseException
	 */
	public void doAction(String line)
			throws IOException, ParseException;

}
