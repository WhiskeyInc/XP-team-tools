package server.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import server.db.IDBConnection;
import server.utils.auth.Authenticate;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

/**
 * It handle the clients registering each of them, refreshing their dynamic
 * attributes
 * 
 * @author alberto
 *
 */

// No sigleton to improve performances: poi ne mettiamo tipo uno per ogni
// team...
public class ClientsManager2 {

	private IDBConnection connection;
	private Authenticate auth = new Authenticate();
	private Set<ClientConnectionDetails> clients = new HashSet<ClientConnectionDetails>();

	// NB: non treeSet, penso abbia la documentazione sbagliata, cioè nel
	// contains chiama il compareTo,
	// non l' equals

	public ClientsManager2(IDBConnection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * Register a client. If a client is already registered, this method only
	 * refresh his dynamic attributes as sockets
	 * 
	 * @param client
	 * @throws SQLException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void registerClient(ClientConnectionDetails client)
			throws NoSuchAlgorithmException, IOException, SQLException {
		if (authenticate(client.getNickname(), client.getPwd())) {

			System.out.println("Sto registrando " + client.getNickname() + " "
					+ client.getTeamName() + ClientsManager2.class);
			if (has(client)) {
				clients.remove(client);
				System.out.println("L ho rimosso " + ClientsManager2.class);
				clients.add(client);
			} else {
				clients.add(client);
			}
			
		} else {
			// TODO throw new IOException("The user " + nickname
			// + "does not exist or invalid password");
			System.err.println("Utente non autenticato!");
		}
	}
	public Set<ClientConnectionDetails> getClients() {
		return clients;
	}

	/**
	 * It returns the {@link ClientConnectionDetails} that has the same team
	 * name and the same nickname of the {@link ClientDetails} passed as
	 * parameter. If it' isn' t the match, returns null
	 * 
	 * @param {@link ClientDetails}
	 * @return
	 */
	public ClientConnectionDetails get(ClientDetails det) {
		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			ClientConnectionDetails conDet = iter.next();
			if (conDet.equals(det)) {
				return conDet;
			}
		}
		return null;
	}

	/**
	 *  Checks the permissions of the client to connect.
	 *  
	 * @param nickname
	 * @param pwd
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	
	private boolean authenticate(String nickname, String pwd)
			throws IOException, NoSuchAlgorithmException, SQLException { // TODO

		boolean autheniticated = auth.authenticate(connection.getConnection(),
				nickname, pwd);

		return autheniticated;
	}

	public int size() {
		return clients.size();
	}
	
	public boolean has(ClientConnectionDetails conDet) {
		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			if(iter.next().getNickname().equals(conDet.getNickname())) {
				return true;
			}
		}
		return false;
	}

}
