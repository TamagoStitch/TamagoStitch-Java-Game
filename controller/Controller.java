package controller;



import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import model.BlueStitch;
import model.BotStitch;
import model.Image;
import model.Model;
import model.PinkStitch;
import model.RedStitch;
import model.TamagoStitch;
import model.Weather;
import view.LoadPanel;
import view.View;



import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Controller {
	
	public Model model;
	public View view;
	private boolean running;
	private final int sleepTime = 200; //temps en ms entre chaque update
	private int[] cd= {0,0,0,0,0};
	private int[] timeA= {3000,3000,3000,3000,3000};
	private int cdA;
	private int typeStitchCharger;
	public Clip gameSound;
	
    public Action toGamePanelFromLoad= new AbstractAction() {
		private static final long serialVersionUID = 1L;
        @SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
            String name=e.getActionCommand();
            Load charger = new Load(name, model);
            TamagoStitch loadedStitch=null;

            try {
                loadedStitch = charger.chargeStitch();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            loadedStitch.setName(name);
            
            view.setContentPane(view.gamePanel);
            view.revalidate();
            
            view.menuPanel.menuSound.close();
            startGame(loadedStitch);
        }
    };
	
	public Controller() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		model = new Model(this);
		view = new View(this);
	}
	
	//____________________Action des boutons____________________
	
	public void toCreatePanel() {
		view.setContentPane(view.createPanel);
		view.revalidate();
	}
	
	@SuppressWarnings("static-access")
	public void toLoadPanel() {
        view.setContentPane(view.loadPanel);
        view.revalidate();        
	}
	
	@SuppressWarnings("static-access")
	public void toMenuPanel() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		view.menuPanel.menuSound.start();
		view.setContentPane(view.menuPanel);
		view.revalidate();
		running=false;
	}
	
	public void toGamePanel() {
		view.setContentPane(view.gamePanel);
		view.revalidate();
		startGame();
	}
	
	@SuppressWarnings("static-access")
	public void toScorePanel() {
		view.menuPanel.menuSound.start();
		view.setContentPane(view.scorePanel);
		view.revalidate();
	}
	
	public void test(int i,int l) { //TODO
		cd[l-1]=model.getTimeInGame()+i;
		cdA=model.getTimeInGame()+(timeA[l-1]);
		
	}
	
	public void makeTrans(int i) {
		if(i==1)view.gamePanel.setBtnEatN(Image.FondTrans);
		if(i==2)view.gamePanel.setBtnPlayN(Image.FondTrans);
		if(i==3)view.gamePanel.setBtnSleepN(Image.FondTrans);
		if(i==4)view.gamePanel.setBtnWashN(Image.FondTrans);
		if(i==5)view.gamePanel.setBtnWCN(Image.FondTrans);
	}
	
	public void actionNope(String name) { //PROB TODO
		int i=1;
		view.gamePanel.setBtnEatN(Image.btnEatEnCD);
		view.gamePanel.setBtnPlayN(Image.btnPlayEnCD);
		if(typeStitchCharger==3) {
			view.gamePanel.setBtnSleepN(Image.btnPlugEnCD);
			view.gamePanel.setBtnWashN(Image.btnOilEnCD);
			view.gamePanel.setBtnWCN(Image.btnFixEnCD);
			
		}else {
		view.gamePanel.setBtnSleepN(Image.btnSleepEnCD);
		view.gamePanel.setBtnWashN(Image.btnWashEnCD);
		view.gamePanel.setBtnWCN(Image.btnWCEnCD);
		}
		for(String[] s : model.tamagostitch.getFullLocas()) {
			for(String ss : s) {
				if(ss.equals(name)) {
					makeTrans(i);
				}
			}
			i++;
		}
		
	}
	public void eat() {
    	model.tamagostitch.eat(model.getCurrentLocation(), model.getCurrentWeather());
	}
	
	public void play() {
    	model.tamagostitch.play(model.getCurrentLocation(), model.getCurrentWeather());
	}
	
	public void sleep() {
    	model.tamagostitch.sleep(model.getCurrentLocation(), model.getCurrentWeather());
	}
	
	public void wash() {
    	model.tamagostitch.wash(model.getCurrentLocation(), model.getCurrentWeather());
	}
	
	public void wc() {
    	model.tamagostitch.wc(model.getCurrentLocation(), model.getCurrentWeather());
	}
	
	public void toLeft() {
		if(model.getCurrentLocation().getName().equals("cabane")) {
			model.setCurrentLocation(model.locations[0]);
			actionNope(model.getCurrentLocation().getName());
		}else if(model.getCurrentLocation().getName().equals("foret")) {
			model.setCurrentLocation(model.locations[1]);
			actionNope(model.getCurrentLocation().getName());
		}else if(model.getCurrentLocation().getName().equals("village")) {
			model.setCurrentLocation(model.locations[2]);
			actionNope(model.getCurrentLocation().getName());
		}
	}
	
	public void toRight() {
		if(model.getCurrentLocation().getName().equals("plage")) {
			model.setCurrentLocation(model.locations[1]);
			actionNope(model.getCurrentLocation().getName());
		}else if(model.getCurrentLocation().getName().equals("cabane")) {
			model.setCurrentLocation(model.locations[2]);
			actionNope(model.getCurrentLocation().getName());
		}else if(model.getCurrentLocation().getName().equals("foret")) {
			model.setCurrentLocation(model.locations[3]);
			actionNope(model.getCurrentLocation().getName());
		}
	}
	@SuppressWarnings("static-access")
	public void autoSave() {
		if(view.loadPanel.getPlayerName().size()<8) {
	    	Save.save(model);
			view.loadPanel = new LoadPanel(this);
		}
	}
	
	@SuppressWarnings("static-access")
	public void saveAndQuit() {
    	Save.save(model);
		view.loadPanel = new LoadPanel(this);
    	try {
			toMenuPanel();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void toGamePanelFromLoad(String name) {
        Load charger = new Load(name, model);
        TamagoStitch loadedStitch=null;
        try {
            loadedStitch = charger.chargeStitch();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        loadedStitch.setName(name);
        view.setContentPane(view.gamePanel);
        view.revalidate();

        startGame(loadedStitch);

    }

    public void deletePlayer(String name) {
        File save = new File("./save/"+name+".txt");
        save.delete();
        View.loadPanel = new LoadPanel(this);
        this.toLoadPanel();
        
    }
	
	public HashMap<String, Integer> getSavePlayer(String player) {
		return Save.getSave(player);
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running=running;
	}
	
	public int getStitchType() {
        return typeStitchCharger;
    }
	
	public void refresh(int i) { 
		view.gamePanel.setBtnEat(Image.btnEat);
	    view.gamePanel.setBtnPlay(Image.btnPlay);
	    if(i==3) {
	    	view.gamePanel.setBtnSleep(Image.btnPlug);
			view.gamePanel.setBtnWC(Image.btnFix);
			view.gamePanel.setBtnWash(Image.btnOil);
	    }else {
	    view.gamePanel.setBtnSleep(Image.btnSleep);
	    view.gamePanel.setBtnWash(Image.btnWash);
	    view.gamePanel.setBtnWC(Image.btnWC);
	    }
	    view.gamePanel.setLastClickTime(0);
	    view.gamePanel.setAnimeGif(Image.FondTrans);
  		view.gamePanel.setBoolA(false);
	}
	
	@SuppressWarnings("static-access")
	public void startGame() {
		view.menuPanel.menuSound.stop();
		model.setTimeInGame(0);
		String name = view.createPanel.getStitchName();
		int type = view.createPanel.getType();
		typeStitchCharger=type;
		refresh(type);
	    if(type == 1) {
	    	model.setStitch(new PinkStitch(name));view.gamePanel.setStitchIcon(Image.PinkStitchGif);
			view.gamePanel.gameSound.setMicrosecondPosition(0);
	    	view.gamePanel.gameSound.loop(Clip.LOOP_CONTINUOUSLY);
		}
	    else if(type==2) {
	    	model.setStitch(new BlueStitch(name));view.gamePanel.setStitchIcon(Image.BlueStitchGif);
			view.gamePanel.gameSound.setMicrosecondPosition(0);
			view.gamePanel.gameSound.loop(Clip.LOOP_CONTINUOUSLY);
	    }
	    else if(type==3) {
	    	model.setStitch(new BotStitch(name));view.gamePanel.setStitchIcon(Image.BotStitchGif);
			view.gamePanel.botSound.setMicrosecondPosition(0);
			view.gamePanel.botSound.loop(Clip.LOOP_CONTINUOUSLY);
	    }
	    else if(type==4) {
	    	model.setStitch(new RedStitch(name));view.gamePanel.setStitchIcon(Image.RedStitchGif);
	    	view.gamePanel.madSound.setMicrosecondPosition(0);
	    	view.gamePanel.madSound.loop(Clip.LOOP_CONTINUOUSLY);
	    }
		
		running=true;
		model.setCurrentLocation(model.locations[1]);
		communStartGame();
		actionNope(model.getCurrentLocation().getName());
		updateWeather();
		
        new Thread(new myRunnable()).start();
	}
	
	@SuppressWarnings("static-access")
	public void startGame(TamagoStitch stitch) {
		view.menuPanel.menuSound.stop();
        model.setStitch(stitch);
        model.setTimeInGame(stitch.getAge());
        
        running=true;
        int type = stitch.getType();
        typeStitchCharger=type;
        refresh(type);
        if(type==1) {
        	view.gamePanel.setStitchIcon(Image.PinkStitchGif);
        	view.gamePanel.gameSound.setMicrosecondPosition(0);
	    	view.gamePanel.gameSound.loop(Clip.LOOP_CONTINUOUSLY);     
        }
        else if(type==2) {
        	view.gamePanel.setStitchIcon(Image.BlueStitchGif);
        	view.gamePanel.gameSound.setMicrosecondPosition(0);
	    	view.gamePanel.gameSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else if(type==3) {
        	view.gamePanel.setStitchIcon(Image.BotStitchGif);
			view.gamePanel.botSound.setMicrosecondPosition(0);
			view.gamePanel.botSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else if(type==4) {
        	view.gamePanel.setStitchIcon(Image.RedStitchGif);  
        	view.gamePanel.madSound.setMicrosecondPosition(0);
	    	view.gamePanel.madSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
		communStartGame();
		actionNope(model.getCurrentLocation().getName());
        new Thread(new myRunnable()).start();
    }
	
	public void communStartGame() {
		view.gamePanel.pause.setVisible(false);
		view.gamePanel.HPBar.setVisible(true);
		view.gamePanel.energyBar.setVisible(true);
		view.gamePanel.foodBar.setVisible(true);
		view.gamePanel.healthBar.setVisible(true);
		view.gamePanel.happinessBar.setVisible(true);
	}
	
	public void pause() {
		if(running) {
			running=false;
			view.gamePanel.gameSound.stop();
			view.gamePanel.botSound.stop();
			view.gamePanel.madSound.stop();
			view.gamePanel.rainSound.stop();
			view.gamePanel.stormSound.stop();
		}else {
			running = true;
			if(model.tamagostitch.getType()==1||model.tamagostitch.getType()==2) {
				view.gamePanel.gameSound.start();
			}
			if(model.tamagostitch.getType()==3) {
				view.gamePanel.botSound.start();
			}
			if(model.tamagostitch.getType()==4) {
				view.gamePanel.madSound.start();
			}
			if(model.getCurrentWeather().getName().equals("rain"))view.gamePanel.rainSound.start();
			if(model.getCurrentWeather().getName().equals("storm"))view.gamePanel.stormSound.start();
			new Thread(new myRunnable()).start();
		}
	}
	
	//Met a jour le bar de progression du Stitch 
	public void update() {
		if(model.getCurrentWeather().getName().equals("storm")) {
			view.gamePanel.rainSound.stop();
			view.gamePanel.stormSound.start();
		}
		if(model.getCurrentWeather().getName().equals("rain")) {
			view.gamePanel.stormSound.stop();
			view.gamePanel.rainSound.start();
		}
		if(model.getCurrentWeather().getName().equals("sunny")) {
			view.gamePanel.rainSound.stop();
			view.gamePanel.stormSound.stop();
		}
		if(model.tamagostitch.getHp()>0) {
			model.tamagostitch.setEnergy(-model.tamagostitch.getLostEnergy());
			model.tamagostitch.setFood(-model.tamagostitch.getLostFood());
			model.tamagostitch.setHealth(-model.tamagostitch.getLostHealth());
			model.tamagostitch.setHappiness(-model.tamagostitch.getLostHappiness());
			if(model.tamagostitch.getHealth()==0 || model.tamagostitch.getEnergy()==0 || model.tamagostitch.getFood()==0 || model.tamagostitch.getHappiness()==0) {
                model.tamagostitch.setEnergy(-1);
                model.tamagostitch.setFood(-1);
                model.tamagostitch.setHealth(-1);
                model.tamagostitch.setHappiness(-1);
            }
			int moyenne = 	(model.tamagostitch.getEnergy()+
							model.tamagostitch.getFood()+
							model.tamagostitch.getHealth()+
							model.tamagostitch.getHappiness())/4;
			model.tamagostitch.setHP(moyenne);
			
			//sauvegarder tte les minutes
          	if(model.getTimeInGame()%60000==0) {
        		autoSave();
        		updateWeather();
        		if(model.getCurrentWeather().getName().equals("storm")) {
        			view.gamePanel.rainSound.stop();
        			view.gamePanel.stormSound.setMicrosecondPosition(0);
        			view.gamePanel.stormSound.loop(Clip.LOOP_CONTINUOUSLY);
        		}
        		if(model.getCurrentWeather().getName().equals("rain")) {
        			view.gamePanel.stormSound.stop();
        			view.gamePanel.rainSound.setMicrosecondPosition(0);
        			view.gamePanel.rainSound.loop(Clip.LOOP_CONTINUOUSLY);
        		}
        		if(model.getCurrentWeather().getName().equals("sunny")) {
        			view.gamePanel.rainSound.stop();
        			view.gamePanel.stormSound.stop();
        		}
        	}
          	
          	if(model.getTimeInGame()>=cd[0])view.gamePanel.setBtnEat(Image.btnEat);
          	if(model.getTimeInGame()>=cd[1])view.gamePanel.setBtnPlay(Image.btnPlay);
          	if(typeStitchCharger==3) {
          		if(model.getTimeInGame()>=cd[2])view.gamePanel.setBtnSleep(Image.btnPlug);
	          	if(model.getTimeInGame()>=cd[3])view.gamePanel.setBtnWash(Image.btnOil);
	          	if(model.getTimeInGame()>=cd[4])view.gamePanel.setBtnWC(Image.btnFix);
          	}else {
	          	if(model.getTimeInGame()>=cd[2])view.gamePanel.setBtnSleep(Image.btnSleep);
	          	if(model.getTimeInGame()>=cd[3])view.gamePanel.setBtnWash(Image.btnWash);
	          	if(model.getTimeInGame()>=cd[4])view.gamePanel.setBtnWC(Image.btnWC);
          	}
          	if(model.getTimeInGame()>=cdA) {
          		view.gamePanel.setAnimeGif(Image.FondTrans);
          		view.gamePanel.setBoolA(false);
          	}
          	
		}else {
			gameOver();
			File save = new File("./save/"+model.tamagostitch.getName()+".txt");
	        save.delete();
			return;
		}
    	model.setTimeInGame(model.getTimeInGame()+sleepTime);
	}
	
	public void gameOver() {
		view.gamePanel.gameSound.stop();
		view.gamePanel.botSound.stop();
		view.gamePanel.madSound.stop();
		view.gamePanel.rainSound.stop();
		view.gamePanel.stormSound.stop();
		view.overPanel.overSound.setMicrosecondPosition(0);
		view.overPanel.overSound.start();
        view.repaint();
		try {
			Score.saveScore(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
		running=false;
		view.setContentPane(view.overPanel);
		view.revalidate();	
	}
	public void updateWeather(){
        int alea = new Random().nextInt(100)+1;
        for(Weather w : model.weathers) {
        	if(alea<=w.getRate()) {
        		model.setCurrentWeather(w);
        		break;
        	}
        }
    }
	/*
	 * Boucle du temps qui update les information du stitch et repaint 
	 * les visuelle comme le background
	 */
	private class myRunnable implements Runnable {
        public void run() {
            while (running) {
                update();
                view.gamePanel.repaint();
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
	}
}
