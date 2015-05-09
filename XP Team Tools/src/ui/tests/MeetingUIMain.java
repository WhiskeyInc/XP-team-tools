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
						//TODO send event informations
//						System.out.println("Date: " + ask.getDate());
//						System.out.println("Description: " + ask.getDescrtiption());
						detailsFrame.dispose();
					}
				});
			}
		});

		frame.getContentPane().add(ui);
		frame.setVisible(true);
	}
}
