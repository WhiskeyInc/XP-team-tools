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

	public abstract void setAttribute(String request);

	public abstract Observable getAttribute(int index);
	

}