package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePnl;

public class Pauline extends Entity {
    private GamePnl gp;

    private Map<String, BufferedImage[]> spriteMap = new HashMap<>();

    public Pauline(GamePnl gp) {
        this.gp = gp;

        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues() {
    	x = 286;
    	y = 16;
		spriteCounter = 0;
		spriteNum = 1;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        BufferedImage[] frames = spriteMap.get("rest");
        if (frames != null && frames.length > 0) {
            image = frames[0];
        }

        if (image != null) {
        	g2.drawImage(image, x, y, 48, 48, null); }
    }


    private void getPlayerImage() {
        try {
            BufferedImage[] rest = new BufferedImage[1];
            rest[0] = ImageIO.read(getClass().getResourceAsStream("/background/27peach.png"));
            spriteMap.put("rest", rest);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
