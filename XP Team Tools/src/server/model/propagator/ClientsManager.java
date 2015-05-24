package server.model.propagator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

/**
 * It handle the clients registering each of them, refreshing their dynamic
 * attributes
 * 
 * @author alberto
 *
 */

public class ClientsManager {

	private volatile Set<ClientConnectionDetails> clients = new HashSet<ClientConnectionDetails>();
	private static volatile ClientsManager instance = new ClientsManager();
	private boolean testModeEnabled = false;

	/**
	 * Register a client. If a client is already registered, this method only
	 * refresh his dynamic attributes as sockets
	 * 
	 * @param client
	 * @throws SQLException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public synchronized void registerClient(ClientConnectionDetails client)
			throws NoSuchAlgorithmException, IOException, SQLException {

		if (!isTestModeEnabled()) {
		
			recordClient(client);


		} else {
			recordClient(client);
		}
	}

	private void recordClient(ClientConnectionDetails client) {
		if (has(client)) {
			remove(client);
			
			clients.add(client);
		} else {
			clients.add(client);
		}
	}

	public Set<ClientConnectionDetails> getClients() {
		return clients;
	}

	/**
	 * It returns the {@link ClientConnectionDetails} that has the same team
	 * name and the same nickname of the {@link ClientDetails} passed as
	 * parameter. If there isn't the match, returns null
	 * 
	 * @param {@link ClientDetails}
	 * @return
	 */
	public synchronized ClientConnectionDetails get(ClientDetails det) {
		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			ClientConnectionDetails conDet = iter.next();
			if (conDet.equals(det)) {
				return conDet;
			}
		}
		return null;
	}

	public int size() {
		return clients.size();
	}

	/**
	 * Checks if this client is present in client's list
	 * @param conDet @ClientConnectionDetails instance
	 * @return true if the client is present
	 */
	public boolean has(ClientConnectionDetails conDet) {
		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			if (iter.next().getNickname().equals(conDet.getNickname())) {
				return true;
			}
		}
		return false;
	}

	public synchronized static ClientsManager getInstance() {
		return instance;
	}

	/**
	 * Removes this client
	 * @param conDet
	 */
	public void remove(ClientConnectionDetails conDet) {
		Set<ClientConnectionDetails> clientsTmp = new HashSet<ClientConnectionDetails>();

		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			ClientConnectionDetails det = iter.next();
			if (!det.getNickname().equals(conDet.getNickname())) {
				clientsTmp.add(det);
			}
		}
		clients = clientsTmp;
	}

	public void setTestModeEnabled(boolean testModeEnabled) {
		this.testModeEnabled = testModeEnabled;
	}

	public boolean isTestModeEnabled() {
		return testModeEnabled;
	}
}
