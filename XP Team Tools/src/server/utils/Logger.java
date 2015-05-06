package server.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import server.model.CacheMap;
import string.formatter.Formatter;

/**
 * The class writes out a file of all the conversations for each team; It
 * rewrites them every time. It's needed as a demo for a future implementation
 * of a DB storing (TODO). It is expected in future to flush the mapMessageList buffer in
 * {@link CacheMap} after every writing, as it has obviously to be (TODO).
 * It is expected to add the time stamp for every message (TODO);
 * 
 * @author koelio
 *
 */

public class Logger implements ILogger {

	private Map<String, ArrayList<String>> mapMessageList;

	/**
	 * Writes messages onto the database.
	 * Need to be improved as not to rewrite, but append new messages. TODO
	 */
	public void writeDatabase() {
		try {

			Set<String> keys = mapMessageList.keySet();

			for (String key : keys) {

				FileWriter writer = new FileWriter(new File(
						"database/" + key + ".txt"));

				ArrayList<String> value = mapMessageList.get(key);

				for (int i = 0; i < value.size(); i++) {
					writer.write(Formatter.formatMessage(value.get(i)) + "\n");
				}

				writer.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMap(Map<String, ArrayList<String>> m) {
		this.mapMessageList = m;
	}
}
