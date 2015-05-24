package string.formatter;

import java.util.StringTokenizer;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;

/**
 * This class allows to format different String that will be sent to the server
 * 
 * @author pavlo
 *
 */
public class Formatter {

	/**
	 * Basic format with only the nickname
	 * 
	 * @param nickname
	 * @return the formatted output
	 */
	public static String formatNickname(String nickname) {
		return "[" + nickname + "]:";
	}

	/**
	 * It strips the []: chars from the nickname;
	 * 
	 * @param message
	 * @return A String array with the stripped author and the message
	 */
	public static String[] deFormatMessage(String message) {

		StringTokenizer tok;
		String msg;

		tok = new StringTokenizer(message, "[]:");

		String sender = tok.nextToken();
		if (tok.hasMoreTokens()) {
			msg = tok.nextToken();
		} else {
			msg = ""; // case of no message
		}

		String[] result = { sender, msg };

		return result;
	}

	/**
	 * Purges the raw message from delimitation marks []: useful for DB storing
	 * in case of timer event it returns a "Pomodoro!"
	 * 
	 * 
	 * @param rawmessage
	 * @return the formatted output
	 */

	public static String formatMessage(String rawmessage) {

		// System.out.println(rawmessage);

		try {

			int request = JsonParser.getRequest(rawmessage);

			String[] lines;
			String message, formattedMessage;

			switch (request) {
			case JsonParser.CHAT:

				lines = JsonParser.parseChatRequest(rawmessage);
				message = lines[1];

				formattedMessage = deFormatMessage(message)[0] + " "
						+ deFormatMessage(message)[1];
				System.out.println("da formatter " + formattedMessage); // testing
																		// purposes
				return formattedMessage;

			default:
				break;

			}

		} catch (ParseException e) {

			System.err
					.println("Errore in Formatter, formatMessage Parse Exception");
		}

		return "Ne chat ne pomodoro";// to be handled in case of timer parse
										// case

	}

	/**
	 * It appends the char '\n' to the parameter line
	 * 
	 * @param line
	 * @return
	 */
	public static String appendNewLine(String line) {
		return line + "\n";
	}

}
