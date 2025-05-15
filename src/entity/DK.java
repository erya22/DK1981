package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePnl;
import object.Barrel;

public class DK extends Entity {
    private GamePnl gp;

    private Map<String, BufferedImage[]> spriteMap = new HashMap<>();

    private String[] actionCycle = { "rest", "urlo", "sx", "dx", "rest", "sx", "dx" };
    private int currentActionIndex = 0;
    private int actionTimer = 0;
    private final int ACTION_DURATION = 60; // cambia azione ogni 60 frame (~1 sec)
    String currentAction;

    public DK(GamePnl gp) {
        this.gp = gp;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
    	x = 80;
    	y = 128;
		spriteCounter = 0;
		spriteNum = 1;
		currentActionIndex = 0;
		currentAction = actionCycle[currentActionIndex]; 
		actionTimer = 0;
    	

    }

    public void update() {
        actionTimer++;
        if (actionTimer >= ACTION_DURATION) {
            actionTimer = 0;
            currentActionIndex = (currentActionIndex + 1) % actionCycle.length;
            currentAction = actionCycle[currentActionIndex];
            
            // se va a dx lancia il barile
            if (currentAction.equals("dx")) {
                launchBarrel();
            }
        }

        animate();
    }

    private void launchBarrel() {

        Barrel b = new Barrel(x + 64, y);
        gp.addBarrel(b); // lo aggiungi alla lista dei barili gestiti in GamePnl
    }

    private void animate() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;
            BufferedImage[] frames = spriteMap.get(currentAction);
            int maxFrame = frames != null ? frames.length : 1;
            if (spriteNum > maxFrame) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        BufferedImage[] frames = spriteMap.get(currentAction);
        if (frames != null && frames.length > 0) {
            image = frames[0];
        }

        if (image != null) {
        	int drawWidth = gp.tileSize * 2;
        	int drawHeight = gp.tileSize * 2;
        	g2.drawImage(image, x, y, drawWidth, drawHeight, null); }
    }


    private void getPlayerImage() {
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
}