package server.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;

/**
 * 
 * A server with useful features (it stores the message and it sends them to clients)
 * 
 * @author alberto
 *
 */
public class JsonServer extends AbstractServer {

	private HashMap<String, List<Socket>> clientMap = new HashMap<String, List<Socket>>();

	private IChatStorer chatStorer;
	private Socket clientSocket;
	private IMessageRecover recover;
	private BufferedReader in;
	public static final int NUM_OF_MESSAGES = 10;
	public static final int TOTAL_MILLIS = 1000;
	private Map<String, Long> millisMap = new HashMap<String, Long>();
	private Map<String, Timer> timerMap = new HashMap<String, Timer>();
	

	public JsonServer(IChatStorer chatStorer, IMessageRecover recover) {
		super();
		this.chatStorer = chatStorer;
		this.recover = recover;
	}

	public JsonServer(IChatStorer messageStorer) {
		super();
		this.chatStorer = messageStorer;
	}

	public JsonServer() {
		super();
	}

	@Override
	public void listenClients() {

		try {

			while (true) {
				clientSocket = serverSocket.accept();
				setInStream();
				String teamName = groupByTeam();

				try {
					alignClient(teamName);
				} catch (Exception e) {

				}

				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {

		}
	}

	private String groupByTeam() throws IOException {
		String teamName = getLine(in); //TODO
		System.out.println(teamName);
		if (clientMap.containsKey(teamName)) {
			clientMap.get(teamName).add(clientSocket);
		} else {
			List<Socket> socketList = new LinkedList<Socket>();
			socketList.add(clientSocket);
			clientMap.put(teamName, socketList);
		}
		return teamName;
	}

	private void setInStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	private void alignClient(String teamName) throws Exception {
		String[] messages = recoverMessages(teamName);
		for (int i = 0; i < messages.length; i++) {
			propagateMessage(messages[i], clientSocket);
		}
	}

	private Runnable getRunnable() {
		Runnable runnable = new Runnable() {
			final BufferedReader input = in;

			@Override
			public void run() {
				while (true) {
					try {

						String line = getLine(input);
						if (line != null) {
							int request = JsonParser.getRequest(line);
							switch (request) {
							case JsonParser.CHAT:
								chatRequest(line);
								
								break;

							case JsonParser.TIMER:
								timerRequest(line);
								
								break;
							default:
								break;
							}

						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			private void timerRequest(String line) throws ParseException {
				String[] timerLines = JsonParser.parseTimerRequest(line);
				String teamName = timerLines[0];
				
				int minutes = Integer.parseInt(timerLines[1]);
				int seconds = Integer.parseInt(timerLines[2]);
				if(millisMap.containsKey(teamName)) {
					millisMap.replace(teamName, TimerFormatter.getMillis(minutes, seconds));
				} else {
					millisMap.put(teamName, TimerFormatter.getMillis(minutes, seconds));
				}
				
				startTimer(teamName);
			}

			private void chatRequest(String line) throws ParseException,
					IOException {
				String[] lines = JsonParser
						.parseChatRequest(line);
				String teamName = lines[0];
				String message = lines[1];
				System.out.println("Il team è: " + teamName);
				System.out.println(message); // TODO
				propagateMessageToTeamClients(line,
						clientMap.get(teamName));
				chatStorer.storeMessage(teamName, line);
			}

		};
		return runnable;
	}

	private String getLine(BufferedReader in) throws IOException {
		String line;
		line = in.readLine();
		return line;
	}

	private void propagateMessageToTeamClients(String message,
			List<Socket> clientSocketList) throws IOException {

		for (Socket socket : clientSocketList) {
			propagateMessage(message, socket);
		}
	}

	private void propagateMessage(String message, Socket socket)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		out.write(Formatter.appendNewLine(message));
		out.flush();
	}


	private String[] recoverMessages(String teamName) throws Exception {
		int numOfMessages = NUM_OF_MESSAGES;
		String[] sentMessages;

		if (recover.getNumOfMessages(teamName) < numOfMessages) {
			numOfMessages = recover.getNumOfMessages(teamName);
		}
		sentMessages = recover.recoverLastMessages(teamName, numOfMessages);

		return sentMessages;
	}

	private void startTimer(final String teamName) {
		
		
		
		final Timer timer = new Timer(TOTAL_MILLIS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final long totalMillis = millisMap.get(teamName);
				if(totalMillis==0) {
					timerMap.get(teamName).stop();
				}
				try {
					int [] vet = TimerFormatter.getTimeStamp(totalMillis);
					String lineTimer = JsonMaker.timerRequest(teamName, vet[0], vet[1], null); //minuti, secondi
					propagateMessageToTeamClients(lineTimer, clientMap.get(teamName));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				millisMap.replace(teamName, totalMillis - TOTAL_MILLIS);

			}
		});
		
		if(timerMap.containsKey(teamName)) {
			timerMap.replace(teamName, timer);
		} else {
			timerMap.put(teamName, timer);
		}

		timerMap.get(teamName).setInitialDelay(0);
		timerMap.get(teamName).start();
	}

}
