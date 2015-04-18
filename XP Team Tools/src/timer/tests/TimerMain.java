package timer.tests;

import javax.swing.JFrame;

import timer.TimerModel;

public class TimerMain {
	
	public static void main(String[] args) {
		
		TimerModel model = new TimerModel(3000);
		model.startTimer("");
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
	}

}
