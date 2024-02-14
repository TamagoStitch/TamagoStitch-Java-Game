package controller;

import java.io.IOException;
import java.util.HashMap;

import model.BlueStitch;
import model.BotStitch;
import model.Model;
import model.PinkStitch;
import model.RedStitch;
import model.TamagoStitch;

public class Load {
	
    // Nom du fichier de sauvegarde à charger
	private String name;
	private Model model;
	
    // Constructeur prenant le nom du fichier de sauvegarde en paramètre
	public Load(String name, Model model) {
		this.name = name;
		this.model = model;
	}
	
    // Méthode pour charger un objet TamagoStitch à partir d'un fichier de sauvegarde
	public TamagoStitch chargeStitch() throws IOException {
        // Initialisation d'un HashMap pour stocker les données lues à partir du fichier
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		hashmap = Save.getSave(name);
		int type=hashmap.get("type");
        // Récupération des valeurs du HashMap
		int w = hashmap.get("meteo");
		// Convertir les codes météorologiques en chaînes de caractères
		if (w==59) model.setCurrentWeather(model.weathers[0]);
		else if (w==94) model.setCurrentWeather(model.weathers[1]);
		else model.setCurrentWeather(model.weathers[2]);
		
		int l = hashmap.get("location");
		if (l==1) model.setCurrentLocation(model.locations[0]);
		else if (l==2) model.setCurrentLocation(model.locations[1]);
		else if(l==3)model.setCurrentLocation(model.locations[2]);
		else model.setCurrentLocation(model.locations[3]);

		int hp = hashmap.get("hp");
		int energy = hashmap.get("energy");
		int food = hashmap.get("food");
		int health = hashmap.get("health");
		int happiness = hashmap.get("happiness");
		int age = hashmap.get("time");
		
        // Création d'un objet TamagoStitch (ici, un PinkStitch) avec les valeurs récupérées
		TamagoStitch stitch=null;
		
		if(type==1) stitch = new PinkStitch(name, hp, energy, food, health, happiness,age);
		if(type==2) stitch = new BlueStitch(name, hp, energy, food, health, happiness,age);
		if(type==3) stitch = new BotStitch(name, hp, energy, food, health, happiness,age);
		if(type==4) stitch = new RedStitch(name, hp, energy, food, health, happiness,age);
		return stitch;
	}
}