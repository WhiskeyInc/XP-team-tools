package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import ui.tests.FramesUtils;
import client.model.StrategyClient1_1;

public class EventCreationController implements ActionListener {

	private MeetingUIDetails ask = new MeetingUIDetails();
	private JFrame detailsFrame;
	private StrategyClient1_1 client;

	public EventCreationController(MeetingUIDetails ask,
			StrategyClient1_1 client) {
		super();
		this.ask = ask;
		this.client = client;
		detailsFrame = FramesUtils.createFrame("Insert meeting details", 450,
				330);
		detailsFrame.setLocationRelativeTo(null);
		detailsFrame.setVisible(true);
		detailsFrame.getContentPane().add(ask);
		detailsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		client.sendMessageToServer(JsonMaker.teamMembsRequest(client.getNickname(), client.getTeamName()));
		String jsonMembs = client.waitServerResponse();
		
		String[] membs;
		ArrayList<String> participants = new ArrayList<String>();
		try {
			membs = JsonParser.parseMakeTeamMembs(jsonMembs);
			for (int i = 0; i < membs.length; i++) {
				participants.add(membs[i]);
			}
			// add myself member
			participants.add(client.getNickname());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		int year = Integer.parseInt(ask.getYear()) + 2000;
		
		client.sendMessageToServer(JsonMaker.manualEventRequest("admin", 
				ask.getName(), participants, String.valueOf(year), ask.getMonth(),
				ask.getDay(), ask.getHour(), ask.getMinute()));

		System.out.println("Date: " + ask.getDay()+"/"+ask.getMonth()+"/"+ String.valueOf(year));
		System.out.println("Hour: " + ask.getHour()+":"+ask.getMinute());
		System.out.println("Name: " + ask.getName());
		detailsFrame.dispose();
	}

}
