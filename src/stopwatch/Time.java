package stopwatch;

public class Time {
	private int milisecond = 0;
	private int second = 0;
	private int minute = 0;
	private int hour = 0;

	public void incrementMilisecond() {
		milisecond += 1;
		formatTime();
	}

	public void formatTime() {
		if (milisecond >= 100) {
			milisecond -= 100;
			second += 1;
		}

		if (second >= 60) {
			second -= 60;
			minute += 1;
		}

		if (minute >= 60) {
			minute -= 60;
			hour += 1;
		}
	}

	public String getTime() {
		String currentTime = "";
		currentTime = String.valueOf(milisecond);
		if (currentTime.length() == 1) {
			currentTime = "0" + currentTime;
		}
		
		String formattedSeconds = String.valueOf(second);
		if (formattedSeconds.length() == 1) {
			formattedSeconds = "0" + formattedSeconds;
		}
		currentTime = formattedSeconds + "." + currentTime;
		
		String formattedMinutes = String.valueOf(minute);
		if (formattedMinutes.length() == 1) {
			formattedMinutes = "0" + formattedMinutes;
		}
		currentTime = formattedMinutes + ":" + currentTime;
		
		if (hour > 0) {
			String formattedHours = String.valueOf(hour);
			currentTime = formattedHours + ":" + currentTime;
		}
		
		return currentTime;
	}

	public void resetTime() {
		milisecond = 0;
		second = 0;
		minute = 0;
		hour = 0;
	}
}
