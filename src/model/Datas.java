package model;

import java.net.InetAddress;
import java.net.ServerSocket;

public class Datas {

	// Connexion datas :
	// @todo : To load from config file, later on.
	private InetAddress inAddress;
	private int port;
	private boolean serverOn;
	
	// Match datas :
	private Team homeTeam;
	private Team guestTeam;
	private boolean gameOn;
	

	public boolean isServerOn() {
		return serverOn;
	}

	public void setServerOn(boolean serverOn) {
		this.serverOn = serverOn;
	}

	public Datas() {
		port = 7789;
		this.serverOn = true;
		this.gameOn = false;
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

	public boolean isGameOn() {
		return gameOn;
	}

	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}
	

}
