package model;

/**
 * Team class representing a generic instance of the Team.
 * 
 * @author Léo Dorbes
 * @version 1.0 08 Dec, 2016.
 */
public class Team {

	protected String name;
	protected int score;
	
	public Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
