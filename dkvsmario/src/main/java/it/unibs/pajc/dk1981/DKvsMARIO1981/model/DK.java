package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class DK {
	//POSIZIONE
	private int x, y;
	
	//SPRITE
	private HashMap<String, BufferedImage[]> spriteMap = new HashMap<>();
	private int spriteCounter;
	private int spriteNum;
	
	
	//STATO DK
	private State state;
	
	
	//ACTION
	private String[] actionCycle = {"rest", "urlo", "sx", "dx", "rest", "sx", "dx"};
	private int actionIndex = 0;
	private int actionTimer = 0;
	private final int ACTION_DURATION = 60;
	private String currentAction;
	
	public DK() {
		
		setDefaultValues();
		getDKImage();
	}
	
	public void setDefaultValues() {
		x = 0;
		y = 0;
		spriteCounter = 0;
		spriteNum = 1;
		actionIndex = 0;
		currentAction = actionCycle[actionIndex]; 
		actionTimer = 0;
	}
	
	public void getDKImage() {
		try {
            // dx/sx prendi barili
            BufferedImage[] dx = new BufferedImage[1];
            dx[0] = ImageIO.read(getClass().getResourceAsStream("/DK/h6.png"));
            spriteMap.put("dx", dx);
            BufferedImage[] sx = new BufferedImage[1];
            sx[0] = ImageIO.read(getClass().getResourceAsStream("/DK/h4.png"));
            spriteMap.put("sx", sx);

            // urlo selvaggio
            BufferedImage[] rest = new BufferedImage[1];
            rest[0] = ImageIO.read(getClass().getResourceAsStream("/DK/h1.png"));
            spriteMap.put("rest", rest);
            BufferedImage[] urlo = new BufferedImage[1];
            urlo[0] = ImageIO.read(getClass().getResourceAsStream("/DK/h3.png"));
            spriteMap.put("urlo", urlo);


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

	public HashMap<String, BufferedImage[]> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(HashMap<String, BufferedImage[]> spriteMap) {
		this.spriteMap = spriteMap;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String[] getActionCycle() {
		return actionCycle;
	}

	public void setActionCycle(String[] actionCycle) {
		this.actionCycle = actionCycle;
	}

	public int getActionIndex() {
		return actionIndex;
	}

	public void setActionIndex(int actionIndex) {
		this.actionIndex = actionIndex;
	}

	public int getActionTimer() {
		return actionTimer;
	}

	public void setActionTimer(int actionTimer) {
		this.actionTimer = actionTimer;
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public int getACTION_DURATION() {
		return ACTION_DURATION;
	}
	
	
}
