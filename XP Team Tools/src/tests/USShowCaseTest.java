package tests;

import static org.junit.Assert.*;

import model.USShowCase;

import org.junit.Test;

public class USShowCaseTest {

	USShowCase ussc = new USShowCase();

	@Test
	public void addNewUsTest() {
		ussc.addNewUs("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals("us1"
				+ "Voglio che ci sia un pannello con dei tasti che...", ussc
				.getUslist().get(0) + ussc.getDescription("us1"));
	}

	@Test
	public void defaultstatusTest() {
		ussc.addNewUs("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(false, ussc.getStatus("us1"));
	}

	@Test
	public void setStatusTest() {
		ussc.addNewUs("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		ussc.setStatus("us1", true);
		assertEquals(true, ussc.getStatus("us1"));
	}

	@Test
	public void usSwitchTest() {
		ussc.addNewUs("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		ussc.addNewUs("us2", "Voglio che ci sia un menù che...");
		ussc.addNewUs("us3", "Ci deve essere un'area di testo dove poter...");
		ussc.usswitch("us3", 0);
		assertEquals("us3"+"us1"+"us2",ussc.getUslist().get(0)+ussc.getUslist().get(1)+ussc.getUslist().get(2));
	}
	
	@Test
	public void usBoardTest(){
		ussc.addNewUs("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		ussc.addNewUs("us2", "Voglio che ci sia un menù che...");
		ussc.getBoard("us1").addNewTask("Timeline", "Componente che deve...");
		assertEquals(1+0,ussc.getBoard("us1").getTasksNumber()+ussc.getBoard("us2").getTasksNumber());
	}

}
