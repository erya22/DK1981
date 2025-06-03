package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Player {

	//POSITION
	private int x, y;
	private int speed;
	
	//GRAVITY/SPEED
	private int jumpSpeed;
	private int gravitySpeed;
	private int yVelocity;

	//STATO DI GIOCO
	private State state;
	private MovementState movement;
	
	//GESTIONE VITE
	private int vite = 3;
	private long invincibleTime = 0;
	private final int IMMUNITY = 2000;
	
	//SPRITES
	private HashMap<String, BufferedImage[]> spriteMap = new HashMap<>();
	String direction = "right";
	private int spriteCounter = 0;
	private int spriteNum = 1;
	
	public Player() {
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 0;
		y = 0;
		speed = 4;
		direction = "right";
		spriteCounter = 1;
		spriteNum = 1;
		yVelocity = 0;
		jumpSpeed = 12;
		gravitySpeed = 1;
	}
	
	private void getPlayerImage() {
        try {
            // UP/DOWN
            BufferedImage[] up = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/player/b" + (i+1) + ".png"));
            }
            spriteMap.put("up", up);
            spriteMap.put("down", up);

            // RIGHT
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/player/a" + (i + 1) + ".png"));
            }
            spriteMap.put("right", right);

            // LEFT
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/player/m" + (i + 1) + ".png"));
            }
            spriteMap.put("left", left);
            
            // JUMP ANIMATION
            BufferedImage[] jumpR = new BufferedImage[1];
            jumpR[0] = ImageIO.read(getClass().getResourceAsStream("/player/c3.png"));
            spriteMap.put("jumpR", jumpR);
            BufferedImage[] jumpL = new BufferedImage[1];
            jumpL[0] = ImageIO.read(getClass().getResourceAsStream("/player/m3.png"));
            spriteMap.put("jumpL", jumpL);

            BufferedImage[] hitFrames = new BufferedImage[5];
            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/e" + (i + 1) + ".png"));
            }


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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public int getGravitySpeed() {
		return gravitySpeed;
	}

	public void setGravitySpeed(int gravitySpeed) {
		this.gravitySpeed = gravitySpeed;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public MovementState getMovement() {
		return movement;
	}

	public void setMovement(MovementState movement) {
		this.movement = movement;
	}

	public int getVite() {
		return vite;
	}

	public void setVite(int vite) {
		this.vite = vite;
	}

	public long getInvincibleTime() {
		return invincibleTime;
	}

	public void setInvincibleTime(long invincibleTime) {
		this.invincibleTime = invincibleTime;
	}

	public HashMap<String, BufferedImage[]> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(HashMap<String, BufferedImage[]> spriteMap) {
		this.spriteMap = spriteMap;
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

	public int getIMMUNITY() {
		return IMMUNITY;
	}
	
	

}
