package string.formatter;

import java.util.StringTokenizer;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;

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
	 * Purges the raw message from delimitation marks []: useful for DB storing
	 * in case of timer event it returns a "Pomodoro!" (to be implemented with
	 * json parsing TIMER )
	 * 
	 * @param rawmessage
	 * @return the formatted output
	 */

	public static String formatMessage(String rawmessage) {

		try {
			int request = JsonParser.getRequest(rawmessage);
			switch (request) {
			case JsonParser.CHAT:

				String[] lines = JsonParser.parseChatRequest(rawmessage);
				String message = lines[1];
			    StringTokenizer tok = new StringTokenizer(message, "[]:");
				String sender = tok.nextToken();
				String msg;
				if (tok.hasMoreTokens()) {
					msg = tok.nextToken();
				} else {
					msg = ""; // case of no message, TODO
				}

				String formattedMessage = sender + " " + msg;
				// System.out.println(formattedMessage); //testing purposes
				return formattedMessage;

			default:
				break;

			}

		} catch (ParseException e) {

			System.err
					.println("Errore in Formatter, formatMessage pars exeption");
		}
		
		return "Pomordoro!";// to be handled in case of timer parse case
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
