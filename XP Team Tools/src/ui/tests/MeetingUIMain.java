package ui.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.MeetingUIButton;
import ui.MeetingUIDetails;

public class MeetingUIMain {
	public static void main(String[] args) {

		final JFrame frame = FramesUtils.createFrame("MeetingUI", 450, 450);

		final MeetingUIButton ui = new MeetingUIButton();

		ui.setButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				final JFrame detailsFrame = FramesUtils.createFrame("Insert meeting details", 450, 330);
			    detailsFrame.setLocationRelativeTo(null);
			    detailsFrame.setVisible(true);  
			    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				final MeetingUIDetails ask = new MeetingUIDetails();
				detailsFrame.getContentPane().add(ask);
				
				ask.setCreateButtonListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						System.out.println("Date: " + ask.getDay()+"/"+ask.getMonth()+"/"+ask.getYear());
						System.out.println("Hour: " + ask.getHour()+":"+ask.getMinute());
						System.out.println("Name: " + ask.getName());
						detailsFrame.dispose();
					}
				});
			}
		});

		frame.getContentPane().add(ui);
		frame.setVisible(true);
	}
}
