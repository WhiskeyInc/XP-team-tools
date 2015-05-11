package client.model;

import java.util.Observer;
/**
 * Abstraction of services that handle list of parameters used by clients
 * @author alberto
 *
 */
public interface IListService {
	
	public abstract void setMembs(String request);

	public abstract String[] getMembs();
	
	public abstract void addObserver(Observer obs);

}