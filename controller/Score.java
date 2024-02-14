package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Model;

public class Score {
	
	
	public static void saveScore(Model model) throws IOException {
		// Création du répertoire de score s'il n'existe pas
        File dir = new File("./score");
        dir.mkdir();
        
        // Nom du fichier des scores
        File score = new File("./score/score.txt");
        
        // Si le fichier n'existe pas, le créer
        if (!score.exists()) {
            try {
                score.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
        	FileWriter fw = new FileWriter(score,true);
        	BufferedWriter bw = new BufferedWriter(fw);
        	
        	//on recupere le temps de jeu du stitch
        	int mil = model.getTimeInGame()/1000;
        	
        	//on recupere le nom du joueur
    		String name = model.tamagostitch.getName();
    		
    		//on recupere le type du stitch
    		int type = model.tamagostitch.getType();
    		
       		//on ecrit le score dans le fichier texte avec le nom, le temps de jeu et le type du stitch
        	String s = name + ":" + mil + ":" + type + '\n';
        		bw.write(s);
        		bw.close();
		} 
        
        catch (IOException e) {
        e.printStackTrace();
		}
	}
	
	public static boolean checkNotEmptyFile() throws IOException {
		File save = new File("./score/score.txt");

	    FileReader fr = new FileReader(save);
	    BufferedReader br = new BufferedReader(fr);

	    String line = br.readLine();
	    br.close();
	    if(line==null) return true;
	    else return false;
	}
}
