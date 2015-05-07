package server.model;

import java.net.Socket;

import client.model.ClientConnectionDetails;
/**
 * This interface abstracts on the socket returned to
 * start the message communication and on the method 
 * responsable of a sudden disconnection from the client
 * @author alberto
 *
 */
public interface IPropagator {

	public abstract Socket getSocket(ClientConnectionDetails conDet);

	public abstract void handleSuddenDisconnection(ClientConnectionDetails conDet);

}
