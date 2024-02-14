package model;

public class PinkStitch extends TamagoStitch{		
		private static final int[] deltas = {3000, 3000, 3000, 3000, 3000};//eat, sleep, play, wash, wc
		private static final int[] losts = { 1, 1, 1, 1};//energy, food, health, happiness
		private static final String[][] locas = {{"cabane","village"},{"plage","village"},
				{"cabane","foret"},{"plage","foret"},{"cabane"}};//eat,play,sleep,wash,wc
		
		public PinkStitch(String n) {
			super(n, 1, deltas, losts,locas);
		}
		
		public PinkStitch(String name, int hp, int energy, int food, int health, int happiness,int age) {
            super(name, 1, hp, energy, food, health, happiness, deltas, losts,age,locas);
        }

		public void eat(Location l, Weather w) {
			String lName = l.getName();
			String wName = w.getName();
			if(lName.equals("cabane")){
				if(wName.equals("sunny")){
					setHappiness(10);
					setEnergy(30);
					setFood(100);
				}
				if(wName.equals("rain")){
					setHappiness(10);
					setEnergy(30);
					setFood(110);
				}
				if(wName.equals("storm")) {
					setHappiness(15);
					setEnergy(20);
					setFood(120);
				}
			}
			
			if(lName.equals("village")) {
				if(wName.equals("sunny")) {
					setEnergy(20);
					setFood(120);
					//setHappiness(50);
				}
				if(wName.equals("rain")) {
					setEnergy(20);
					setFood(110);
					//setHappiness(30);
				}
				if(wName.equals("storm")) {
					setEnergy(10);
					setFood(70);
					//setHappiness(-10);
				}
			}	
		}

		public void sleep(Location l, Weather w) {
			String lName = l.getName();
			String wName = w.getName();
			if(lName.equals("cabane")) {
				if(wName.equals("sunny")) {
					setEnergy(120);
					setHappiness(15);
				}
				if(wName.equals("rain")) {
					setEnergy(100);
					setHappiness(20);
				}
				if(wName.equals("storm")) {
					setEnergy(50);
					setHappiness(25);
				}
			}
			if(lName.equals("foret")) 
			{
				if(wName.equals("sunny")) {
					setEnergy(150);
					//setHealth(-5);
					setHappiness(10);
				}
				if(wName.equals("rain")) {
					setEnergy(50);
					//setHealth(-15);
					setHappiness(-5);
				}
				if(wName.equals("storm")){
					setEnergy(-20);
					setHealth(-30);
					setHappiness(-30);
				}
			}		
		}

		public void wash(Location l, Weather w) {
			String lName = l.getName();
			String wName = w.getName();
			if(lName.equals("plage") || lName.equals("foret")) {
				if(wName.equals("sunny")) {
					setHealth(100);
					setHappiness(10);
				}
				if(wName.equals("rain")){
					setHealth(150);
					setHappiness(10);
				}
				if(wName.equals("storm")){
					setHealth(10);
					setHappiness(-20);			
				}
			}
		}

		public void wc(Location l, Weather w) {
			if(l.getName().equals("cabane")) {
				setHealth(120);
				setHappiness(10);	
			}
		}

		public void play(Location l, Weather w) {
			String lName = l.getName();
			String wName = w.getName();
			if(lName.equals("plage")){
				if(wName.equals("sunny")){
					setEnergy(-10);
					setHealth(-10);
					setFood(-20);
					setHappiness(160);
				}
				if(wName.equals("rain")){
					setEnergy(-20);
					setHealth(20);
					setFood(-20);
					setHappiness(120);
				}
				if(wName.equals("storm")){
					setEnergy(-40);
					setHealth(-30);
					setFood(-30);
					setHappiness(-10);
				}
			}
			if(lName.equals("village")){
				if(wName.equals("sunny")) {
					//setEnergy(-5);
					//setHealth(-15);
					setFood(-10);
					setHappiness(100);
				}
				if(wName.equals("rain")){
					//setEnergy(-15);
					setHealth(10);
					setFood(-20);
					setHappiness(50);
				}
				if(wName.equals("storm")) {
					setEnergy(-15);
					setHealth(-30);
					setFood(-30);
					setHappiness(30);
				}
			}		
		}
}