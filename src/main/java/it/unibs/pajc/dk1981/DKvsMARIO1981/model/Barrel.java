package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Barrel extends GameItem {
	
	//POSIZIONE
	private int x, y;
	
	//SPEED
	private int speedX, speedY;
	
	//SPRITES
	private BufferedImage[] sprites = new BufferedImage[4];
	private static final int FRAME_DELAY = 10;
	
	
	//STATO
	private boolean collision = false;
	
	public Barrel(int x, int y, BufferedImage[] sprite, int speedX, int speedY) {
        super(x, y, 32, 32, sprite);
        this.speedX = speedX;
        this.speedY = speedY;
        getBarrelImage();
    }

	
	private void getBarrelImage() {
		try {
            for (int i = 0; i < 4; i++) {
                sprites[i] = ImageIO.read(getClass().getResourceAsStream("/barrel/barrel" + (i + 1) + ".png"));
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
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


	public BufferedImage[] getSprites() {
		return sprites;
	}


	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
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
