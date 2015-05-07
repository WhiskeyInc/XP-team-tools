package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import protocol.JsonMaker;
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
		detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] membs = client.getMembsService().getMembs();
		ArrayList<String> participants = new ArrayList<String>();
		for (int i = 0; i < membs.length; i++) {
			participants.add(membs[i]);
		}

		client.sendMessageToServer(JsonMaker.manualEventRequest(
				ask.getName(), participants, ask.getYear(), ask.getMonth(),
				ask.getDay(), ask.getHour(), ask.getMinute()));

		System.out.println("Date: " + ask.getDay()+"/"+ask.getMonth()+"/"+ask.getYear());
		System.out.println("Hour: " + ask.getHour()+":"+ask.getMinute());
		System.out.println("Name: " + ask.getName());
		detailsFrame.dispose();
	}

}
