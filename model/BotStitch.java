package model;

public class BotStitch extends TamagoStitch{
	
	private static final int[] deltas = {7000, 7000, 7000, 7000, 7000};//eat, sleep, play, wash, wc
	private static final int[] losts = {2, 2, 2, 2};//energy, food, health, happiness
	private static final String[][] locas = {{"cabane","village"},{"plage","village","foret"},
			{"cabane","plage"},{"cabane","foret"},{"cabane","village"}};//eat,play,sleep,wash,wc
	
	public BotStitch(String n) {
		super(n, 3,deltas, losts,locas);
	}
	
	public BotStitch(String name, int hp, int energy, int food, int health, int happiness,int a) {
        super(name, 3, hp, energy, food, health, happiness, deltas, losts,a,locas);
    }

	public void eat(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")) 
		{
			if(wName.equals("sunny")) 
			{
				setHappiness(25);
				setFood(80);
			}
			if(wName.equals("rain")) 
			{
				setHappiness(25);
				setFood(90);
			}
			if(wName.equals("storm")) 
			{
				setHappiness(35);
				setFood(100);
			}
		}
		
		if(lName.equals("village")) 
		{
			if(wName.equals("sunny")) 
			{
				setFood(120);
				setHappiness(25);
			}
			if(wName.equals("rain")) 
			{
				setFood(70);
				setHappiness(15);
			}
			if(wName.equals("storm")) 
			{
				setHappiness(5);
				setFood(30);
			}
		}	
	}

	public void sleep(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")) 
		{
			if(wName.equals("sunny")) 
			{
				setEnergy(120);
				setHappiness(-25);
			}
			if(wName.equals("rain")) {
				setEnergy(120);
				setHappiness(-15);
			}
			if(wName.equals("storm")) {
				setEnergy(70);
				setHappiness(-15);
			}
		}
		if(lName.equals("plage")) 
		{
			//setFood(-100);
			setEnergy(-100);
			setHappiness(-100);
		}		
	}

	public void wash(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")) {
			setHealth(70);
			setEnergy(35);
			setHappiness(15);				
		}
		if(lName.equals("foret")) {
			
			if(wName.equals("sunny")) {
				setHealth(120);
				setHappiness(25);
			}
			if(wName.equals("rain")) 
			{
				setHealth(50);
				setHappiness(20);
			}
			if(wName.equals("storm")) 
			{
				setHealth(-100);
				//setEnergy(-50);
				setHappiness(-100);			
			}
		}
	}

	public void wc(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("cabane")) {
			setHealth(120);
			setHappiness(-10);
			setFood(-10);
		}
		if(lName.equals("village")) {
			if(wName.equals("sunny")) 
			{
				setHealth(150);
				setHappiness(10);
				setEnergy(20);
				setFood(-20);
			}
			if(wName.equals("rain")) 
			{
				setHealth(80);
				//setHappiness(-40);
				setEnergy(10);
				setFood(-30);
			}
			if(wName.equals("storm"))
			{
				setHealth(-50);
				setHappiness(50);
				setEnergy(150);
				setFood(-50);
			}
		}
	}

	public void play(Location l, Weather w) {
		String lName = l.getName();
		String wName = w.getName();
		if(lName.equals("plage")) 
		{
			if(wName.equals("sunny")) 
			{
				setEnergy(-50);
				setHealth(-50);
				setFood(-50);
				setHappiness(300);
			}
			if(wName.equals("rain")) 
			{
				setEnergy(-60);
				setHealth(-60);
				setFood(-40);
				setHappiness(200);
			}
			if(wName.equals("storm"))
			{
				setEnergy(-70);
				setHealth(-70);
				setFood(-40);
				setHappiness(100);
			}
		}
		if(lName.equals("foret")) 
		{
			if(wName.equals("sunny")) 
			{
				setEnergy(-10);
				setHealth(10);
				setFood(-10);
				setHappiness(100);
			}
			if(wName.equals("rain")) 
			{
				setEnergy(-20);
				setHealth(20);
				setFood(-20);
				setHappiness(80);
			}
			if(wName.equals("storm"))
			{
				setEnergy(100);
				setHealth(-50);
				setFood(-50);
				setHappiness(70);
			}
		}	
		if(lName.equals("village")) 
		{
			if(wName.equals("sunny")) 
			{
				setEnergy(-10);
				setHealth(-10);
				setFood(-10);
				setHappiness(100);
			}
			if(wName.equals("rain")) 
			{
				setEnergy(-15);
				setHealth(-15);
				setFood(-15);
				setHappiness(70);
			}
			if(wName.equals("storm"))
			{
				setEnergy(80);
				setHealth(-80);
				setFood(-50);
				setHappiness(80);
			}
		}
	}
}