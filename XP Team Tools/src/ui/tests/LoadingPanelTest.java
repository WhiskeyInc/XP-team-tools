package ui.tests;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import ui.LoadingPanel;

public class LoadingPanelTest {
	
	public static void main(String[] args) {
		
		final JFrame frame = FramesUtils.createFrame("Loading Test", 500, 500);
		final LoadingPanel lp = new LoadingPanel("Loading");
		final JPanel panel = new JPanel();
		final JPanel panel2 = new JPanel();
		JLabel area = new JLabel("This is the panel before loading operation");
		panel2.add(area);
		panel.setBackground(Color.WHITE);
		panel.add(lp);
		frame.getContentPane().add(panel2);
		
		Timer timer1 = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lp.getLabelText().contains("...")){
					lp.setLabelText("Loading");
				}
				lp.setLabelText(lp.getLabelText()+".");
				frame.getContentPane().add(panel);
				frame.setVisible(true);
			}
		});
		//for testing, after 3 seconds, a loading operation is required
		timer1.setInitialDelay(3000);
		
		timer1.start();
		
		frame.setVisible(true);
	}

}
