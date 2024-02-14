package model;

import javax.swing.ImageIcon;

public class Weather {
	
	private String name;
	private int rate;//taux d'apparition ?
	private ImageIcon background;
	
	public Weather(String n, int r, ImageIcon bg) {
		name=n;
		rate=r;
		setBackground(bg);
	}
	
	public String getName() {
		return name;
	}
	
	public int getRate() {
		return rate;
	}

	public ImageIcon getBackground() {
		return background;
	}

	public void setBackground(ImageIcon bg) {
		background = bg;
	}

}
