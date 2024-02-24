package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import model.Image;

@SuppressWarnings("serial")
public class Rules extends JPanel implements MouseListener{
	
	@SuppressWarnings("unused")
	private Controller controller;
	private Pause pause; 
	private ImageIcon btnBack = Image.btnBack;
	ImageIcon btnLang = Image.french;
	
	private int type;
	
	public JTextArea text = new JTextArea();
	public String FRpinkText = "Bienvenue à Hawaï !\r\n"
			+ "Nous te confions une mission, faire survivre ton Stitch le plus longtemps possible.\n"
			+ "Tu joues actuellement le Stitch Rose, il correspond au premier degré de difficulté de ce jeu.\n"
			+ "Pour faire survivre ta créature bien-aimée, tu peux effectuer diverses actions comme \n"
			+ "manger, dormir, jouer, te laver, ou faire tes besoins.\r\n"
			+ "Attention, selon les lieux et la météo seules certaines actions sont possibles !\n"
			+ "Prends garde dans ton choix d'actions, effectuer une action peut augmenter certaines \n"
			+ "statistiques, mais il en fait diminuer d'autres !\r\n"
			+ "Effectuer une action prend du temps et a un temps de récupération.\n"
			+ "La partie s'arrête lorsque ta créature ne possède plus de vie.\n"
			+ "Le score est calculé selon ton temps en jeu.\n"
			+ "Tu peux à tout moment sauvegarder ou quitter pour lancer une autre partie\n"
			+ "avec un niveau de difficulté plus élevé si tu te sens prêt(e).\n"
			+ "L'équipe de Tamago'Stitch te remercie de jouer à notre projet.\n"
			+ "Nous te souhaitons un bon jeu et surtout ... prends garde à la tempête !";
	
	public String ENpinkText = "Welcome in Hawaï ! \r\n"
			+ "We entrust you with a mission, make sure to keep alive your Stitch as long as possible.\r\n"
			+ "You are currently playing Pink Stitch, it's to the first level of difficulty of this game.\r\n"
			+ "To keep your beloved creature alive, you can do some actions such as :\n"
			+ "eating, sleeping, playing, washing or going to wc.\r\n"
			+ "Be careful, depending of the location and the weather, only few actions are possibles !\r\n"
			+ "Take care in your choices, do something can increase some statistics.\n"
			+ "It can decrease others.\r\n"
			+ "Performing an action takes time, and has a cooldown.\r\n"
			+ "Game is over when your Stitch has no life anymore.\r\n"
			+ "Score is calculated based on your game time.\r\n"
			+ "You can at any moment save or quit at any time, maybe to start another game with \n"
			+ "harder difficulty if you feel ready.\r\n"
			+ "Tamago'Stitch's team thanks you for playing our project, wishes you good game \n"
			+ "and above all... watch out for the storm !";
	
	public String FRblueText = "Bienvenue à Hawaï !\r\n"
			+ "Nous te confions une mission, faire survivre ton Stitch le plus longtemps possible.\r\n"
			+ "Tu joues actuellement le Stitch Bleu, il correspond au second degré de difficulté de ce jeu.\r\n"
			+ "Pour faire survivre ta créature bien-aimée, tu peux effectuer diverses actions comme \n"
			+ "manger, dormir, jouer, te laver, ou faire tes besoins.\r\n"
			+ "Attention, selon les lieux et la météo seules certaines actions sont possibles !\r\n"
			+ "Prends garde dans ton choix d'actions, effectuer une action peut augmenter certaines\n"
			+ "statistiques, mais il en fait diminuer d'autres !\r\n"
			+ "Effectuer une action prend du temps et a un temps de récupération.\r\n"
			+ "La partie s'arrête lorsque ta créature ne possède plus de vie.\r\n"
			+ "Le score est calculé selon ton temps en jeu.\r\n"
			+ "Tu peux à tout moment sauvegarder ou quitter pour lancer une autre partie avec un \n"
			+ "niveau de difficulté plus aisé ou plus élevé si tu te sens prêt(e).\r\n"
			+ "L'équipe de Tamago'Stitch te remercie de jouer à notre projet et te souhaite un bon jeu.\n"
			+ "Et surtout ... prends garde à la tempête !";
	
	public String ENblueText = "Welcome in Hawaï ! \r\n"
			+ "We entrust you with a mission, make sure to keep alive your Stitch as long as possible.\r\n"
			+ "You are currently playing Blue Stitch, it's the second level of difficulty of this game.\r\n"
			+ "To keep your beloved creature alive, you can do some actions such as :\n"
			+ "eating, sleeping, playing, washing or going to wc.\r\n"
			+ "Be careful, depending of the location and the weather, only few actions are possibles !\r\n"
			+ "Take care in your choices, do something can increase some statistics. \n"
			+ "It can decrease others !\r\n"
			+ "Performing an action takes time, and has a cooldown.\r\n"
			+ "Game is over when your Stitch has no life anymore.\r\n"
			+ "Score is calculated based on your game time.\r\n"
			+ "You can at any moment save or quit at any time, maybe to start another game with \n"
			+ "easier or harder difficulty if you feel ready.\r\n"
			+ "Tamago'Stitch's team thanks you for playing our projet, wishes you good game \n"
			+ "and above all... watch out for the storm !";
	
	public String FRbotText = "Bienvenue à Hawaï !\r\n"
			+ "Nous te confions une mission, faire survivre ton Robot le plus longtemps possible.\r\n"
			+ "Attention ! Il correspond au troisième degré de difficulté de ce jeu.\r\n"
			+ "Tu peux effectuer diverses actions comme manger, charger, jouer, te huiler, ou te réparer.\r\n"
			+ "Attention, selon les lieux et la météo seules certaines actions sont possibles !\r\n"
			+ "Prends garde dans ton choix d'actions, effectuer une action peut augmenter certaines \n"
			+ "statistiques, mais il en fait diminuer d'autres !\r\n"
			+ "Effectuer une action prend du temps et a un temps de récupération.\r\n"
			+ "Tu le remarqueras assez vite, le robot peut parfois s'avérer compliqué à maintenir chargé...\r\n"
			+ "La partie s'arrête lorsque ton robot ne possède plus de vie.\r\n"
			+ "Le score est calculé selon ton temps en jeu.\r\n"
			+ "Tu peux à tout moment sauvegarder ou quitter pour lancer une autre partie avec un \n"
			+ "niveau de difficulté plus aisé ou plus élevé si tu te sens prêt(e).\r\n"
			+ "L'équipe de Tamago'Stitch te remercie de jouer à notre projet et te souhaite un bon jeu.\n"
			+ "Et surtout ... prends garde à la tempête !";
	
	public String ENbotText = "Welcome in Hawaï ! \r\n"
			+ "We entrust you with a mission, make sure to keep alive your Stitch as long as possible.\r\n"
			+ "You are currently playing Bot Stitch. Be carefull, it's the third level of difficulty of this game.\r\n"
			+ "To keep your beloved robot alive, you can do some actions such as :\n"
			+ "eating, playing, plugging, oiling or fixing.\r\n"
			+ "Be careful, depending of the location and the weather, only few actions are possibles !\r\n"
			+ "Take care in your choices, do something can increase some statistics.\n"
			+ "It can decrease others.\r\n"
			+ "Performing an action takes time, and has a cooldown.\r\n"
			+ "You will notice it soon enough, robot can sometimes be complicated to keep charged...\r\n"
			+ "Game is over when your Stitch has no life anymore.\r\n"
			+ "You can at any moment save or quit at any time, maybe to start another game with \n"
			+ "harder difficulty if you feed ready.\r\n"
			+ "Tamago'Stitch's team thanks you for playing our projet, wishes you good game \n"
			+ "and above all... watch out for the storm !";
	
	public String FRredText = "Bienvenue à Hawaï !\r\n"
			+ "Nous te confions une mission, faire survivre ton Stitch le plus longtemps possible.\r\n"
			+ "Tu joues actuellement le Stitch Malveillant, oublies tout ce que tu as appris(e) jusqu'a \n"
			+ "maintenant... il correspond au dernier degré de difficulté de ce jeu.\r\n"
			+ "Tu peux effectuer diverses actions : manger, dormir, jouer, te laver, ou faire tes besoins.\r\n"
			+ "Attention, selon les lieux et la météo seules certaines actions sont possibles !\r\n"
			+ "Prends garde dans ton choix d'actions, effectuer une action peut augmenter certaines \n"
			+ "statistiques, mais il en fait diminuer grandement d'autres !\r\n"
			+ "Effectuer une action prend du temps et a un temps de récupération.\r\n"
			+ "La partie s'arrête lorsque ta créature ne possède plus de vie.\r\n"
			+ "Le score est calculé selon ton temps en jeu.\r\n"
			+ "Tu peux à tout moment sauvegarder ou quitter pour lancer une autre partie avec un \n"
			+ "niveau de difficulté plus aisé ou plus élevé si tu te sens prêt(e).\r\n"
			+ "L'équipe de Tamago'Stitch te remercie de jouer à notre projet et te souhaite un bon jeu.\n"
			+ "Et surtout... profites de la tempête !";
	
	public String ENredText = "Welcome in Hawaï ! \r\n"
			+ "We entrust you with a mission, make sure to keep alive your Stitch as long as possible.\r\n"
			+ "You are currently playing Mad Stitch, forget everything you have learned so far...\n"
			+ "It corresponds to the last level of difficulty of this game.\r\n"
			+ "To keep your mad creature alive, you can do some actions such as :\n"
			+ "eating, sleeping, playing, washing or going to wc.\r\n"
			+ "Be careful, depending of the location and the weather, only few actions are possibles !\r\n"
			+ "Take care in your choices, do something can increase some statistics. \n"
			+ "It makes others decreases so much !\r\n"
			+ "Game is over when your Stitch has no life anymore.\r\n"
			+ "Score is calculated based on your game time.\r\n"
			+ "You can at any moment save or quit at any time, maybe to start another game with \n"
			+ "an easier difficulty if you need it.\r\n"
			+ "Tamago'Stitch's team thanks you for playing our projet, wishes you good game \n"
			+ "and above all... enjoy the storm !";
	
	public Rules(Controller controller, Pause pause) {
		this.controller = controller;
		this.pause=pause;
		this.addMouseListener(this);
		this.add(text);
		text.setOpaque(false);
		text.setBounds(25, 155, 550, 300);
		text.setFont(new Font("tahoma", Font.PLAIN, 13));
		text.setEditable(false);
		setLayout(null);
		repaint();
	}
	
	public void setType(int type) {
		this.type=type;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(Image.rules.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(btnBack.getImage(), 245, 40, 125, 75, this);
		g.drawImage(btnLang.getImage(), 475, 50, 50, 50, this);
	}

	public void mouseClicked(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1) {
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
			pause.displayRules(false);
		}
		if(loc==2) {
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
			if(type==1) {
				if(text.getText().equals(ENpinkText)) {
					text.setText(FRpinkText);
					btnLang=Image.english;
				}
				else {
					text.setText(ENpinkText);
					btnLang=Image.french;
				}
			}
			if(type==2) {
				if(text.getText().equals(ENblueText)) {
					text.setText(FRblueText);
					btnLang=Image.english;
				}
				else {
					text.setText(ENblueText);
					btnLang=Image.french;
				}
			}
			if(type==3) {
				if(text.getText().equals(ENbotText)) {
					text.setText(FRbotText);
					btnLang=Image.english;
				}
				else {
					text.setText(ENbotText);
					btnLang=Image.french;
				}
			}
			if(type==4) {
				if(text.getText().equals(ENredText)) {
					text.setText(FRredText);
					btnLang=Image.english;
				}
				else {
					text.setText(ENredText);
					btnLang=Image.french;
				}
			}
		}
		repaint();
	}
	/*
	 * 0=null
	 * 1=back
	 * 2=btnLang
	 */
	public int checkLocation(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if((x>225 && x<350) && (y>40 && y<115))return 1;
		if((x>475 && x<525) && (y>50 && y<100))return 2;
		return 0;
		
	}
	
	public void mousePressed(MouseEvent e) {
		int loc = checkLocation(e);
		if(loc==1)btnBack=Image.btnBackPressed;
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		btnBack = Image.btnBack;
		repaint();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
