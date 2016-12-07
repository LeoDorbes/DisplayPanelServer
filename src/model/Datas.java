package model;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Timer;

import view.MainFrame;
import controller.packets.OutputThread;

public class Datas {

	public final static String separator = "%";

	// Connexion datas :
	// @todo : To load from config file, later on.
	private InetAddress inAddress;
	private int port;
	private boolean serverOn;
	private ArrayList<OutputThread> outputThreads;

	// Match datas :
	private Team homeTeam;
	private Team guestTeam;
	
	//Match time datas :
	private boolean gameOn;
	private boolean gamePaused;
	private long previousTime;
	private Chronometer chronometer;
	private long matchTime;
	private boolean countDownward;

	// View datas :
	private MainFrame mainFrame;

	public Datas() {
		outputThreads = new ArrayList<OutputThread>();
		port = 7789;
		this.serverOn = true;
		this.gameOn = false;
		previousTime = 0;
	}

	public synchronized void broadcastCmd(String cmd) {
		for (OutputThread outputThread : outputThreads) {
			outputThread.getOutputQueue().add(cmd);
		}
	}

	public synchronized void broadcastCmdExceptedIndex(String cmd, int index) {

		int i = 0;
		for (OutputThread outputThread : outputThreads) {
			if (i != index)
				outputThread.getOutputQueue().add(cmd);
			i++;
		}
	}

	public void removeOutputThreadFromList(int index) {
		outputThreads.remove(index);
		int i = 0;
		for (OutputThread outputThread : outputThreads) {

			if (i >= index)
				outputThread.setIndex(outputThread.getIndex() - 1);
			i++;
		}

	}

	public String getCurrentSportName() {

		// This is NOT scalable, will be ok for the current work but should be
		// re-thinked in priority in a bigger environement :

		String t = null;
		if (homeTeam != null) {
			if (homeTeam.getClass() == RugbyTeam.class) {
				t = "Rugby";
			} else if (homeTeam.getClass() == SoccerTeam.class) {
				t = "Football";
			} else if (homeTeam.getClass() == BasketballTeam.class) {
				t = "Basketball";
			}
		}
		return t;
	}

	public boolean isServerOn() {
		return serverOn;
	}

	public void setServerOn(boolean serverOn) {
		this.serverOn = serverOn;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}

	public InetAddress getInAddress() {
		return inAddress;
	}

	public void setInAddress(InetAddress inAddress) {
		this.inAddress = inAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Getter for the gameOn boolean.
	 * 
	 * @return boolean gameOn
	 */
	public boolean isGameOn() {
		return gameOn;
	}

	/**
	 * Setter for the gameOn boolean.
	 * 
	 * @return boolean gameOn
	 */
	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}

	public ArrayList<OutputThread> getOutputThreads() {
		return outputThreads;
	}

	public void setOutputThreads(ArrayList<OutputThread> outputThreads) {
		this.outputThreads = outputThreads;
	}
	
	public Chronometer getChronometer() {
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public long getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(long previousTime) {
		this.previousTime = previousTime;
	}

	public long getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(long matchTime) {
		this.matchTime = matchTime;
	}

	public boolean isCountDownward() {
		return countDownward;
	}

	public void setCountDownward(boolean countDownward) {
		this.countDownward = countDownward;
	}
	
	
}
