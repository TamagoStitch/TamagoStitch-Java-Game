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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controller.Controller;
import model.Image;
import model.Location;
import model.Weather;

public class Game extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	public Pause pause;
	private ImageIcon StitchIcon;
	
	//____________________Composants____________________
	
	public JProgressBar HPBar = new JProgressBar(0, 400);
	public JProgressBar energyBar = new JProgressBar(0, 400);
	public JProgressBar foodBar = new JProgressBar(0, 400);
	public JProgressBar healthBar = new JProgressBar(0, 400);
	public JProgressBar happinessBar = new JProgressBar(0, 400);
	private JLabel lblTimeInGame = new JLabel("0");
	
	private ImageIcon btnEat = Image.btnEat;
	private ImageIcon btnPlay = Image.btnPlay;
	private ImageIcon btnSleep;
	private ImageIcon btnWash;
	private ImageIcon btnWC;
	private ImageIcon btnEatNope = Image.btnEatEnCD;
	private ImageIcon btnPlayNope = Image.btnPlayEnCD;
	private ImageIcon btnSleepNope = Image.btnSleepEnCD;
	private ImageIcon btnWashNope = Image.btnWashEnCD;
	private ImageIcon btnWCNope = Image.btnWCEnCD;
	private ImageIcon btnLeft = Image.btnLeft;
	private ImageIcon btnRight = Image.btnRight;
	private ImageIcon btnPause = Image.btnPause;
	private ImageIcon AnimeGif = Image.FondTrans;
	private boolean AnimeEnCours=false;		
	private long[] lastClickTime= {0,0,0,0,0};
	
	public Clip gameSound;
	public Clip botSound;
	public Clip madSound;
	public Clip rainSound;
	public Clip stormSound;
	
	public Game(Controller controller) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.controller = controller;
		pause = new Pause(controller, this);
		this.addMouseListener(this);
		setLayout(null);
		
		this.add(energyBar);
		energyBar.setBounds(49, 17, 146, 17);
		
		this.add(foodBar);
		foodBar.setBounds(49, 64, 146, 17);
		
		this.add(healthBar);
		healthBar.setBounds(249, 17, 146, 17);
		
		this.add(happinessBar);		
		happinessBar.setBounds(249, 64, 146, 17);
		
		this.add(HPBar);
		HPBar.setBounds(249, 370, 146, 17);
		
		this.add(lblTimeInGame);
		lblTimeInGame.setBounds(450, 13, 180, 30);
		lblTimeInGame.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblTimeInGame.setForeground(Color.white);
		
		this.add(pause);
		pause.setVisible(false);
		pause.setBounds(0, 0, 600, 600);
		try {
			URL f = View.class.getResource("sounds/gameSound.wav");
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(f);
			gameSound = AudioSystem.getClip();
			gameSound.open(ais);
	
			
			URL fr = View.class.getResource("sounds/rainSound.wav");
			AudioInputStream aisr = AudioSystem.getAudioInputStream(fr);
			rainSound = AudioSystem.getClip();
			rainSound.open(aisr);
			rainSound.loop(Clip.LOOP_CONTINUOUSLY);
			rainSound.stop();
			
			URL fs = View.class.getResource("sounds/stormSound.wav");
			AudioInputStream aiss = AudioSystem.getAudioInputStream(fs);
			stormSound = AudioSystem.getClip();
			stormSound.open(aiss);
			stormSound.loop(Clip.LOOP_CONTINUOUSLY);
			stormSound.stop();
			
			URL rob = View.class.getResource("sounds/robotSound.wav");
			AudioInputStream aisRob = AudioSystem.getAudioInputStream(rob);
			botSound = AudioSystem.getClip();
			botSound.open(aisRob);
	
			
			URL mad = View.class.getResource("sounds/madSound.wav");
			AudioInputStream aisMad = AudioSystem.getAudioInputStream(mad);
			madSound = AudioSystem.getClip();
			madSound.open(aisMad);
		}
		catch(NullPointerException e) {
			
		}

	}
	
	/*
	 * 	Permet de modifier la valeur du bar de progression et sa couleur en fonction
	 * 	@jpb : la bar de progressiona  mofifier
	 * 	@value : la nouvelle valeur de la bar de progression
	 */
	public void updatePB(JProgressBar jpb, int value) {
		if(value >400) {
			jpb.setValue(400);
			jpb.setForeground(new Color(155, 255, 0));
		}else if(value <0) {
			jpb.setValue(0);
			jpb.setForeground(new Color(255, 0, 0));
		}else {
			jpb.setValue(value);
			jpb.setForeground(new Color(255-value/4, value/4*255/100, 0));
		}
	}
	
	//____________________Setteurs____________________
	
		public void setLastClickTime(int i) {
			for(int j=0;j<lastClickTime.length;j++) {
				lastClickTime[j]=i;
			}
		}
		public void setBoolA(boolean v) {
			AnimeEnCours=v;
		}
		public void setBtnEat(ImageIcon i) {
			btnEat=i;
		}
		public void setBtnPlay(ImageIcon i) {
			btnPlay=i;
		}
		public void setBtnSleep(ImageIcon i) {
			btnSleep=i;
		}
		public void setBtnWash(ImageIcon i) {
			btnWash=i;
		}
		public void setBtnWC(ImageIcon i) {
			btnWC=i;
		}
		public void setBtnEatN(ImageIcon i) {
			btnEatNope=i;
		}
		public void setBtnPlayN(ImageIcon i) {
			btnPlayNope=i;
		}
		public void setBtnSleepN(ImageIcon i) {
			btnSleepNope=i;
		}
		public void setBtnWashN(ImageIcon i) {
			btnWashNope=i;
		}
		public void setBtnWCN(ImageIcon i) {
			btnWCNope=i;
		}
		public void setStitchIcon(ImageIcon ii) {
			StitchIcon = ii;
		}
		public void setAnimeGif(ImageIcon ii) {
			AnimeGif = ii;
		}
	
	public void displayPause(boolean bol) {
		HPBar.setVisible(!bol);
		energyBar.setVisible(!bol);
		foodBar.setVisible(!bol);
		healthBar.setVisible(!bol);
		happinessBar.setVisible(!bol);
		
		pause.setVisible(bol);
		controller.pause();
	}
	
	
	/*
	 *	Met les visuelles a jour en fonction des nouvelles valeur du stich
	 *	-> Bar de progression + background 
	 */
	@Override
	public void paintComponent(Graphics g) {
				
		updatePB(energyBar, controller.model.tamagostitch.getEnergy());
		updatePB(foodBar, controller.model.tamagostitch.getFood());
		updatePB(healthBar, controller.model.tamagostitch.getHealth());
		updatePB(happinessBar,controller.model.tamagostitch.getHappiness());
		int moyenne = 	(energyBar.getValue() +
					foodBar.getValue() +
					healthBar.getValue() +
					happinessBar.getValue())/4;
		updatePB(HPBar, moyenne);
		
		Location loc = controller.model.getCurrentLocation();
		ImageIcon bgloc  = loc.getBackground();
		g.drawImage(bgloc.getImage(), 0, 0, getWidth(), getHeight(), this);
		
		Weather w = controller.model.getCurrentWeather();
		ImageIcon bgw = w.getBackground(); 
		g.drawImage(bgw.getImage(), 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(Image.time.getImage(), 425, 10, 150, 35, this);
		lblTimeInGame.setText(Integer.toString(controller.model.getTimeInGame()/1000));

		g.drawImage(btnRight.getImage(), 500, 500, 50, 50, this);
		g.drawImage(btnLeft.getImage(), 25, 500, 50, 50, this);
		
		g.drawImage(btnEat.getImage(), 25, 200, 100, 50, this);
		g.drawImage(btnPlay.getImage(), 25, 260, 100, 50, this);
		g.drawImage(btnSleep.getImage(), 25, 320, 100, 50, this);
		g.drawImage(btnWash.getImage(), 25, 380, 100, 50, this);
		g.drawImage(btnWC.getImage(), 25, 440, 100, 50, this);
		g.drawImage(btnPause.getImage(), 475, 50, 50, 50, this);
		
		g.drawImage(btnEatNope.getImage(), 25, 200, 100, 50, this);
		g.drawImage(btnPlayNope.getImage(), 25, 260, 100, 50, this);
		g.drawImage(btnSleepNope.getImage(), 25, 320, 100, 50, this);
		g.drawImage(btnWashNope.getImage(), 25, 380, 100, 50, this);
		g.drawImage(btnWCNope.getImage(), 25, 440, 100, 50, this);
		
		g.drawImage(Image.energy.getImage(), 20, 10, 30, 30, this);
		g.drawImage(Image.food.getImage(), 20, 60, 30, 30, this);
		g.drawImage(Image.health.getImage(), 220, 10, 30, 30, this);
		g.drawImage(Image.happiness.getImage(), 220, 60, 30, 30, this);
		g.drawImage(Image.life.getImage(), 220, 360, 30, 30, this);
		
		g.drawImage(StitchIcon.getImage(), 260, 390, 100, 100, this);
		g.drawImage(AnimeGif.getImage(), 290, 190, 290,170, this);
	}

	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		boolean b=false;
		long currentTime = controller.model.getTimeInGame();
		int delta = 0; 
		if(loc<6 && loc>0) {
			delta = controller.model.tamagostitch.getDelta(loc);
			Location loca = controller.model.getCurrentLocation();
			String locaName=loca.getName();
			String[] locas=controller.model.tamagostitch.getLocas(loc);
			for(String s : locas) {
				if(s.equals(locaName)) {
					b=true;
				}
			}
			if(((currentTime - lastClickTime[loc-1]) >delta || 
					lastClickTime[loc-1]==0) && b && !AnimeEnCours) {
				if(loc==1) {
					controller.eat();
					AnimeGif = Image.EatGif;
				}
				if(loc==2) {
					controller.play();
					AnimeGif = Image.PlayGif;
				}
				if(controller.getStitchType()==3) {
					if(loc==3) {
						controller.sleep();
						AnimeGif = Image.PlugGif;
					}
					if(loc==4) {
						controller.wash();
						AnimeGif = Image.OilGif;
					}
					if(loc==5) {
						controller.wc();
						AnimeGif = Image.FixGif;
					}
				}else {
					if(loc==3) {
						controller.sleep();
						AnimeGif = Image.SleepGif;
					}
					if(loc==4) {
						controller.wash();
						AnimeGif = Image.WashGif;
					}
					if(loc==5) {
						controller.wc();
						AnimeGif = Image.WCGif;
					}
				}
				lastClickTime[loc-1] = currentTime;
				AnimeEnCours=true;
				controller.test(delta,loc);
				
			}
		}

		if(loc==6 && !AnimeEnCours)controller.toLeft();
		if(loc==7 && !AnimeEnCours)controller.toRight();
		if(loc==8)
			{
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
			displayPause(controller.getRunning());
			}

	}
	
	/*
	 * 0=null
	 * 1=eat 
	 * 2=play 
	 * 3=sleep 
	 * 4=wash 
	 * 5=wc 
	 * 6=left 
	 * 7=right 
	 * 8=pause
	 */
	public int checkLocation(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if( ( x>25 && x<125 ) && ( y>200 && y<250) )return 1;
		if( ( x>25 && x<125 ) && ( y>260 && y<310) )return 2;
		if( ( x>25 && x<125 ) && ( y>320 && y<370) )return 3;
		if( ( x>25 && x<125 ) && ( y>380 && y<420) )return 4;
		if( ( x>25 && x<125 ) && ( y>440 && y<490) )return 5;
		if( ( x>25 && x<75 ) && ( y>500 && y<550) )return 6;
		if( ( x>500 && x<550 ) && ( y>500 && y<550) )return 7;
		if( ( x>475 && x<525 ) && ( y>50 && y<100) )return 8;
		return 0;
	}
	
	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		boolean b=false;
		long currentTime = controller.model.getTimeInGame();
		int delta = 0; 
		if(loc<6 && loc>0) {
			delta = controller.model.tamagostitch.getDelta(loc);
			Location loca = controller.model.getCurrentLocation();
			String locaName=loca.getName();
			String[] locas=controller.model.tamagostitch.getLocas(loc);
			for(String s : locas) {
				if(s.equals(locaName)) {
					b=true;
				}
			}
		}
		if(loc==1 && b &&((currentTime - lastClickTime[loc-1]) >delta || 
				lastClickTime[loc-1]==0) && !AnimeEnCours)btnEat=Image.btnEatPressed;
		if(loc==2 && b &&((currentTime - lastClickTime[loc-1]) >delta || 
				lastClickTime[loc-1]==0) && !AnimeEnCours)btnPlay=Image.btnPlayPressed;
		if(loc==3 && b &&((currentTime - lastClickTime[loc-1]) >delta || 
				lastClickTime[loc-1]==0) && !AnimeEnCours) {
			if(controller.getStitchType()==3)btnSleep=Image.btnPlugPressed ;
			else btnSleep=Image.btnSleepPressed;
		}
		if(loc==4 && b &&((currentTime - lastClickTime[loc-1]) >delta || 
				lastClickTime[loc-1]==0) && !AnimeEnCours){
			if(controller.getStitchType()==3)btnWash=Image.btnOilPressed ;
			else btnWash=Image.btnWashPressed;
		}
		if(loc==5 && b &&((currentTime - lastClickTime[loc-1]) >delta || 
				lastClickTime[loc-1]==0) && !AnimeEnCours){
			if(controller.getStitchType()==3)btnWC=Image.btnFixPressed ;
			else btnWC=Image.btnWCPressed;
		}
		if(loc==6 && !AnimeEnCours)btnLeft=Image.btnLeftPressed;
		if(loc==7 && !AnimeEnCours)btnRight=Image.btnRightPressed;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		int loc = checkLocation(e);
		boolean b=false;
		if(loc<6 && loc>0) {
			Location loca = controller.model.getCurrentLocation();
			String locaName=loca.getName();
			String[] locas=controller.model.tamagostitch.getLocas(loc);
			for(String s : locas) {
				if(s.equals(locaName)) {
					b=true;
				}
			}
		}
		if(loc==1 && b && !AnimeEnCours)btnEat=Image.btnEatEnCD;
		if(loc==2 && b && !AnimeEnCours)btnPlay=Image.btnPlayEnCD;
		if(controller.getStitchType()==3) {
			if(loc==3 && b && !AnimeEnCours)btnSleep=Image.btnPlugEnCD;
			if(loc==4 && b && !AnimeEnCours)btnWash=Image.btnOilEnCD;
			if(loc==5 && b && !AnimeEnCours)btnWC=Image.btnFixEnCD;
		}else {
			if(loc==3 && b && !AnimeEnCours)btnSleep=Image.btnSleepEnCD;
			if(loc==4 && b && !AnimeEnCours)btnWash=Image.btnWashEnCD;
			if(loc==5 && b && !AnimeEnCours)btnWC=Image.btnWCEnCD;
		}
		btnLeft=Image.btnLeft;
		btnRight=Image.btnRight;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
