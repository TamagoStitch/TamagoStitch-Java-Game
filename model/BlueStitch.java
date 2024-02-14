package model;

public class BlueStitch extends TamagoStitch{
	
	private static final int[] deltas = {5000, 5000, 5000, 5000, 5000};//eat, sleep, play, wash, wc
	private static final int[] losts = {1, 1, 1, 1};//energy, food, health, happiness
	private static final String[][] locas = {{"cabane","village"},{"plage","village","foret"},
			{"cabane"},{"plage"},{"foret"}};//eat,play,sleep,wash,wc
	
	public BlueStitch(String n) {
		super(n, 2, deltas, losts,locas);
	}
	
	public BlueStitch(String name, int hp, int energy, int food, int health, int happiness,int a) {
        super(name, 2, hp, energy, food, health, happiness, deltas, losts,a,locas);
    }

	public void eat(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")){
			if(wName.equals("sunny")){
				setHappiness(5);
				setEnergy(15);
				setFood(70);
			}
			if(wName.equals("rain")){
				setHappiness(5);
				setEnergy(15);
				setFood(80);
			}
			if(wName.equals("storm")) {
				setHappiness(10);
				setEnergy(10);
				setFood(90);
				
			}
		}
		
		if(lName.equals("village")) {
			if(wName.equals("sunny")) {
				setEnergy(15);
				setFood(110);
				setHappiness(30);
			}
			if(wName.equals("rain")) {
				setEnergy(15);
				setFood(80);
				setHappiness(20);
			}
			if(wName.equals("storm")) {
				setEnergy(20);
				setFood(30);
				setHappiness(15);
			}
		}	
	}

	public void sleep(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")) {
			if(wName.equals("sunny")) {
				setEnergy(120);
				setHappiness(-25);
			}
			if(wName.equals("rain")) {
				setEnergy(100);
				setHappiness(-25);
			}
			if(wName.equals("storm")) {
				setEnergy(50);
				setHappiness(-30);
			}
		}	
	}

	public void wash(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("plage")){
			if(wName.equals("sunny")) {
				setHealth(100);
				setHappiness(10);
			}
			if(wName.equals("rain")){
				setHealth(80);
				setHappiness(10);
			}
			if(wName.equals("storm")){
				setHealth(20);
				setHappiness(15);			
			}
		}
	}

	public void wc(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("foret")) {
			if(wName.equals("sunny")) {
				setHealth(90);
				setHappiness(-10);	
			}
			if(wName.equals("rain")) {
				setHealth(70);
				setHappiness(-10);	
			}
			if(wName.equals("storm")) {
				setHealth(20);
				setHappiness(15);
			}				
		}
	}

	public void play(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("plage")){
			if(wName.equals("sunny")){
				setEnergy(-40);
				setFood(-20);
				setHappiness(80);
				setHealth(-40);
			}
			if(wName.equals("rain")) {
				setEnergy(-20);
				setFood(-20);
				setHappiness(60);
				setHealth(-20);
			}
			if(wName.equals("storm")) {
				setEnergy(-30);
				setFood(-30);
				setHappiness(30);
				setHealth(-30);
			}
		}
		if(lName.equals("village")){
			if(wName.equals("sunny")) {
				setEnergy(-30);
				setFood(-20);
				setHappiness(60);
				setHealth(-30);
			}
			if(wName.equals("rain")) {
				setEnergy(-20);
				setFood(-20);
				setHappiness(70);
				setHealth(-20);
			}
			if(wName.equals("storm")) {
				setEnergy(-30);
				setFood(-30);
				setHappiness(30);
				setHealth(-30);
			}
		}
		if(lName.equals("foret")) {
			if(wName.equals("sunny")) {
				setEnergy(-20);
				setFood(-20);
				setHappiness(70);
				setHealth(-20);
			}
			if(wName.equals("rain")) {
				setEnergy(-20);
				setFood(-20);
				setHappiness(60);
				setHealth(-20);
			}
			if(wName.equals("storm")) {
				setEnergy(-30);
				setFood(-30);
				setHappiness(30);
				setHealth(-30);
			}
		}
	}
}