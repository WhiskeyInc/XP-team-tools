package client.model;

import java.util.Observable;


/**
 *  Abtraction for generic client' s service
 * @author alberto
 *
 */

public interface IClientService {

	public abstract void setAttribute(String request);

	public abstract Observable getAttribute(int index);
	

}