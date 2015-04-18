package timer.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import timer.TimerModel;

public class TimerModelTest {

	@Test
	public void test() {
		TimerModel model = new TimerModel(3000);
		model.startTimer();
	}

}
