package server.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import org.json.simple.parser.ParseException;

import timer.TimerFormatter;
import client.model.JsonMaker;

public class TimerServiceAuth implements IService {

	private ClientsManagerAuth clientManager;

	public static final int TOTAL_MILLIS = 1000;

	private Map<String, Timer> timerMap = new HashMap<String, Timer>();
	private Map<String, Long> millisMap = new HashMap<String, Long>();

	public TimerServiceAuth(ClientsManagerAuth clientManager) {
		super();
		this.clientManager = clientManager;
	}

	
	@Override
	public void doAction(String line) throws IOException,
			ParseException {

		String[] timerLines = JsonParser.parseTimerRequest(line);
		String teamName = timerLines[0];

		int minutes = Integer.parseInt(timerLines[1]);
		int seconds = Integer.parseInt(timerLines[2]);
		if (millisMap.containsKey(teamName)) {
			millisMap.replace(teamName,
					TimerFormatter.getMillis(minutes, seconds));
		} else {
			millisMap.put(teamName, TimerFormatter.getMillis(minutes, seconds));
		}

		startTimer(teamName);
	}

	/**
	 * It starts the timer making a division by groups (the timer is
	 * synchronized only with members of the same team)
	 */
	private void startTimer(final String teamName) {

		final Timer timer = new Timer(TOTAL_MILLIS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final long totalMillis = millisMap.get(teamName);

				if (totalMillis == 0) {
					timerMap.get(teamName).stop();
				}

				int[] vet = TimerFormatter.getTimeStamp(totalMillis);
				String lineTimer = JsonMaker.timerRequest(teamName, vet[0],
						vet[1]); // minuti, secondi

				try {
					clientManager.propagateMessageToTeamClients(lineTimer,
							teamName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				millisMap.replace(teamName, totalMillis - TOTAL_MILLIS);

			}
		});

		if (timerMap.containsKey(teamName)) {
			timerMap.replace(teamName, timer);
		} else {
			timerMap.put(teamName, timer);
		}

		timerMap.get(teamName).setInitialDelay(0);
		timerMap.get(teamName).start();
	}

}
