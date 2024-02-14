package model;

public class Besoin {
	
	private String name;
	private int value;
	private final int MAX_VALUE = 400;
	private final int MIN_VALUE = 0;
	
	public Besoin(String n, int v) {
		name=n;
		value=v;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}	
	
	/*
	 *	@input(val) : 	positif ou negatif 
	 *					valeur a additionner ou soustraire 
	 *					de la valeur courante
	 */
	public void please(int val) {
		if(value+val > MAX_VALUE) {
			value= MAX_VALUE;
		}else if(value+val < MIN_VALUE) {
			value=MIN_VALUE;
		}else {
			value+=val;
		}
	}

}