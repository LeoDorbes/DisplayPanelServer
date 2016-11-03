package model;


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
