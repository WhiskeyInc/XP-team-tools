package server.model.propagator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketException;

import string.formatter.Formatter;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

/**
 * The class responsible of the message propagation,
 * it has only one method, a template method which needs a
 * {@link IPropagator} to propagate the message.
 * It' s a component of the Bridge Pattern, to maintain more
 * flexibility
 * @author alberto
 *
 */

public class MessagePropagator {

	private volatile ClientsManager clientsManager;
	private IPropagator propagator;
	
	public MessagePropagator(ClientsManager clientsManager,
			IPropagator propagator) {
		super();
		this.clientsManager = clientsManager;
		this.propagator = propagator;
	}

	public MessagePropagator(ClientsManager clientsManager) {
		super();
		this.clientsManager = clientsManager;
	}

	/**
	 * propagates the message to other clients
	 * @param message
	 * @param details
	 * @throws IOException
	 */
	public void propagateMessage(String message, ClientDetails details) throws IOException {
		ClientConnectionDetails conDet = clientsManager.get(details);
		

		try {
			if (conDet != null && conDet.isOnline() == true) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						propagator.getSocket(conDet).getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				
				out.flush();
			}

		} catch (SocketException e) {
			propagator.handleSuddenDisconnection(conDet);
		}
	}
	public void setPropagator(IPropagator propagator) {
		this.propagator = propagator;
	}
}
