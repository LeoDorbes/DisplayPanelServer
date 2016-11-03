package model;


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
