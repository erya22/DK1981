package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Entity {
	private static final Logger log = LoggerFactory.getLogger(Entity.class);
	private final String name;
	
	private int x, y;
	private int speedX, speedY;
	private String direction;
	
	private int spriteCounter;
	private int spriteNum = 1;
	private HashMap<String, BufferedImage[]> spriteMap;
	
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
		if (x < 0) return;
		log.info("Set x{}", x);
		if (x == 0) { log.info("Zero", new RuntimeException("Zero")); throw new RuntimeException("Zero"); }
		this.x = x;
	}
	
	public int addX(int addendo) {
		this.x += addendo;
		log.info("Set x{}", x);
		return this.x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y < 0) return;
		log.info("Set y{}", y);
		this.y = y;
	}
	
	public int addY(int addendo) {
		this.y += addendo;
		log.info("Set y{}", y);
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

	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public String getName() {
		return name;
	}
	
	public int getScreenX() {
		
		return (int) (getX() * Universe.SCALE_FACTOR);
	}
	
	public int getScreenY() {
		return (int) (getY() * Universe.SCALE_FACTOR);
	}

	
	
	
}
