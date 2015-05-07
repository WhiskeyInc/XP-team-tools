package server.model;

import java.net.Socket;

import client.model.ClientConnectionDetails;
/**
 * It' s a implementation of {@link IPropagator} that give back a socket
 * which is responsible of real-time messages
 * @author alberto
 *
 */
public class RealTimePropagator implements IPropagator {

	@Override
	public Socket getSocket(ClientConnectionDetails conDet) {
		return conDet.getRealTimeSocket();
	}

	@Override
	public void handleSuddenDisconnection(ClientConnectionDetails conDet) {
		conDet.setOnline(false);
	}

	
}
