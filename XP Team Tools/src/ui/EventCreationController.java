package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.tests.FramesUtils;

public class EventCreationController implements ActionListener{
	
	private MeetingUIDetails ask = new MeetingUIDetails();
	private JFrame detailsFrame;
	
	public EventCreationController(MeetingUIDetails ask) {
		super();
		this.ask = ask;
		detailsFrame = FramesUtils.createFrame("Insert meeting details", 500, 250);
	    detailsFrame.setLocationRelativeTo(null);
	    detailsFrame.setVisible(true);  
		detailsFrame.getContentPane().add(ask);
		detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//TODO send event informations
		
		System.out.println("Date: " + ask.getDate());
		System.out.println("Description: " + ask.getDescrtiption());
		detailsFrame.dispose();
	}

}
