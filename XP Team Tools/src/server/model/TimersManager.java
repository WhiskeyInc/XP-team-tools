package server.model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

/**
 *  The TimersManager is a singleton manager of timers. This class has a useful map of all the 
 *  timers (thanks to which, it's possible to handle all the timers) 
 * 
 * @author Alberto
 *
 */

public class TimersManager {
	
	private Map<Integer, Timer> timerMap = new HashMap<Integer, Timer>();
	private Map<Integer, Long> millisMap = new HashMap<Integer, Long>();
	

	private static TimersManager instance = new TimersManager();

	
	public boolean hasTimerKey(Integer key) {
		return timerMap.containsKey(key);
	}
	public Timer getTimer(Integer key) {
		return timerMap.get(key);
	}
	public void putTimer(Integer key, Timer value) {
		timerMap.put(key, value);
	}
	
	public void putMillis(Integer key, long value) {
		millisMap.put(key, value);
	}
	public Long getMillis(Integer key) {
		return millisMap.get(key);
	}
	
	public void replaceTimer(int key, Timer value) {
		timerMap.replace(key, value);
	}
	
	public void replaceMillis(int key, long value) {
		millisMap.replace(key, value);
	}
	
	public boolean hasMillisKey(Integer key) {
		return millisMap.containsKey(key);
	}
	private TimersManager() {
	}

	public static TimersManager getInstance() {
		return instance;
	}
	


}
