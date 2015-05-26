package server.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import string.formatter.Formatter;
import client.model.ClientDetails;

/**
 * The class writes out a file of all the conversations for each team; It
 * rewrites them every time. It's needed as a demo for a future implementation
 * of a DB storing (TODO). It is expected to add the time stamp for every
 * message (TODO), the messages are written in a database (text file)
 * 
 * @author koelio
 *
 */

public class Logger implements ILogger {

	private PrintWriter writer;
	private ArrayList<String> participants;
	private String path = "database/";
	private String fileType = ".txt";
	
	public Logger() {
	}

	public Logger(String path) {
		this.path = path;
	}
	
	
	@Override
	public void writeDatabase(ArrayList<ClientDetails> attendantsDetails,
			String m) {

		participants = new ArrayList<String>();

		for (ClientDetails details : attendantsDetails) {

			participants.add(details.getNickname());

		}

		String filename = participants.toString();

		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(
					path + filename + fileType, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m = Formatter.formatMessage(m);
		writer.println("\n" + m);

		writer.close();

	}

}
