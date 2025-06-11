package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player extends Entity{
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	public static final int BASE_TILE = 16;
	//GRAVITY/SPEED
	private final int jumpStrenght = 12;
	private final int gravity = 1;
	private final int moveSpeed = 4;
	
	private int yVelocity = 12;
	private int xVelocity;
	
	private int frameIndex = (int) ((System.currentTimeMillis() - this.getHIT_DURATION()) / (this.getHIT_DURATION() / 5));
	
	//STATO DI GIOCO
	private State state;
	private MovementState movement;
	private Terrain terrain;
	
	//GESTIONE VITE
	private int vite = 3;
	private long invincibleTime = 0;
	private final int IMMUNITY = 2000;
	
	private long hitStartTime = 0;
	private final long HIT_DURATION = 1500;

	
	public Player(Universe universe) {
		super(universe, "Mario");
		setDefaultValues();
		getEntityImage();
	}

	public void setDefaultValues() {
		this.setDirection("right");
		this.setX(32); 
		this.setY(22*32 + 3);
		setyVelocity(0);
		this.setSpeedX(4);
		this.setSpeedY(4);
		this.setSpriteMap(new HashMap<>());
		setTerrain(Terrain.BEAM);
		setMovement(MovementState.IDLE);
		setState(State.ALIVE);
		
	}
	
	
	public void getEntityImage() {
		HashMap<String, BufferedImage[]> spriteMap = this.getSpriteMap();
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
            
            spriteMap.put("hit", hitFrames);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public int getFrameIndex() {
		return frameIndex;
	}

	public void addyVelocity(int adder) {
		this.yVelocity += adder;
	}
	
	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
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

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
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

	public long getHitStartTime() {
		return hitStartTime;
	}

	public void setHitStartTime(long hitStartTime) {
		this.hitStartTime = hitStartTime;
	}

	public static Logger getLog() {
		return log;
	}

	public static int getBaseTile() {
		return BASE_TILE;
	}

	public int getJumpStrenght() {
		return jumpStrenght;
	}

	public int getGravity() {
		return gravity;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public int getIMMUNITY() {
		return IMMUNITY;
	}

	public long getHIT_DURATION() {
		return HIT_DURATION;
	}

	public void climb(MovementState movement) {
		this.setMovement(movement);
		log.info("x{} y{}", this.getX(), this.getY());
		
		if (this.getMovement() == MovementState.UPCLIMB) {
			if (getTerrain() == Terrain.LADDER) {
				this.addY(-this.getSpeedY());
				int newY = this.getUniverse().findLadder(getX(), getY(), getUniverse().U_TILE_SIZE);
				if (newY != -1) {
					this.setY(newY);
				} else {
					newY = this.getUniverse().findBeam(getX(), getY(), getUniverse().U_TILE_SIZE);
					this.setTerrain(Terrain.BEAM);
					this.setMovement(MovementState.IDLE);
				}
					
			} else {
				int newY = this.getUniverse().findLadder(getX(), getY(), getUniverse().U_TILE_SIZE);
				if (newY != -1) {
					this.setTerrain(Terrain.LADDER);
					this.setY(newY);
				}
			}
		} else if (this.getMovement() == MovementState.DOWNCLIMB) {
			if (getTerrain() == Terrain.LADDER) {
				this.addY(this.getSpeedY());
				int newY = this.getUniverse().findLadder(getX(), getY(), getUniverse().U_TILE_SIZE);
				if (newY != -1) {
					this.setY(newY);
				} else {
					newY = this.getUniverse().findBeam(getX(), getY(), getUniverse().U_TILE_SIZE);
					this.setTerrain(Terrain.BEAM);
					this.setMovement(MovementState.IDLE);
				}
					
			} else {
				int newY = this.getUniverse().findLadder(getX(), getY(), getUniverse().U_TILE_SIZE);
				if (newY != -1) {
					this.setTerrain(Terrain.LADDER);
					this.setY(newY);
				}
			}
		}
		
	}


	public void walk(String direction) {
		setMovement(MovementState.WALKING);
		if (direction.equals("left")) {
            this.addX(-this.getSpeedX());
            this.setY(this.getUniverse().findBeam(getX(), getY(), getUniverse().U_TILE_SIZE));
        } else if (direction.equals("right")) {
            this.addX(this.getSpeedX());
            this.setY(this.getUniverse().findBeam(getX(), getY(), getUniverse().U_TILE_SIZE));
        }
	}

	public void startJump() {
	    if (getTerrain() == Terrain.BEAM && getMovement() != MovementState.JUMPING) {
	        this.setyVelocity(yVelocity);
	        
	     // Mantieni velocità orizzontale
	        if (this.getDirection().equals("left")) {
	            setxVelocity(-moveSpeed); 
	        } else if (this.getDirection().equals("right")) {
	        	setxVelocity(moveSpeed);
	        } else {
	        	setxVelocity(0); // nessuna direzione = salto verticale
	        }
	        
	        setMovement(MovementState.JUMPING);
	        setTerrain(Terrain.AIR);
	    }
	}
	
	public void updatePhysics() {
		
		
	    if (getTerrain() == Terrain.AIR || getMovement() == MovementState.JUMPING) {
	        addyVelocity(gravity); 
	        int newY = getY() + yVelocity;
	        int newX = getX() + xVelocity;
	        
	        log.info("Ricerca beam → x: {}, y: {}", getX(), getY());
	        int beamY = getUniverse().findBeam(getX(), getY(), getUniverse().U_TILE_SIZE);
	        log.info("Risultato beamY: {}", beamY);
	        
	        if (beamY != -1 && getyVelocity() > 0 && beamY < newY) {
	            setY(beamY);
	            setMovement(MovementState.IDLE);
	            setTerrain(Terrain.BEAM);
	            setyVelocity(0); 
	            setxVelocity(0);
	        } else {
	        	log.warn("Beam non trovata, continuando movimento");
	            setY(newY);
	            setX(newX);
	        }
	    }
	}


	
	

	public void idle() {
		if (getTerrain() != Terrain.AIR) {
			return;
		}
		switch(getMovement()) {
			case JUMPING: 
				this.setMovement(MovementState.FALLING);
				this.yVelocity = 0;
				break;
			
		}
		
	}

	

	
	
	

}
