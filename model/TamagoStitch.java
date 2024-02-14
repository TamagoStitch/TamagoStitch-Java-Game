package model;

public abstract class TamagoStitch {
	
	private String name;
	private int age;
	private int type;
	private int[] deltas;//O=eat, 1=sleep, 2=play, 3=wash, 4=WC		en milliseconde l'interval avant de pouvoir refaire l'action
	private int[] losts;//O=energy, 1=food, 2=health, 3=happiness	combien le Stitch perd au fil du temps en millisecondes
	private String[][] locas; //localisation des action
	private Besoin[] besoins = {new Besoin("hp", 400),
								new Besoin("Energy", 400),
								new Besoin("food", 400),
								new Besoin("health", 400),
								new Besoin("happiness", 400)};
	
	public TamagoStitch(String n, int t, int[] d, int[] l,String[][] s){
		name=n;
		age=0;
		type = t;
		deltas=d;
		losts=l;	
		locas=s;
	}
	
	public TamagoStitch(String name, int type, int hp, int e, int food, int health, int happiness, int[] d, int[] l,int a,String[][] s) {
		
		this.name = name;
		this.type = type;
		besoins[0].setValue(hp);
		besoins[1].setValue(e);
		besoins[2].setValue(food);
		besoins[3].setValue(health);
		besoins[4].setValue(happiness);
		this.deltas=d;
        this.losts=l;
        this.age=a;
        this.locas=s;
	}
	
	
	public void updateHP() {
		besoins[0].setValue((	besoins[1].getValue() + 
								besoins[2].getValue() + 
								besoins[3].getValue() + 
								besoins[4].getValue())/4);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int a) {
		age=a;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type=type;
	}
	
	
//--------------------Methode heritable de satisfaction des besoins--------------------
	public abstract void eat(Location l, Weather w);
	public abstract void sleep(Location l, Weather w);
	public abstract void wash(Location l, Weather w);
	public abstract void wc(Location l, Weather w);
	public abstract void play(Location l, Weather w);
//--------------------Getters des besoins--------------------
	public int getHp() {
		return besoins[0].getValue();
	}

	public int getEnergy() {
		return besoins[1].getValue();
	}

	public int getFood() {
		return besoins[2].getValue();
	}
	
	public int getHealth() {
		return besoins[3].getValue();
	}
	
	public int getHappiness() {
		return besoins[4].getValue();
	}
//--------------------Setters des besoins--------------------
	
	public void setHP(int val) {
		besoins[0].setValue(val);;
	}
	
	public void setEnergy(int val) {
		besoins[1].please(val);
	}

	public void setFood(int val) {
		besoins[2].please(val);
	}

	public void setHealth(int val) {
		besoins[3].please(val);
	}

	public void setHappiness(int val) {
		besoins[4].please(val);
	}
//--------------------Getters des deltas--------------------
	public int getDelta(int i) {
		return deltas[i-1];
	}
	public int getDeltaEat() {
		return deltas[0];
	}

	public int getDeltaSleep() {
		return deltas[1];
	}

	public int getDeltaPlay() {
		return deltas[2];
	}

	public int getDeltaWash() {
		return deltas[3];
	}

	public int getDeltaWC() {
		return deltas[4];
	}
//--------------------Getters des losts--------------------
	public int getLostEnergy() {
		return losts[0];
	}

	public int getLostFood() {
		return losts[1];
	}

	public int getLostHealth() {
		return losts[2];
	}

	public int getLostHappiness() {
		return losts[3];
	}	
//--------------------Getters des localisations--------------------
	public String[] getLocas(int i) {
		return locas[i-1];
	}
	
	public String[][] getFullLocas(){
		return locas;
	}
	public void setLocas(String[][] locas) {
		this.locas = locas;
	}

}
