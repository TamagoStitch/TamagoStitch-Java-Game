package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import model.Image;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements MouseListener{
	private Controller controller;
	private ImageIcon btnMenu = Image.btnMenu;
	private static JTextArea txtAreaJ1 = new JTextArea();
	private static JTextArea txtAreaJ2 = new JTextArea();
	private static JTextArea txtAreaJ3 = new JTextArea();
	private static int j1,j2,j3;
	
	public ScorePanel (Controller controller) {
		setBestPlayers();
		this.controller=controller;
		this.addMouseListener(this);
		setLayout(null);
		
		txtAreaJ1.setBounds(250, 520, 120, 40);
		txtAreaJ1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		txtAreaJ1.setEditable(false);
		
		txtAreaJ2.setBounds(110, 520, 120, 40);
		txtAreaJ2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		txtAreaJ2.setEditable(false);
		
		txtAreaJ3.setBounds(400, 520, 120, 40);
		txtAreaJ3.setFont(new Font("Arial Black", Font.PLAIN, 13));
		txtAreaJ3.setEditable(false);
		
		this.add(txtAreaJ1);
		this.add(txtAreaJ2);
		this.add(txtAreaJ3);
		
		txtAreaJ1.setOpaque(false);
		txtAreaJ2.setOpaque(false);
		txtAreaJ3.setOpaque(false);
		
		txtAreaJ1.setForeground(Color.white);
		txtAreaJ2.setForeground(Color.white);
		txtAreaJ3.setForeground(Color.white);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(Image.podium.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(btnMenu.getImage(), 400, 25, 150, 85, this);
		g.drawImage(Image.time.getImage(), 90, 520, 120, 40, this);
		g.drawImage(Image.time.getImage(), 230, 520, 120, 40, this);
		g.drawImage(Image.time.getImage(), 380, 520, 120, 40, this);
		
		if(j1==1) g.drawImage(Image.PinkStitchGif.getImage(),240,280,100,100,this);
		if(j1==2) g.drawImage(Image.BlueStitchGif.getImage(),240,280,100,100,this);
		if(j1==3) g.drawImage(Image.BotStitchGif.getImage(),240,280,100,100,this);
		if(j1==4) g.drawImage(Image.RedStitchGif.getImage(),240,280,100,100,this);

		if(j2==1) g.drawImage(Image.PinkStitchGif.getImage(),100,330,100,100,this);
		if(j2==2) g.drawImage(Image.BlueStitchGif.getImage(),100,330,100,100,this);
		if(j2==3) g.drawImage(Image.BotStitchGif.getImage(),100,330,100,100,this);
		if(j2==4) g.drawImage(Image.RedStitchGif.getImage(),100,330,100,100,this);

		if(j3==1) g.drawImage(Image.PinkStitchGif.getImage(),380,340,100,100,this);
		if(j3==2) g.drawImage(Image.BlueStitchGif.getImage(),380,340,100,100,this);
		if(j3==3) g.drawImage(Image.BotStitchGif.getImage(),380,340,100,100,this);
		if(j3==4) g.drawImage(Image.RedStitchGif.getImage(),380,340,100,100,this);


	}
	
	public static void setBestPlayers() {
		
	    try {
	    	HashMap<Integer, String> hmap = new HashMap<Integer,String>();
	    	HashMap<Integer, String> hmap2 = new HashMap<Integer,String>();
	    	HashMap<Integer, String> hmap3 = new HashMap<Integer,String>();
	    	ArrayList<Integer>val=new ArrayList<Integer>();
	    	
			FileReader fr = new FileReader("./score/score.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String l;
			while((l=br.readLine())!=null){
				String[] current = l.split(":");
				if (current.length==3) {
					if(!hmap.containsKey(Integer.parseInt(current[1]))) {
						hmap.put(Integer.parseInt(current[1]),current[0]+current[2]);
					}else if (!hmap2.containsKey(Integer.parseInt(current[1]))) {
						hmap2.put(Integer.parseInt(current[1]),current[0]+current[2]);
					}else {
						hmap3.put(Integer.parseInt(current[1]),current[0]+current[2]);
					}
					val.add(Integer.parseInt(current[1]));
				}
			}
			br.close();
			
			Collections.sort(val);
			int index = val.size();
			
			if (index==1) {
				txtAreaJ1.setText(hmap.get(val.get(index-1)).substring(0,hmap.get(val.get(index-1)).length()-1)+"\n"+val.get(index-1)+" pts");
				j1=Integer.parseInt(hmap.get(val.get(index-1)).substring(hmap.get(val.get(index-1)).length()-1));
			}
			else if (index==2) {
				txtAreaJ1.setText(hmap.get(val.get(index-1)).substring(0,hmap.get(val.get(index-1)).length()-1)+"\n"+val.get(index-1)+" pts");
				j1=Integer.parseInt(hmap.get(val.get(index-1)).substring(hmap.get(val.get(index-1)).length()-1));
				hmap.remove(val.get(index-1));
				if (hmap.containsKey(val.get(index-2))){
					txtAreaJ2.setText(hmap.get(val.get(index-2)).substring(0,hmap.get(val.get(index-2)).length()-1)+"\n"+val.get(index-2)+" pts");
					j2=Integer.parseInt(hmap.get(val.get(index-2)).substring(hmap.get(val.get(index-2)).length()-1));
				}else {
					txtAreaJ2.setText(hmap2.get(val.get(index-2)).substring(0,hmap2.get(val.get(index-2)).length()-1)+"\n"+val.get(index-2)+" pts");
					j2=Integer.parseInt(hmap2.get(val.get(index-2)).substring(hmap2.get(val.get(index-2)).length()-1));
				}
			}
			else if(index>2) {
				txtAreaJ1.setText(hmap.get(val.get(index-1)).substring(0,hmap.get(val.get(index-1)).length()-1)+"\n"+val.get(index-1)+" pts");
				j1=Integer.parseInt(hmap.get(val.get(index-1)).substring(hmap.get(val.get(index-1)).length()-1));
				hmap.remove(val.get(index-1));
				
				if (hmap.containsKey(val.get(index-2))){
					txtAreaJ2.setText(hmap.get(val.get(index-2)).substring(0,hmap.get(val.get(index-2)).length()-1)+"\n"+val.get(index-2)+" pts");
					j2=Integer.parseInt(hmap.get(val.get(index-2)).substring(hmap.get(val.get(index-2)).length()-1));
					hmap.remove(val.get(index-2));
				}else {
					txtAreaJ2.setText(hmap2.get(val.get(index-2)).substring(0,hmap2.get(val.get(index-2)).length()-1)+"\n"+val.get(index-2)+" pts");
					j2=Integer.parseInt(hmap2.get(val.get(index-2)).substring(hmap2.get(val.get(index-2)).length()-1));
					hmap2.remove(val.get(index-2));
				}
				
				
				if(hmap.containsKey(val.get(index-3))) {
					txtAreaJ3.setText(hmap.get(val.get(index-3)).substring(0,hmap.get(val.get(index-3)).length()-1)+"\n"+val.get(index-3)+" pts");
					j3=Integer.parseInt(hmap.get(val.get(index-3)).substring(hmap.get(val.get(index-3)).length()-1));
					hmap.remove(val.get(index-3));
				}else if(hmap2.containsKey(val.get(index-3))) {
					txtAreaJ3.setText(hmap2.get(val.get(index-3)).substring(0,hmap2.get(val.get(index-3)).length()-1)+"\n"+val.get(index-3)+" pts");
					j3=Integer.parseInt(hmap2.get(val.get(index-3)).substring(hmap2.get(val.get(index-3)).length()-1));
					hmap2.remove(val.get(index-2));
				}else {
					txtAreaJ3.setText(hmap3.get(val.get(index-3)).substring(0,hmap3.get(val.get(index-3)).length()-1)+"\n"+val.get(index-3)+" pts");
					j3=Integer.parseInt(hmap3.get(val.get(index-3)).substring(hmap3.get(val.get(index-3)).length()-1));
					hmap3.remove(val.get(index-2));
				}
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1)
			try {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				controller.toMenuPanel();
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
				// TODO Auto-generated catch block
				
			}
	}
	
	/*
	 * 	0=null
	 * 	1=menu
	 */
	public int checkLocation(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if((x>400 && x<550)&&(y>25 && y<110))return 1;
		return 0;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1)btnMenu=Image.btnMenuPressed;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		btnMenu=Image.btnMenu;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}