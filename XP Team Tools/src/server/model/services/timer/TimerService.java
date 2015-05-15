package server.model.services.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.events.IEventActionRequest;
import server.model.propagator.MessagePropagator;
import server.model.recover.Chat;
import server.model.services.IService;
import server.model.services.chat.ChatsManager;
import timer.TimerFormatter;
import client.model.ClientDetails;

/**
 * This class makes the Timer Service concrete, by overriding the abstract method doAction in @IService:
 * it starts the timer making a division by groups (the timer is synchronized only with members of the 
 * same team)
 * 
 * 
 * @author Alberto, Alessandro
 *
 */
public class TimerService implements IService {

	private ChatsManager chatsManager;
	private TimersManager timersManager;
	private MessagePropagator messagePropagator;
	private IEventActionRequest eventSender;

	public static final int TOTAL_MILLIS = 1000;
	
	public TimerService(ChatsManager chatsManager,
			TimersManager timersManager, MessagePropagator messagePropagator, IEventActionRequest eventSender) {
		super();
		this.chatsManager = chatsManager;
		this.timersManager = timersManager;
		this.messagePropagator = messagePropagator;
		this.eventSender = eventSender;
	}

	@Override
	public void doAction(String request) throws IOException,
			ParseException {

		String[] timerLines = JsonParser.parseTimerRequest(request);
		String indexString = timerLines[0];
		int index = Integer.parseInt(indexString);
		int minutes = Integer.parseInt(timerLines[1]);
		int seconds = Integer.parseInt(timerLines[2]);
		
		ArrayList<String> participants = new ArrayList<String>();
		for (int i = 3; i < timerLines.length; i++) {
			participants.add(timerLines[i]);
		}
		
		if (timersManager.hasMillisKey(index)) {
			timersManager.replaceMillis(index,
					TimerFormatter.getMillis(minutes, seconds));
		} else {
			timersManager.putMillis(index, TimerFormatter.getMillis(minutes, seconds));
		}

		startTimer(index,participants);
	}

	private void startTimer(final int index, final ArrayList<String> participants) {

		final Timer timer = new Timer(TOTAL_MILLIS, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final long totalMillis = timersManager.getMillis(index);

				if (totalMillis == 0 && timersManager.hasTimerKey(index)) {
					timersManager.getTimer(index).stop();
					
					eventSender.sendAutomaticEventAction("admin", "Finished Tomato", participants);
				}

				int[] vet = TimerFormatter.getTimeStamp(totalMillis);
				String lineTimer = JsonMaker.timerRequest(String.valueOf(index), vet[0],
						vet[1], participants); // minuti, secondi

				Chat chat = chatsManager.get(index);
				ArrayList<ClientDetails> list = chat.getAttendantsDetails();
				for (ClientDetails details : list) {
					try {
						messagePropagator.propagateMessage(lineTimer, details);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				timersManager.replaceMillis(index, totalMillis - TOTAL_MILLIS);

			}
		});

		if (timersManager.hasTimerKey(index)) {
			timersManager.replaceTimer(index, timer);
		} else {
			timersManager.putTimer(index, timer);
		}

		timersManager.getTimer(index).setInitialDelay(0);
		timersManager.getTimer(index).start();
	}
	
}
