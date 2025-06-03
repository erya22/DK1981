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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public HashMap<String, BufferedImage[]> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(HashMap<String, BufferedImage[]> spriteMap) {
		this.spriteMap = spriteMap;
	}
	 
	 

	
}
