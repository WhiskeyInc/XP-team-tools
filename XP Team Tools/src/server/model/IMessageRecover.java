package server.model;

/**
 * Interface recovering the messages of the main conversation from the server organisation
 * [still doesn't handle many conversations]
 * @author alberto
 */
public interface IMessageRecover {

	/**
	 * Recover the last messages from the main conversation
	 * @param numOfMessages number of messages to recover
	 * @return A string vector with the last messages
	 */
	public abstract String[] recoverLastMessages(int numOfMessages);
	
	/**
	 * Gives the number of messages in a conversation
	 * @return Number of messages
	 */
	public abstract int getNumOfMessages();

}