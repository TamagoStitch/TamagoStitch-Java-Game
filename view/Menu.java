package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;

public class Menu extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private ImageIcon btnNewGame = Image.btnNewGame;
	private ImageIcon btnLoadGame = Image.btnLoadGame;
	private ImageIcon btnQuitGame = Image.btnQuitGame;
	public static Clip menuSound;
	public static Clip buttonSound;
	
	public Menu(Controller controller) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		try {
			
			URL f = View.class.getResource("sounds/menuSound.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(f);
			menuSound = AudioSystem.getClip();
			menuSound.open(ais);
			menuSound.setMicrosecondPosition(0);
			menuSound.loop(Clip.LOOP_CONTINUOUSLY);
			URL b = View.class.getResource("sounds/buttonSound.wav");
			AudioInputStream aisb = AudioSystem.getAudioInputStream(b);
			buttonSound = AudioSystem.getClip();
			buttonSound.open(aisb);
			
			this.controller=controller;
			addMouseListener(this);	
			repaint();
		}
		catch(NullPointerException e) {
			
		}

		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(Image.menu.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(Image.TamagoStitch.getImage(), 50, 50, 500, 60, this);
		g.drawImage(btnNewGame.getImage(), 200, 140, 200, 85, this);		
		g.drawImage(btnLoadGame.getImage(), 200, 250, 200, 85, this);	
		g.drawImage(btnQuitGame.getImage(), 200, 360, 200, 85, this);	
	}
	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1) {
			buttonSound.setMicrosecondPosition(0);
			buttonSound.start();
			controller.toCreatePanel();

		}
		if(loc==2) {
			View.loadPanel = new LoadPanel(controller);
			buttonSound.setFramePosition(0);
			buttonSound.start();
			controller.toLoadPanel();
		}
		if(loc==3) {
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();			
			controller.view.dispose();
		}
	}
	/*
	 * retourne 0 si aucun bouton sont a l'endroit quitter
	 * 			1 si "new Game" est cliqué
	 * 			2 si "Load Game" est cliqué
	 * 			3 si "Quit Game" est cliqué
	 */
	public int checkLocation(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if ((x>200 && x<400) && (y>140 && y<225)) return 1;
		if ((x>200 && x<400) && (y>250 && y<335)) return 2;
		if ((x>200 && x<400) && (y>360 && y<445)) return 3;
		return 0;
	}

	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1) btnNewGame = Image.btnNewGamePressed;
		if(loc==2) btnLoadGame = Image.btnLoadGamePressed;
		if(loc==3) btnQuitGame = Image.btnQuitGamePressed;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		btnNewGame = Image.btnNewGame;
		btnLoadGame = Image.btnLoadGame;
		btnQuitGame = Image.btnQuitGame;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
