package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class DK extends Entity {

	//STATO DK
	private State state;
	
	
	//ACTION
	private String[] actionCycle = {"rest", "urlo", "sx", "dx", "rest", "sx", "dx"};
	private int actionIndex = 0;
	private int actionTimer = 0;
	private final int ACTION_DURATION = 60;
	private String currentAction;
	
	
	public DK(Universe universe) {
		super(universe, "DK");
		setDefaultValues();
		getEntityImage();
	}

	public void setDefaultValues() {
		this.setX(0); 
		this.setY(0);
		this.setSpriteCounter(0);
		this.setSpriteNum(1); 
		actionIndex = 0;
		currentAction = actionCycle[actionIndex]; 
		actionTimer = 0;
		this.setSpriteMap(new HashMap<>());
	}
	
	public void getEntityImage() {
		HashMap<String, BufferedImage[]> spriteMap = this.getSpriteMap();
		try {
            // dx/sx prendi barili
            BufferedImage[] dx = new BufferedImage[1];
            dx[0] = ImageIO.read(getClass().getResourceAsStream("/NPCS/h6.png"));
			spriteMap.put("dx", dx);
            BufferedImage[] sx = new BufferedImage[1];
            sx[0] = ImageIO.read(getClass().getResourceAsStream("/NPCS/h4.png"));
            spriteMap.put("sx", sx);

            // urlo selvaggio
            BufferedImage[] rest = new BufferedImage[1];
            rest[0] = ImageIO.read(getClass().getResourceAsStream("/NPCS/h1.png"));
            spriteMap.put("rest", rest);
            BufferedImage[] urlo = new BufferedImage[1];
            urlo[0] = ImageIO.read(getClass().getResourceAsStream("/NPCS/h3.png"));
            spriteMap.put("urlo", urlo);


        } catch (IOException e) {
            e.printStackTrace();
        }
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
