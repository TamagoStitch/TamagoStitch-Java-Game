package view;

import java.awt.Dimension;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import controller.Controller;

public class View extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Menu menuPanel;
	public static LoadPanel loadPanel;
	public Create createPanel;
	public Game gamePanel;
	public Over overPanel;
	public ScorePanel scorePanel;
	
	public View(Controller controller) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		super("Tamago'Stitch");
		menuPanel = new Menu(controller);
		loadPanel = new LoadPanel(controller);
		createPanel = new Create(controller);
		gamePanel = new Game(controller);
		overPanel = new Over(controller);
		scorePanel = new ScorePanel(controller);

		this.setContentPane(menuPanel);
		this.setSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
