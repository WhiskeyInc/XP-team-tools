package server.model;


public interface IMessageRecover {

	public abstract String[] recoverLastMessages(int numOfMessages);
	
	public abstract int getNumOfMessages();

}