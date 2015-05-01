package client.model;

import java.util.Observable;
import java.util.Observer;
//TODO rivedere
public class ClientChatIndexManager implements Observer {

	private volatile int index = -1;
	private IClientService indexService;
	
	
	public ClientChatIndexManager(IClientService indexService) {
		super();
		this.indexService = indexService;
		this.indexService.addObserver(this);
	}



	@Override
	public void update(Observable o, Object arg) {
		index = Integer.parseInt(indexService.getAttribute()[0]);
		System.out.println(index + " " + ClientChatIndexManager.class);
	}
	
	public int getIndex() {
		return index;
	}

}
