package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Player extends Entity{

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
	private static final int IMMUNITY = 2000;
	
	private int tileSize = 32;
	private long hitStartTime = 0;
	private final long HIT_DURATION = 1500;
	
	private Universe universe;
	
	

	
	public Player(Universe universe) {
		super(universe);
		setDefaultValues();
		getEntityImage();
	}

	public void setDefaultValues() {
		x = 0;
		y = 0;
		yVelocity = 0;
		jumpSpeed = 12;
		gravitySpeed = 1;
		spriteMap = new HashMap<>();
	}
	
	public void getEntityImage() {
        try {
        	
            // UP/DOWN
            BufferedImage[] up = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/b" + (i+1) + ".png"));
            }
            spriteMap.put("up", up);
            spriteMap.put("down", up);

            // RIGHT
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/a" + (i + 1) + ".png"));
            }
            spriteMap.put("right", right);

            // LEFT
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m" + (i + 1) + ".png"));
            }
            spriteMap.put("left", left);
            
            // JUMP ANIMATION
            BufferedImage[] jumpR = new BufferedImage[1];
            jumpR[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/c3.png"));
            spriteMap.put("jumpR", jumpR);
            BufferedImage[] jumpL = new BufferedImage[1];
            jumpL[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m3.png"));
            spriteMap.put("jumpL", jumpL);

            BufferedImage[] hitFrames = new BufferedImage[5];
            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFrames[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + (i + 1) + ".png"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
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

	public static int getImmunity() {
		return IMMUNITY;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public long getHitStartTime() {
		return hitStartTime;
	}

	public void setHitStartTime(long hitStartTime) {
		this.hitStartTime = hitStartTime;
	}

	public long getHitDuration() {
		return HIT_DURATION;
	}

	
	
	

}
