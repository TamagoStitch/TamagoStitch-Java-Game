package view;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextArea;

import controller.Controller;
import model.Image;

public class Over extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private ImageIcon btnMenu = Image.btnMenu;
	private ImageIcon btnScore = Image.btnScore;

	private JTextArea txtAreaTime;
	public Clip overSound;
	
	public Over(Controller controller ) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.controller = controller;
		this.addMouseListener(this);
		setLayout(null);
		txtAreaTime = new JTextArea();
		txtAreaTime.setOpaque(false);
		txtAreaTime.setBounds(280, 115, 150, 35);
		txtAreaTime.setEditable(false);
		txtAreaTime.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtAreaTime.setForeground(Color.black);
		this.add(txtAreaTime);
		
		try {
			URL f = View.class.getResource("sounds/overSound.wav");		
			AudioInputStream ais = AudioSystem.getAudioInputStream(f);
			overSound = AudioSystem.getClip();
			overSound.open(ais);
		}
		catch(NullPointerException e) {
			
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(Image.over.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(btnMenu.getImage(), 400, 450, 125, 75, this);
		g.drawImage(btnScore.getImage(), 50, 450, 125, 75, this);
		g.drawImage(Image.displayScore.getImage(), 200, 110, 195, 35, this);
		txtAreaTime.setText(""+controller.model.getTimeInGame()/1000);
	}
	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1)
			try {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				controller.toMenuPanel();
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(loc==2) {
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
			ScorePanel.setBestPlayers();
			controller.toScorePanel();
		}
	}
	/*
	 * 0=null
	 * 1=menu
	 * 2=score
	 */
	public int checkLocation(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if( (x>400 && x<525) && ( y>450 && y<525 ))return 1;
		if( (x>50 && x<175) && (y>450 && y<525)) return 2;
		return 0;

	}
	
	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1)btnMenu=Image.btnMenuPressed;
		if(loc==2)btnScore=Image.btnScorePressed;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		btnMenu=Image.btnMenu;
		btnScore=Image.btnScore;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {} 
	
}
