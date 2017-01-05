package model;

/**
 * RugbyTeam class representing a specific instance of a rugby team.
 * 
 * @author Léo Dorbes
 * @version 1.0 08 Dec, 2016.
 */
public class RugbyTeam extends Team {

	public RugbyTeam(String name) {
		super(name);
	}
	
	public void scoreTry(){
		super.score += 5;
	}
	
	public void scoreConversion(){
		super.score += 2;
	}
	
	public void scoreGoalKick(){
		super.score += 3;
	}
}
