package stopwatch;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
	static GUI currentGUI;
	static Time totalTimer;
	static Time lapTimer;
//	static int globalMS = 0;
//	static int globalS = 0;
//	static int globalM = 0;
//	static int globalH = 0;
	static int lapNo = 1;
	static boolean isEnabled = false;

	public static void startUp(GUI currentGUI) {
		Controller.currentGUI = currentGUI;
		Timer timer = new Timer();
		timer.schedule(new IncrementTime(), 0, 10);
		totalTimer = new Time();
		lapTimer = new Time();
	}

	public static void updateLabels() {
		currentGUI.setTotalText(totalTimer.getTime());
		currentGUI.setLapText(lapTimer.getTime());
	}
	
	public static void resetAll(){
		totalTimer.resetTime();
		lapTimer.resetTime();
		lapNo = 1;
	}
	
	public static Time getTotalTimer(){
		return totalTimer;
	}
	
	public static Time getLapTimer(){
		return lapTimer;
	}
}

class IncrementTime extends TimerTask {
	public void run() {
		if (!Controller.isEnabled) {
			return;
		}
		Controller.getTotalTimer().incrementMilisecond();
		Controller.getLapTimer().incrementMilisecond();
		Controller.updateLabels();
		
	}
}
