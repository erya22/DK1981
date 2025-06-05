package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Entity {
	
	private final String name;
	private int x, y;
	private int speedX, speedY;
	
	private String direction;
	private int spriteCounter;
	private int spriteNum = 1;
	private HashMap<String, BufferedImage[]> spriteMap;
	private int tileSize;
	private Universe universe;
	
	public Entity(Universe universe, String name) {
		this.universe = universe;
		this.name = name;
	}
	
	public abstract void getEntityImage();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int addX(int addendo) {
		this.x += addendo;
		return this.x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int addY(int addendo) {
		this.y += addendo;
		return this.y;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	
	
	
	

	
}
