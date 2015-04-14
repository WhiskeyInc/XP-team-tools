package server.model;

/**
 * Interface recovering the messages of the main conversation from the server organisation
 * [still doesn't handle many conversations]
 * @author alberto
 */
public interface IMessageRecover {

	/**
	 * Recover the last messages from the main conversation
	 * @param teamName TODO
	 * @param numOfMessages number of messages to recover
	 * @return A string vector with the last messages
	 * @throws NoMessagesException TODO
	 */
	public abstract String[] recoverLastMessages(String teamName, int numOfMessages) throws NoMessagesException;
	
	/**
	 * Gives the number of messages in a conversation
	 * @param teamName TODO
	 * @return Number of messages
	 * @throws NoMessagesException TODO
	 */
	public abstract int getNumOfMessages(String teamName) throws NoMessagesException;

}