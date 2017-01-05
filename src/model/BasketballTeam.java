package model;

/**
 * BasketballTeam class representing a specific instance of a basketball team.
 * 
 * @author Léo Dorbes
 * @version 1.0 08 Dec, 2016.
 */
public class BasketballTeam extends Team {

	public BasketballTeam(String name) {
		super(name);
	}
	
	public void scoreBasket(){
		super.score += 2;
	}
	
	public void scoreThreePointBasket(){
		super.score += 3;
	}
	
	public void scoreFreeThrow(){
		super.score += 1;
	}
}
