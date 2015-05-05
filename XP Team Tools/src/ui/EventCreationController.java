package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import protocol.JsonMaker;
import ui.tests.FramesUtils;
import client.model.StrategyClient1_1;

public class EventCreationController implements ActionListener{
	
	private MeetingUIDetails ask = new MeetingUIDetails();
	private JFrame detailsFrame;
	private StrategyClient1_1 client;
	
	public EventCreationController(MeetingUIDetails ask, StrategyClient1_1 client) { 
		super();
		this.ask = ask;
		this.client = client;
		detailsFrame = FramesUtils.createFrame("Insert meeting details", 500, 250);
	    detailsFrame.setLocationRelativeTo(null);
	    detailsFrame.setVisible(true);  
		detailsFrame.getContentPane().add(ask);
		detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		client.sendMessageToServer(JsonMaker.manualEventCommunication(ask.getDescrtiption(), ask.getDate()));
		
		System.out.println("Date: " + ask.getDate());
		System.out.println("Description: " + ask.getDescrtiption());
		detailsFrame.dispose();
	}

}
