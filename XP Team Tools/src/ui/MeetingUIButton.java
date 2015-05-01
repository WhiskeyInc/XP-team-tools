package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * UI of the function to schedule a new meeting
 * @author Nicola
 */
public class MeetingUIButton extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JGradientButton meetingButton = new JGradientButton("+ Schedule a Meeting");
	
	public MeetingUIButton() {
		super();
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints lim = new GridBagConstraints();
		super.setLayout(layout);
		
		setMeetingButtonConstraints(lim);
		Dimension dim = new Dimension(200, 20);
		setMeetingButtonProperties(dim);
		super.add(meetingButton, lim);
	}
	
	private void setMeetingButtonConstraints(GridBagConstraints lim) {
		lim.gridx = 1;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
	}
	private void setMeetingButtonProperties(Dimension dim) {
		meetingButton.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
		meetingButton.setMinimumSize(dim);
		meetingButton.setPreferredSize(dim);
		meetingButton.setColor(Color.YELLOW);
	}
	
	
	public void setButtonAction(ActionListener actionListener) {
		meetingButton.addActionListener(actionListener);
	}
	
	
}
