package client.model.service;

import java.util.Observable;


/**
 *  Abstraction for generic client' s service, this interface has two methods that allows to
 *  sets the attribute (i.e. a generic service) and afterwards get it in form of an Observable object
 *  
 * @author alberto
 *
 */

public interface IClientService {

	/**
	 * Sets the attribute (a generic service) from a String request
	 * @param request
	 */
	public abstract void setAttribute(String request);

	/**
	 * Gets the attribute in form of an Observable object
	 * @param index
	 * @return
	 */
	public abstract Observable getAttribute(int index);
	

}