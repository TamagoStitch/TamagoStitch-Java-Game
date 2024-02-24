package view;


import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;

public class LoadPanel extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
	private JLabel lblCreate = new JLabel("CHARGE DU PERSONNAGE");
    @SuppressWarnings("unused")
	private ImageIcon charge = Image.create;
    private static ArrayList<PlayerPanel> playerPanels;
    private Controller controller;
    private ImageIcon btnBack = Image.btnBack;
    private ImageIcon btnScore = Image.btnScore;
    
    public LoadPanel(Controller controller) {
        // initialisation controlleur
        this.controller = controller;
        this.addMouseListener(this);
        
        // Utilisation de GridBagLayout
        setLayout(new GridBagLayout());

        // liste des save de joueurs
        playerPanels = getPlayerPanelFromFile();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // alignement à gauche
        gbc.insets = new Insets(10, 10, 10, 10); // Marge autour des panneaux
        
        for (PlayerPanel playerPanel : playerPanels) {
        	JLabel labelPlayer = playerPanel.getLabelPlayer();
        	
        	add(labelPlayer,gbc);
        	
        	gbc.gridx +=1;
        	gbc.gridwidth=1; //colonne player
        	add(playerPanel.getLabelScore(), gbc);
        	
        	gbc.gridx += 1; 
            gbc.gridwidth = 1; // colonne bouton Load
            JLabel btnLoad = playerPanel.getBtnLoad();
            add(btnLoad, gbc);
            
            gbc.gridx += 1;
            gbc.gridwidth = 1; // colonne bouton Delete
            JLabel btnDelete = playerPanel.getBtnDelete();
            add(btnDelete, gbc);

            gbc.gridy++; // ligne suivante
            gbc.gridx = 0; // retour première colonne
        }

        repaint();
    }

    public ArrayList<PlayerPanel> getPlayerPanelFromFile() {
        File dir = new File("./save/");
        String liste[] = dir.list();
        ArrayList<PlayerPanel> playerPanels = new ArrayList<PlayerPanel>();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                String playerName = liste[i].substring(0, liste[i].length() - 4);
                String score = String.valueOf(getScorePlayer(playerName)/1000);
                PlayerPanel PlayerPanel = new PlayerPanel(controller, playerName, score);
                playerPanels.add(PlayerPanel);
            }
        } else {
            System.out.println("repertoire save introuvable");
        }
        return playerPanels;
    }

    public int getScorePlayer(String player) {
        HashMap<String, Integer> hash = controller.getSavePlayer(player);
        return hash.get("time");
    }
    public ArrayList<PlayerPanel> getPlayerName() {
    	return playerPanels;
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(Image.load2.getImage(), 0, 0, getWidth(), getHeight(), this);
        g.drawImage(Image.loadCharacter.getImage(), 50, 50, 500, 50, this);
        g.drawImage(btnBack.getImage(), 30, 440, 150, 85, this);
        g.drawImage(btnScore.getImage(), 400, 440, 150, 85, this);

    }

    public void mouseClicked(MouseEvent e) {
        int loc = checkLocation(e);
        if (loc == 1) {
        	try {
        		Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
        		controller.toMenuPanel();
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if (loc==2) {
        	Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
        	controller.toScorePanel();
        }
    }

    /*
     * 	0 = null
     *  1 = back
     */
    public int checkLocation(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if ((x > 30 && x < 180) && (y > 440 && y < 525))
            return 1;
        else if((x>400 && x < 550) && (y>440 && y < 525))
        	return 2;
        return 0;
    }

    public void mousePressed(MouseEvent e) {
        int loc = checkLocation(e);
        if (loc == 1) btnBack = Image.btnBackPressed;
        if (loc == 2) btnScore = Image.btnScorePressed;
        repaint();
        
    }

    public void mouseReleased(MouseEvent e) {
        btnBack = Image.btnBack;
        btnScore = Image.btnScore;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}