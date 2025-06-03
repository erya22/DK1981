package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Barrel extends GameItem {
	
	//POSIZIONE
	private int x, y;
	
	//SPEED
	private int speed;
	
	//SPRITES
	private HashMap<String, BufferedImage[]> spriteMap = new HashMap<String, BufferedImage[]>();
	private String name;
	
	//STATO
	private boolean collision = false;
	
	public Barrel(int x, int y, BufferedImage[] sprite, int speed) {
        super(x, y, 32, 32, sprite);
        this.speed = speed;
        getBarrelImage();
    }

	
	private void getBarrelImage() {
		BufferedImage[] frames = new BufferedImage[4];
		try {
            for (int i = 0; i < 4; i++) {
                frames[i] = ImageIO.read(getClass().getResourceAsStream("/barrel/barrel" + (i + 1) + ".png"));
                spriteMap.put(name, frames);
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



	public HashMap<String, BufferedImage[]> getSpriteMap() {
		return spriteMap;
	}



	public void setSpriteMap(HashMap<String, BufferedImage[]> spriteMap) {
		this.spriteMap = spriteMap;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public boolean isCollision() {
		return collision;
	}



	public void setCollision(boolean collision) {
		this.collision = collision;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
	
	
	
}
