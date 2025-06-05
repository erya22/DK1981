package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Barrel extends GameItem {
	

	
	//SPEED
	private int speedX, speedY;
	
	//SPRITES
	private static final int FRAME_DELAY = 10;
	
	
	//STATO
	private boolean collision = false;
	
	public Barrel(Universe universe) {
        super(universe);
        setDefaultValues();
        getItemImage();
    }
	
	public void setDefaultValues() {
		
	}

	
	public void getItemImage() {
		try {
            for (int i = 0; i < 4; i++) {
                sprite[i] = ImageIO.read(getClass().getResourceAsStream("/OBJECTS/barrel" + (i + 1) + ".png"));
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}



	public int getSpeedX() {
		return speedX;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}


	public int getSpeedY() {
		return speedY;
	}


	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}



	public boolean isCollision() {
		return collision;
	}



	public void setCollision(boolean collision) {
		this.collision = collision;
	}


	public static int getFrameDelay() {
		return FRAME_DELAY;
	}



	
	
	
	
	
}
