package model;

import javax.swing.ImageIcon;

public class Location {
	
	private String name;
	private ImageIcon background;
	
	public Location(String n, ImageIcon bg) {
		name=n;
		background=bg;
	}
	
	public String getName() {
		return name;
	}
	
	public ImageIcon getBackground() {
		return background;
	}
}
