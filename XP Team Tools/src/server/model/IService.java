package server.model;

import java.io.IOException;
import java.net.Socket;

import org.json.simple.parser.ParseException;

/**
 * Generico Servizio
 * 
 * TODO: rivedere javadoc
 * 
 * @author nicola
 */
public interface IService {

	/**
	 * TODO :
	 * 
	 * @param clientSocket
	 * @param uri
	 * @throws IOException
	 */
	public void doAction(final Socket clientSocket, String line)
			throws IOException, ParseException;

}
