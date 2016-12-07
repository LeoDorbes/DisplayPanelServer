package controller.actions;

import java.util.Timer;

import model.BasketballTeam;
import model.Chronometer;
import model.Datas;
import model.RugbyTeam;
import model.SoccerTeam;

/**
 * GameActions contains all the controller methods related to the network of the
 * program. They are all declared as static, because they receive their datas in
 * parameters and synchronized, because two threads should never access them in
 * parallel.
 * 
 * @author Léo Dorbes
 * @version 1.0 01 Dec, 2016.
 */
public class GameActions {

	/**
	 * This method is called when a new game is started.
	 * 
	 * @param cmd
	 *            Value of the command received by the server
	 * @param datas
	 *            accessor to the datas from the server
	 */
	public static synchronized void newGame(String cmd, Datas datas, int index) {
		// If new game :
		if (!datas.isGameOn()) {
			boolean error = false;
			String[] values;
			values = cmd.split(Datas.separator);
			if (values.length != 5) {
				error = true;
				System.out.println("Error : Wrong size");
			} else {

				String homeTeamName = values[2];
				String guestTeamName = values[3];
				String timeString = values[4];
				long timeVal = 0;
				try {
					timeVal = Long.parseLong(timeString);
					datas.setMatchTime(timeVal);
				} catch (NumberFormatException e) {
					System.out.println("Error : Element not a timer");
					error = true;
				}

				if (homeTeamName.equalsIgnoreCase(guestTeamName)) {
					System.out.println("Error : Both team are the same");
					error = true;

				} else {
					if (values[1].equalsIgnoreCase("football")) {
						datas.setHomeTeam(new SoccerTeam(homeTeamName));
						datas.setGuestTeam(new SoccerTeam(guestTeamName));
						datas.setCountDownward(false);
					} else if (values[1].equalsIgnoreCase("rugby")) {
						datas.setHomeTeam(new RugbyTeam(homeTeamName));
						datas.setGuestTeam(new RugbyTeam(guestTeamName));
						datas.setCountDownward(false);
					} else if (values[1].equalsIgnoreCase("basketball")) {
						datas.setHomeTeam(new BasketballTeam(homeTeamName));
						datas.setGuestTeam(new BasketballTeam(guestTeamName));
						datas.setCountDownward(true);
					} else {
						System.out.println("Error : Sport not found");
						error = true;
					}
				}
				if (error == false) {

					datas.setChronometer(new Chronometer(datas, datas.getMatchTime()*1000, datas.isCountDownward()));
					Thread t = new Thread(datas.getChronometer());
					t.start();
					
					datas.setGameOn(true);
					datas.setGamePaused(false);

					datas.broadcastCmd(buildTeamCommand(datas));

				}
			}
		} else {
			// If we receive a command from a client that is new.
			datas.getOutputThreads().get(index).getOutputQueue().add("Game is on");
		}
	}

	/**
	 * This method is called when a game is finished.
	 * 
	 * @param datas
	 *            accessor to the datas from the server
	 */
	public static synchronized void endGame(Datas datas) {

		// @Todo : Ajouter la sauvegarde de la partie
		if (datas.isGameOn()) {

			datas.setHomeTeam(null);
			datas.setGuestTeam(null);
			datas.setGameOn(false);
			datas.setGamePaused(false);
			datas.getChronometer().stopChronometer();
			datas.setChronometer(null);
			

			datas.broadcastCmd("Game Finished");
			System.out.println("partie terminée @TODO");
		} else {

			// Theorically impossible
			System.out.println("Critical Error : Finished game was ended too early");
		}

	}

	/**
	 * This method is called when a game is canceled.
	 * 
	 * @param datas
	 *            accessor to the datas from the server
	 */
	public static synchronized void cancelGame(Datas datas, int index) {

		if (datas.isGameOn()) {

			datas.setHomeTeam(null);
			datas.setGuestTeam(null);
			datas.setGameOn(false);
			datas.setGamePaused(false);
			datas.getChronometer().stopChronometer();
			datas.setChronometer(null);
			
			
			datas.broadcastCmd("Game Canceled");
			System.out.println("partie annulée @TODO");
		} else {
			System.out.println("Aucune partie en cours @TODO");
			// @TODO : Informer le client d'une deconnexion???
		}
	}

	/**
	 * This method is called when a new client needs to synchronize with the
	 * server and get the current ongoing game.
	 * 
	 * @param datas
	 *            accessor to the datas from the server
	 * @param index
	 *            reference of the outputThread wich should answer
	 */
	public static synchronized void sendGame(Datas datas, int index) {

		datas.getOutputThreads().get(index).getOutputQueue().add(GameActions.buildTeamCommand(datas));
	}

	/**
	 * This method builds a teamCommand to be sent to the client(s).
	 * 
	 * @param datas
	 *            accessor to the datas from the server
	 * 
	 * @return The string representing the current game
	 */
	public static synchronized String buildTeamCommand(Datas datas) {
		String t = "";
		String s = "Current Game" + Datas.separator;

		t = datas.getCurrentSportName();

		s = s + t + Datas.separator;
		s = s + datas.getHomeTeam().getName() + Datas.separator;
		s = s + datas.getGuestTeam().getName();
		return s;
	}

	public static synchronized void pauseGame(Datas datas) {
		// Might need to inform the client later, for now it's not necessary
		// because the client doesn't handle Time
		datas.setGamePaused(!datas.isGamePaused());
		if (datas.isGamePaused()){
			System.out.println("Game paused");
			datas.setPreviousTime(datas.getChronometer().stopChronometer());
		}else{
			
			System.out.println("Match time : "+datas.getMatchTime());
			System.out.println("Previous time : "+datas.getPreviousTime());
			System.out.println("Data counted downward : "+datas.isCountDownward());
			datas.setChronometer(new Chronometer(datas, (datas.getMatchTime()*1000)-datas.getPreviousTime(), datas.getPreviousTime(), datas.isCountDownward()));
			Thread t = new Thread(datas.getChronometer());
			t.start();
			System.out.println("Game pause stopped");
		}
			

	}

	// Team 0 is Home, 1 is Guest (default is guest)
	public static synchronized void score(Datas datas, int team, int scoreType) {

		// Rugby :
		if (datas.getHomeTeam().getClass() == RugbyTeam.class) {
			if (team == 0) {
				RugbyTeam r = (RugbyTeam) datas.getHomeTeam();
				if (scoreType == 1) {
					r.scoreConversion();
				} else if (scoreType == 2) {
					r.scoreGoalKick();
				} else if (scoreType == 3) {
					r.scoreTry();
				}
			} else {
				RugbyTeam r = (RugbyTeam) datas.getGuestTeam();
				if (scoreType == 1) {
					r.scoreConversion();
				} else if (scoreType == 2) {
					r.scoreGoalKick();
				} else if (scoreType == 3) {
					r.scoreTry();
				}
			}

			// Soccer
		} else if (datas.getHomeTeam().getClass() == SoccerTeam.class) {
			if (team == 0) {
				SoccerTeam s = (SoccerTeam) datas.getHomeTeam();
				if (scoreType == 1) {
					s.scoreGoal();
				} else if (scoreType == 2) {
					System.out.println("Error : Impossible action, type 2 not defined for Soccer");
				} else if (scoreType == 3) {
					System.out.println("Error : Impossible action, type 3 not defined for Soccer");
				}

			} else {
				SoccerTeam s = (SoccerTeam) datas.getGuestTeam();
				if (scoreType == 1) {
					s.scoreGoal();
				} else if (scoreType == 2) {
					System.out.println("Error : Impossible action, type 2 not defined for Soccer");
				} else if (scoreType == 3) {
					System.out.println("Error : Impossible action, type 3 not defined for Soccer");
				}
			}

			// Basketball
		} else if (datas.getHomeTeam().getClass() == BasketballTeam.class) {
			if (team == 0) {
				BasketballTeam b = (BasketballTeam) datas.getHomeTeam();
				if (scoreType == 1) {
					b.scoreFreeThrow();
				} else if (scoreType == 2) {
					b.scoreBasket();
				} else if (scoreType == 3) {
					b.scoreThreePointBasket();
				}

			} else {
				BasketballTeam b = (BasketballTeam) datas.getGuestTeam();
				if (scoreType == 1) {
					b.scoreFreeThrow();
				} else if (scoreType == 2) {
					b.scoreBasket();
				} else if (scoreType == 3) {
					b.scoreThreePointBasket();
				}
			}
		} else {
			System.out.println("Error : Invalid command sent to score in GameAction");
		}

	}
}
