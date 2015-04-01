package timer.utils;

public class TimerIndexManager {
	
	private boolean timeOver;
	private boolean PauseIndex;
	private TimerManager man;
	
	public TimerIndexManager(TimerManager man) {
		this.man = man;
	}
	
	public void setPauseIndex(boolean pauseIndex) {
		PauseIndex = pauseIndex;
	}
	
	public void setTimeOver(boolean timeOver) {
		this.timeOver = timeOver;
	}
	
	public boolean isTimeOver() {
		return timeOver;
	}
	
	public boolean isPauseIndex() {
		return PauseIndex;
	}
	
	public boolean isTimeTerminated(){
		if(man.getMinute() == 0 && man.getSecond() == 0) return true;
		else return false;
	}
	
	public boolean isMinuteTerminated(){
		if(man.getSecond() == 0) return true;
		else return false;
	}
	
	

}
