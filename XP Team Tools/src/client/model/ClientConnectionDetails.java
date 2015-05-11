package client.model;

import java.io.IOException;
import java.net.Socket;

/**
 * It wraps the dynamic client's attributes as sockets and the client' s
 * status[online-offline]. It extends {@link ClientDetails}.
 * It handles two sockets, one for the real time channel [for example an istant-chat]
 * one used waiting the Server response.
 * 
 * This class override the equals method, returning true if and only if
 * the two ClientConnectionDetails(or ClientDetails) have the same nickname 
 * and the same teamName
 * 
 * @author alberto
 *
 */
public class ClientConnectionDetails extends ClientDetails {

	public ClientConnectionDetails(String nickname, String teamName) {
		super(nickname, teamName);
	}
	
	public ClientConnectionDetails(String nickname, String teamName, String pwd) {
		super(nickname, teamName, pwd);
	}

	private Socket realTimesocket;
	private Socket requestSocket;
	private boolean online;

	
	/**
	 * Return the real time Socket
	 * @return
	 */
	public Socket getRealTimeSocket() {
		return realTimesocket;
	}
	/**
	 * Checks if the client is online
	 * @return
	 */
	public boolean isOnline() {
		return online;
	}
	/**
	 * Sets the actual status of the client
	 * @param online
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public void closeRealTimeSocket() throws IOException {
		realTimesocket.close();
	}

	public void setRealTimeSocket(Socket socket) {
		this.realTimesocket = socket;
	}

	public Socket getRequestSocket() {
		return requestSocket;
	}

	public void setRequestSocket(Socket requestSocket) {
		this.requestSocket = requestSocket;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClientConnectionDetails)
				&& !(obj instanceof ClientDetails)) {
			return false;
		}
		ClientDetails det = (ClientDetails) obj;
		if (nickname.equals(det.nickname)) {
			return true;
		}
		return false;
	}
}
