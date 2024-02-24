package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Image;

public class PlayerPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private JLabel labelPlayer;
	private JLabel labelScore;
	private JLabel btnLoad;
	private JLabel btnDelete;
	
	private Controller controller;

	
	public PlayerPanel(Controller c, String player, String score) {
		this.controller = c;
		
		labelPlayer = new JLabel(player + "        ");
		labelScore = new JLabel(score + "                                       ");
		btnLoad = new JLabel(Image.btnLoad);
		btnDelete = new JLabel(Image.btnDelete);
		
		labelPlayer.setFont(new Font("Arial Black", Font.BOLD, 16));
		labelPlayer.setForeground(new Color(31, 88, 135));
		
		labelScore.setFont(new Font("Arial Black", Font.BOLD, 16));
		labelScore.setForeground(new Color(31, 88, 135));
		btnLoad.addMouseListener(this);
		btnDelete.addMouseListener(this);
		
	}
	public JLabel getLabelPlayer() {
		return labelPlayer;
		
	}
	public JLabel getLabelScore() {
		return labelScore;
		
	}
	public JLabel getBtnLoad() {
        return btnLoad;
    }

    public JLabel getBtnDelete() {
        return btnDelete;
    }
    
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(btnLoad)) {
			Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
			controller.toGamePanelFromLoad(labelPlayer.getText().trim());
	    } else if (e.getSource().equals(btnDelete)) {
	    	Menu.buttonSound.setFramePosition(0);
			Menu.buttonSound.start();
	    	controller.deletePlayer(labelPlayer.getText().trim());
	    }
	}

	public void mousePressed(MouseEvent e) {
		 if (e.getSource().equals(btnLoad)) btnLoad.setIcon(Image.btnLoadPressed);
		 if (e.getSource().equals(btnDelete)) btnDelete.setIcon(Image.btnDeletePressed);
		 repaint();	
	}

	public void mouseReleased(MouseEvent e) {
		 btnLoad.setIcon(Image.btnLoad);
		 btnDelete.setIcon(Image.btnDelete);
		 repaint();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

}