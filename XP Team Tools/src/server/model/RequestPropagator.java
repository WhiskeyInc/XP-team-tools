package server.model;

import java.net.Socket;

import client.model.ClientConnectionDetails;

public class RequestPropagator  implements IPropagator{

	@Override
	public Socket getSocket(ClientConnectionDetails conDet) {
		return conDet.getRequestSocket();
	}

	@Override
	public void handleSuddenDisconnection(ClientConnectionDetails conDet) {
		conDet.setOnline(false);
	}

}
