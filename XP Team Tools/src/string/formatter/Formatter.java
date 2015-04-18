package string.formatter;

public class Formatter {

	
	
	public static String SECRET_CODE = "@#1";
	/**
	 * Basic format with only the nickname
	 * @param nickname
	 * @return the formatted output
	 */
	public static String formatNickname(String nickname) {
		return "[" + nickname + "]:";
	}
	
	/**
	 * Format the message with many conversation chats
	 * @param sender
	 * @param conversation
	 * @param message
	 * @return the formatted output
	 */
	
	public static String formatMessage(String sender, String conversation,
			String message) {
		// TODO Auto-generated method stub
		return "TODO formatting";
	}
	
	/**
	 * Format the message adding at the start some particular chars that allow to distinguish it
	 * @param message
	 * @return
	 */

	public static String markMessage(String message) {
		return message + SECRET_CODE;
	}

	public static String removeSecretCode(String message) {
		return message.substring(0, message.length()-SECRET_CODE.length());
	}
	/**
	 * It appends the char '\n' to the parameter line
	 * @param line
	 * @return
	 */
	public static String appendNewLine(String line) {
		return line + "\n";
	}
	
	
}
