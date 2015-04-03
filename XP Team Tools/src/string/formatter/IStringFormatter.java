package string.formatter;

/**
 * Interface for the string formatting of the messages sent from the client to the server
 * and the other way around
 * @author nicola
 */
public interface IStringFormatter {

	/**
	 * Basic format with only the nickname (test only)
	 * @param nickname
	 * @return the formatted output
	 */
	public String formatMessage(String nickname);
	
	/**
	 * Format of the message with many conversation chats
	 * @param sender
	 * @param conversation
	 * @param message
	 * @return the formatted output
	 */
	public String formatMessage(String sender, String conversation, String message);
	
}
