package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;

public  class Pause extends JPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		
		private Controller controller;
		private Game game;
		public Rules rules;
		
		private ImageIcon btnMenu = Image.btnMenu;
		private ImageIcon btnUnpause = Image.btnUnpause;
		private ImageIcon btnRules = Image.btnRules;
		private ImageIcon btnSave = Image.btnSave;
		
		public Pause(Controller controller, Game game) {
			this.controller=controller;
			this.game=game;
			this.rules = new Rules(controller, this);
			this.setBackground(Color.orange);
			this.addMouseListener(this);
			setLayout(null);
			
			this.add(rules);
			rules.setVisible(false);
			rules.setBounds(0, 0, 600, 600);
			
			repaint();
		}
		
		public void displayRules(boolean bol) {
			rules.setVisible(bol);
			rules.setType(controller.model.tamagostitch.getType());
			int type = controller.model.tamagostitch.getType();
			if(type==1)rules.text.setText(rules.ENpinkText);
			if(type==2)rules.text.setText(rules.ENblueText);
			if(type==3)rules.text.setText(rules.ENbotText);
			if(type==4)rules.text.setText(rules.ENredText);
			rules.btnLang=Image.french;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(Image.pause.getImage(), 0, 0, getWidth(), getHeight(), this);
			g.drawImage(btnUnpause.getImage(), 475, 50, 50, 50, this);
			g.drawImage(btnMenu.getImage(), 240, 180, 125, 75, this);
			g.drawImage(btnRules.getImage(), 240, 260, 125, 75, this);
			g.drawImage(btnSave.getImage(), 240, 340, 125, 75, this);

		}

		public void mouseClicked(MouseEvent e) {
			int loc = checkLocation(e);
			if(loc==1) {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				game.displayPause(controller.getRunning());
			}
			if(loc==2)
				try {
					Menu.buttonSound.setFramePosition(0);
					Menu.buttonSound.start();
					controller.toMenuPanel();
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(loc==3) {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				displayRules(true);
			}
			if(loc==4) {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				controller.saveAndQuit();
			}
		}
		
		/*
		 * 0=null
		 * 1=unpause
		 * 2=menu
		 * 3=rules
		 * 4=save
		 */
		public int checkLocation(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if( (x>475 && x<525) && (y>50 && y<100) )return 1;
			if( (x>240 && x<365) && (y>180 && y<255) )return 2;
			if( (x>240 && x<365) && (y>260 && y<335) )return 3;
			if( (x>240 && x<365) && (y>340 && y<415) )return 4;
			return 0;
		}
		
		public void mousePressed(MouseEvent e) {
			int loc = checkLocation(e);
			if (loc==2)btnMenu = Image.btnMenuPressed;
			if (loc==3)btnRules = Image.btnRulesPressed;
			if (loc==4)btnSave = Image.btnSavePressed;
		
		}
		public void mouseReleased(MouseEvent e) {
			btnMenu = Image.btnMenu;
			btnRules = Image.btnRules;
			btnSave = Image.btnSave;
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

	}