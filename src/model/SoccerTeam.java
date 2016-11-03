package model;


public class SoccerTeam extends Team {

	public SoccerTeam(String name) {
		super(name);
	
	}
	public void scoreGoal(){
		super.score++;
	}
}
