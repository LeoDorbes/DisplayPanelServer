package model;

public class Chronometer implements Runnable {

	private boolean stopped;
	private boolean finished;
	private boolean wasPaused;
	private boolean countDownward;
	private long previousTimeToCount; // If was paused
	private long timeToCount;

	private long startTime;
	private Datas datas;
	private int frameRate = 30;

	public Chronometer(Datas datas, long timeToCount, boolean countDownward) {

		this.timeToCount = timeToCount;
		this.datas = datas;

		this.startTime = System.currentTimeMillis();
		this.wasPaused = false;
		this.stopped = false;
		this.finished = false;
		this.countDownward = countDownward;
	}

	public Chronometer(Datas datas, long timeToCount, long previousTimeToCount, boolean countDownward) {
		this(datas, timeToCount, countDownward);

		this.wasPaused = true;
		
		this.previousTimeToCount = previousTimeToCount;
	}

	@Override
	public void run() {
		long currentTime;
		startTime = System.currentTimeMillis();
		while (!this.stopped && !this.finished) {

			currentTime = System.currentTimeMillis();

			long display;
			long duration;
			if (wasPaused)
				duration = previousTimeToCount;
			else
				duration = 0;

			if (countDownward) {
				duration = duration + timeToCount;
				display = duration - (currentTime - startTime) - previousTimeToCount;
				if (display < 0) {
					this.finished = true;
					display = 0;
				}

			} else {
				display = (currentTime - startTime) + duration;
				if (display > duration + timeToCount) {
					this.finished = true;
					display = duration + timeToCount;
				}
			}

			// Update the view:
			datas.getMainFrame().updateChronometer(display);

			try {
				Thread.sleep(1000 / frameRate);
			} catch (InterruptedException e) {
				System.out.println("Chronometre interrompu");
				e.printStackTrace();
			}

		}
		
		//@Todo : Handle the work here

	}

	public long stopChronometer() {
		this.stopped = true;

		long duration;
		if (wasPaused)
			duration = previousTimeToCount;
		else
			duration = 0;

		duration = (System.currentTimeMillis() - this.startTime)+duration;
		if (duration < 0)
			duration = 0;

		return duration;

	}

}
