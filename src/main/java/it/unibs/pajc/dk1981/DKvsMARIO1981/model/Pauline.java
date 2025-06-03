package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Pauline {
	//POSIZIONE
	private int x, y;
	private int spriteCounter;
	private int spriteNum;
	
	//SPRITES
	HashMap<String, BufferedImage[]> spriteMap = new HashMap<>();

	 public Pauline() {

	        setDefaultValues();
	        getPlayerImage();
	 }
	 
	 private void setDefaultValues() {
	    	x = 286;
	    	y = 16;
			spriteCounter = 0;
			spriteNum = 1;
	 }
	 
	 private void getPlayerImage() {
	        try {
	            BufferedImage[] rest = new BufferedImage[1];
	            rest[0] = ImageIO.read(getClass().getResourceAsStream("/background/27peach.png"));
	            spriteMap.put("rest", rest);


	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }

	
}
