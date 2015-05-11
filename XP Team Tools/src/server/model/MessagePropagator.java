package server.model;

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

	private ClientsManager2 clientsManager;
	private IPropagator propagator;
	
	public MessagePropagator(ClientsManager2 clientsManager,
			IPropagator propagator) {
		super();
		this.clientsManager = clientsManager;
		this.propagator = propagator;
	}

	public MessagePropagator(ClientsManager2 clientsManager) {
		super();
		this.clientsManager = clientsManager;
	}

	//Template method
	public void propagateMessage(String message, ClientDetails details) throws IOException {
		ClientConnectionDetails conDet = clientsManager.get(details);
		try {
			if (conDet != null && conDet.isOnline()) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						propagator.getSocket(conDet).getOutputStream()));
				out.write(Formatter.appendNewLine(message));
				out.flush();
			} else {
				System.err.println("Condet is "+ conDet);
			}
		} catch (SocketException e) {
			propagator.handleSuddenDisconnection(conDet);
		}
	}
	public void setPropagator(IPropagator propagator) {
		this.propagator = propagator;
	}
}
