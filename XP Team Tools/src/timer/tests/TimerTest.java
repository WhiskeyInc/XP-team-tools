package timer.tests;

import timer.TimerModel;

public class TimerTest {
	
	public static void main(String[] args) {
		
		TimerModel tim = new TimerModel();
		
		tim.getTimer().schedule(tim.getTask(), 5000);
	}

}
