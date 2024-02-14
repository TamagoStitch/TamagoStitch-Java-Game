package model;

public class RedStitch extends TamagoStitch {
	
	private static final int[] deltas = {10000, 10000, 10000, 10000, 10000};//eat, sleep, play, wash, wc
	private static final int[] losts = {3, 3, 3, 3};//energy, food, health, happiness
	private static final String[][] locas = {{"cabane","village","plage","foret"},{"cabane","village"},
			{"cabane","foret"},{"plage","foret"},{"plage","foret"}};//eat,play,sleep,wash,wc
	
	public RedStitch(String n) {
		super(n, 4, deltas, losts,locas);
	}
	
	public RedStitch(String n, int hp, int e, int food, int health, int happiness,int a) {
        super(n, 4, hp, e, food, health, happiness, deltas, losts,a,locas);
    }

	public void eat(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(100);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(110);
				setHappiness(20);
			}
			if(wName.equals("storm")) {
				setEnergy(-100);
				setHealth(-100);
				setFood(-100);
				setHappiness(-100);
			}
		}
		if(lName.equals("plage")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(80);
				setHappiness(-30);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(100);
				setHappiness(-30);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
		if(lName.equals("foret")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(70);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(90);
				setHappiness(20);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
		if(lName.equals("village")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(120);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(140);
				setHappiness(40);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
	}
	public void sleep(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")){
			if(wName.equals("sunny")) {
				setEnergy(100);
				setFood(-50);
				setHappiness(-30);
			}
			if(wName.equals("rain")) {
				setEnergy(80);
				setFood(-30);
				setHappiness(-30);
			}
			if(wName.equals("storm")) {
				setEnergy(-100);
				setHealth(-100);
				setFood(-100);
				setHappiness(-100);
			}
		}
		if(lName.equals("foret")){
			if(wName.equals("sunny")) {
				setEnergy(120);
				setHealth(-30);
				setFood(-50);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(140);
				setHealth(-30);
				setFood(-30);
				setHappiness(20);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
	}
	public void play(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")){
			if(wName.equals("sunny")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(-30);
				setHappiness(120);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(-30);
				setFood(-30);
				setHappiness(80);
			}
			if(wName.equals("storm")) {
				setEnergy(-100);
				setHealth(-100);
				setFood(-100);
				setHappiness(-100);
			}
		}
		if(lName.equals("village")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(-30);
				setHappiness(100);
			}
			if(wName.equals("rain")) {
				setEnergy(-50);
				setHealth(-30);
				setFood(-30);
				setHappiness(120);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
	}
	public void wash(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("plage")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(80);
				setFood(-30);
				setHappiness(-30);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(100);
				setFood(-30);
				setHappiness(-30);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
		if(lName.equals("foret")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(100);
				setFood(-30);
				setHappiness(-50);
			}
			if(wName.equals("rain")) {
				setEnergy(-50);
				setHealth(80);
				setFood(-30);
				setHappiness(-30);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
	}
	public void wc(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("plage")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(30);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(40);
				setHappiness(30);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
		if(lName.equals("foret")){
			if(wName.equals("sunny")) {
				setEnergy(-50);
				setHealth(40);
				setHappiness(20);
			}
			if(wName.equals("rain")) {
				setEnergy(-30);
				setHealth(20);
				setHappiness(30);
			}
			if(wName.equals("storm")) {
				setEnergy(100);
				setHealth(100);
				setFood(100);
				setHappiness(100);
			}
		}
	}
}