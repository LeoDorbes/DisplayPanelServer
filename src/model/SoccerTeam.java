package model;

/**
 * SoccerTeam class representing a specific instance of a soccer team.
 * 
 * @author Léo Dorbes
 * @version 1.0 08 Dec, 2016.
 */
public class SoccerTeam extends Team {

	public SoccerTeam(String name) {
		super(name);
	
	}
	public void scoreGoal(){
		super.score++;
	}
}
