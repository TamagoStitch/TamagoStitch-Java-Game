package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import model.Image;

public class Create extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private int type=2;//1=pink, 2=blue, 3=bot, 4=red
	private JTextField txtname = new JTextField();
	
	private ImageIcon btnBack = Image.btnBack;
	private ImageIcon btnStart = Image.btnStart;
	
	private ImageIcon PinkStitch = Image.PinkStitch;
	private ImageIcon BlueStitch = Image.BlueStitchSelected;
	private ImageIcon BotStitch = Image.BotStitch;
	private ImageIcon RedStitch = Image.RedStitch;
	
	private String[] forbiddenWord = {	"adolf","hitler","asiate","boche","chicano","enculeur","femmelette",
										"gogol","goudou","gouine","lope","lopette","nabot","negre","negresse",
										"negrillon","pede","poufiasse","romano","schleu","sidaÃ¯que","tafiole",
										"tantouse","tantouze","tarlouse","tarlouze","travelo","pd","encule",
										"merde","abruti","pute","salope","tchoin","adolf hitler","zine al abdine ben ali",
										"mouammar kadhafi","oussama ben laden","kouachi","mohammed merah","saddam hussein",
										"gerald darmanin","pouffe","cul","bite","enfoire","fuck","bitch","negro",
										"zguege","pedo","videcouille","trainee","tafiole","sousmerde","salop",
										"saloperie","salopard","putin","putain","pourriture","pouffiasse","petasse",
										"pedale","pequenaud","michto","merdeux","mauviette","fiotte","foutre",
										"feignasse","enfoire","ducon","crevard","crevure","couille","conasse",
										"chintok","chienne","chier","branler","bougnoule","bouffon","avorton","salaud","schnoke"}; 
	
	private ArrayList<String> existingPlayers;
	public Create(Controller controller) {
		
		this.controller = controller;
		this.addMouseListener(this);
		setLayout(null);
		txtname.setFont(new Font("Arial Black", Font.PLAIN, 15));
		txtname.setBounds(250, 160, 150, 22);
		txtname.setMaximumSize(new Dimension(100, 20));
		txtname.setAlignmentX(CENTER_ALIGNMENT);
		this.add(txtname);
		repaint();
	}
	
	public String getStitchName() {
		return txtname.getText();
	}
	
	public int getType() {
		return type;
	}
	
	public boolean checkNameIfNotForbidden() {
		String name = txtname.getText();
		for(String s : forbiddenWord) {
			if(name.contains(s)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkNameIfEmpty() {
		return (txtname.getText().length()==0) ? true : false;
	}
	
	public boolean checkNameLength() {
		return (txtname.getText().length()<11) ? true : false;
	}
	
	public boolean checkNameCharacter(String s) {
		for(char c : s.toCharArray()) {
			if(!Character.isDigit(c) && !Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkNameExist() {
		String name = txtname.getText();
		for(String s : existingPlayers) {
			if(name.equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	public void setExistingPlayers() {
		File f = new File("./save");
		String [] sf = f.list();
		existingPlayers = new ArrayList <String>();  
		try {
			for(String name : sf) {
				String [] s = name.split("\\.");
				existingPlayers.add(s[0].trim());
			}
		}
		catch(NullPointerException e) {
			
		}
	}
	@SuppressWarnings("static-access")
	public boolean checkSave() {
		if(controller.view.loadPanel.getPlayerName().size()<6) {
			return false;
		}
		return true;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(Image.create.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(Image.createCharacter.getImage(), 50, 50, 500, 50, this);
		g.drawImage(Image.name.getImage(), 170, 150, 250, 40, this);
		
		g.drawImage(btnBack.getImage(), 30, 440, 150, 85, this);
		g.drawImage(btnStart.getImage(), 380, 420, 175, 115, this);
		
		g.drawImage(RedStitch.getImage(), 425, 200, 150, 150, this);
		g.drawImage(BotStitch.getImage(), 280, 200, 150, 150, this);
		g.drawImage(BlueStitch.getImage(), 140, 200, 150, 150, this);
		g.drawImage(PinkStitch.getImage(), 0, 200, 150, 150, this);
	}

	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		Menu.buttonSound.setFramePosition(0);
		Menu.buttonSound.start();
		if(loc==1) {PinkStitch=Image.PinkStitchSelected;
					BlueStitch=Image.BlueStitch;
					BotStitch=Image.BotStitch;
					RedStitch=Image.RedStitch;
					type=1;}
		if(loc==2) {PinkStitch=Image.PinkStitch;
					BlueStitch=Image.BlueStitchSelected;
					BotStitch=Image.BotStitch;
					RedStitch=Image.RedStitch;type=2;}
		if(loc==3) {PinkStitch=Image.PinkStitch;
					BlueStitch=Image.BlueStitch;
					BotStitch=Image.BotStitchSelected;
					RedStitch=Image.RedStitch;type=3;}
		if(loc==4) {PinkStitch=Image.PinkStitch;
					BlueStitch=Image.BlueStitch;
					BotStitch=Image.BotStitch;
					RedStitch=Image.RedStitchSelected;type=4;}
		if(loc==5)
			try {
				Menu.buttonSound.setFramePosition(0);
				Menu.buttonSound.start();
				controller.toMenuPanel();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(loc==6) 
		{			
					Menu.buttonSound.setFramePosition(0);
					Menu.buttonSound.start();
					setExistingPlayers();
					if(!checkNameIfNotForbidden()) { 
						JOptionPane.showMessageDialog(null, "Forbidden name", "Error", JOptionPane.ERROR_MESSAGE);					
					}
					else if(checkNameIfEmpty()){
						JOptionPane.showMessageDialog(null, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!checkNameLength()){
						JOptionPane.showMessageDialog(null, "Name is too long, please enter another name", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!checkNameCharacter(txtname.getText())) {
						JOptionPane.showMessageDialog(null, "Innapropriate character", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!checkNameExist()) {
						JOptionPane.showMessageDialog(null, "Name already taken", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(checkSave()) {
						JOptionPane.showMessageDialog(null, "Maximum save reach, please delete or end an already existing one ", "Error", JOptionPane.ERROR_MESSAGE);
						View.loadPanel = new LoadPanel(controller);
						controller.toLoadPanel();
					}
					else { 
						controller.toGamePanel();
						
					}
		}
		repaint();
	}
	
	/*
	 * 	0 si null
	 * 	1 si pink
	 * 	2 si blue 
	 * 	3 si bot
	 * 	4 si red
	 * 	5 si back
	 * 	6 si start
	 */
	public int checkLocation(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(	(x>0 && x<150) && (y>200 && y<350) ) return 1;
		else if( (x>125 && x<275) && (y>200 && y<350) ) return 2;
		else if( (x>275 && x<425) && (y>200 && y<350) ) return 3;
		else if( (x>425 && x<575) && (y>200 && y<350) ) return 4;
		else if( (x>30 && x<180) && (y>440 && y<525) ) return 5;
		else if( (x>380 && x<555) && (y>420 && y<535) )return 6;
		return 0;
	}

	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==5) btnBack=Image.btnBackPressed;
		if(loc==6) btnStart=Image.btnStartPressed;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		btnBack=Image.btnBack;
		btnStart=Image.btnStart;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}