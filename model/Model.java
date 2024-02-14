package model;

import controller.Controller;

public class Model {
	
	@SuppressWarnings("unused")
	private Controller controller;
	
	public Location[] locations = {new Location("plage",Image.plage),
									new Location("cabane",Image.cabane),
									new Location("foret",Image.foret),
									new Location("village", Image.village)};
	public Weather[] weathers = {	new Weather("sunny", 40, Image.sunny),
									new Weather("rain", 70, Image.rain),
									new Weather("storm", 100, Image.storm)};
	private Location currentLocation;
	private Weather currentWeather;
	
	public TamagoStitch tamagostitch;
	
	private int timeInGame;

	public Model(Controller c) {
		this.controller = c;
	}
	
	public void setTimeInGame(int timeInGame) {
		this.timeInGame = timeInGame;
	}
	
	public int getTimeInGame() {
		return timeInGame;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Weather getCurrentWeather() {
		return currentWeather;
	}

	public void setCurrentWeather(Weather currentWeather) {
		this.currentWeather = currentWeather;
	}
	
	public void setStitch(TamagoStitch stitch) {
		this.tamagostitch=stitch;
	}

}
