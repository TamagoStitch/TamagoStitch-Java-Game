package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import model.Model;
import java.util.Calendar;


public class Save {
    private static HashMap<String, Integer> hash = new HashMap<String, Integer>();    // HashMap pour stocker les valeurs du stitch
    private static HashMap<String, Integer> hashMeteo = new HashMap<String, Integer>();   // HashMap pour la meteo

    
    // Méthode pour sauvegarder les données du stitch dans un fichier
    public static void save(Model model) {
        // Création du répertoire de sauvegarde s'il n'existe pas
        File dir = new File("./save");
        dir.mkdir();
        
        // Nom du fichier de sauvegarde basé sur le nom du joueur
        String name = model.tamagostitch.getName();
        File save = new File("./save/"+name+".txt");
        
        // Si le fichier n'existe pas, le créer
        if (!save.exists()) {
            try {
                save.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // Initialisation des objets de flux pour écrire dans le fichier
            Random rand = new Random();
            FileWriter fw = new FileWriter(save);
            BufferedWriter bw = new BufferedWriter(fw);
            
	        //recup la date actuelle
            Calendar c = Calendar.getInstance();
	        long date = c.getTimeInMillis();

            // Définition des valeurs associées à la météo
            hashMeteo.put("sunny", 59);
            hashMeteo.put("rain", 94);
            hashMeteo.put("storm", 99);
            
            // Stockage des données de stitch dans le HashMap
            hash.put("hp", model.tamagostitch.getHp());
            hash.put("energy", model.tamagostitch.getEnergy());
            hash.put("food", model.tamagostitch.getFood());
            hash.put("health", model.tamagostitch.getHealth());
            hash.put("happiness", model.tamagostitch.getHappiness());

            //.
            hash.put("time", model.getTimeInGame());

            // Génération aléatoire d'un delta entre 3 et 9 inclus
            int delta = rand.nextInt(9-3+1)+3;
            bw.write(String.valueOf(delta));
            bw.write(String.valueOf(model.tamagostitch.getType()));
            
            int loc = 0;
            if(model.getCurrentLocation().getName().equals("plage")) {
            	loc=1;
            }else if(model.getCurrentLocation().getName().equals("cabane")) {
            	loc=2;
            }else if(model.getCurrentLocation().getName().equals("foret")) {
            	loc=3;
            }else if(model.getCurrentLocation().getName().equals("village")) {
            	loc=4;
            }
            bw.write(String.valueOf(loc)+":");
            bw.write(String.valueOf(model.getTimeInGame()/100)+":");
            bw.write(String.valueOf(date)+":");
            int offset = 0;
            
            // Boucle pour générer des valeurs aléatoires séparées par ':'
            for (int i = 1; i < 10000; i++) {
                int r = rand.nextInt(2)+3;
                if (offset >= r) {
                    bw.write(":");
                    offset = 0;
                }
                Integer k = rand.nextInt(9);
                bw.write(k.toString());
                offset++;
            }
            bw.write(":");

            // Écriture des données dans le fichier, ces valeurs sont multipliées par le delta pour qu'elles soient moins visibles
            bw.write(hashMeteo.get(model.getCurrentWeather().getName())*delta+ ":");
            bw.write(hash.get("hp")*delta + ":");
            bw.write(hash.get("energy")*delta + ":");
            bw.write(hash.get("food")*delta + ":");
            bw.write(hash.get("health")*delta + ":");
            bw.write(hash.get("happiness")*delta + ":");
            
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer les données sauvegardées à partir d'un fichier
    public static HashMap<String, Integer> getSave(String name) {
        // Initialisation d'un HashMap pour stocker les données lues
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        
        // Création d'un objet File pour le fichier de sauvegarde
        File save = new File("./save/"+name+".txt");
        
        try {
	        // Initialisation des objets de flux pour lire le fichier
	        FileReader fr = new FileReader(save);
	        BufferedReader br = new BufferedReader(fr);
	        
	        // Lecture de la première ligne du fichier
	        String line = br.readLine();
	        // Division de la ligne en parties séparées par ':'
	        String[] part = line.split(":");
	        
	        //recup la date actuelle
            Calendar c = Calendar.getInstance();
	        long date = c.getTimeInMillis();
	        
	        long ancienneDate = Long.parseLong(part[2]);
	        long malus = date-ancienneDate;
	        malus/=86400000;
	        malus*=30;

	        // Extraction des données et stockage dans le HashMap
	        int delta=Integer.parseInt(part[0].substring(0,1));
	        hashmap.put("type",Integer.parseInt(part[0].substring(1,2)));
	        hashmap.put("location",Integer.parseInt(part[0].substring(2,3)));
	        hashmap.put("time", Integer.parseInt(part[1])*100);
	        hashmap.put("meteo", Integer.parseInt(part[part.length-6].substring(part[part.length-6].length()-3))/delta);
	        hashmap.put("hp", Integer.parseInt(part[part.length-5]) / delta);
	        hashmap.put("energy", Integer.parseInt(part[part.length-4]) / delta-(int)malus);
	        hashmap.put("food", Integer.parseInt(part[part.length-3]) / delta-(int)malus);
	        hashmap.put("health", Integer.parseInt(part[part.length-2]) / delta-(int)malus);
	        hashmap.put("happiness", Integer.parseInt(part[part.length-1]) / delta-(int)malus);
	        br.close();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
		return hashmap;
    }    
}