package server.model;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Set;

import client.model.ClientConnectionDetails;
import client.model.ClientDetails;
/**
 * It handle the clients registering each of them, refreshing their dynamic attributes
 * @author alberto
 *
 */

//No sigleton to improve performances: poi ne mettiamo tipo uno per ogni team...
public class ClientsManager2O {
	
	private Set<ClientConnectionDetails> clients = new HashSet<ClientConnectionDetails>();
	// NB: non treeSet, penso abbia la documentazione sbagliata, cioè nel contains chiama il compareTo,
	// non l' equals
	/**
	 * Register a client. If a client is already registed, this method only refresh his 
	 * dynamic attributes as sockets
	 * @param client
	 */
	public void registerClient(ClientConnectionDetails client) {
		System.out.println("Sto registrando "+ client.getNickname() +" " +  client.getTeamName() + ClientsManager2O.class);
		if(clients.contains(client)) {
			clients.remove(client);
			System.out.println("L ho rimosso " +  ClientsManager2O.class);
			clients.add(client);
		} else {
			clients.add(client);
		}
	}
	/**
	 * It returns the {@link ClientConnectionDetails} that has the same team name
	 * and the same nickname of the {@link ClientDetails} passed as parameter.
	 * If it' isn' t the match, returns null
	 * @param  {@link ClientDetails}}
	 * @return
	 */
	public ClientConnectionDetails get(ClientDetails det) {
		Iterator<ClientConnectionDetails> iter = clients.iterator();
		while (iter.hasNext()) {
			ClientConnectionDetails conDet = iter.next();
			if(conDet.equals(det)) {
				return conDet;
			}
		}
		return null;
	}
	
	public int size() {
		return clients.size();
	}
	

}
