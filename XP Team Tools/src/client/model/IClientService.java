package client.model;

import java.util.Observer;

/**
 *  Abtraction for generic client' s service
 * @author alberto
 *
 */

public interface IClientService {

	public abstract void setAttribute(String attribute);

	public abstract String getAttribute();
	
	public abstract void addObserver(Observer observer);

}