package main;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		@SuppressWarnings("unused")
		Controller c = new Controller();
	}
}