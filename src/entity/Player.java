package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePnl;
import main.KeyHandler;

/**
 * TODO: in case I need to create different animations, I can use GIMP or piskel.
 */
public class Player extends Entity {
    private GamePnl gp;
    private KeyHandler keyHandler;
    private int gravitySpeed;
    private boolean jumping = false;
    private int jumpSpeed;        // forza iniziale del salto
    private int yVelocity;         // velocità verticale corrente

    // Map direction -> array of frames
    private Map<String, BufferedImage[]> spriteMap = new HashMap<>();

    public Player(GamePnl gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues() {
        x = 0;
        y = 700;
        speed = 4;
        direction = "right";
        spriteCounter = 0;
        spriteNum = 1;
        yVelocity = 0;
        jumpSpeed = 12;
        gravitySpeed = 1;
    }

    public void update() {
        // calcola il tile ai piedi di mario
        int centerX = x + gp.tileSize / 2;
        int bottomY = y + gp.tileSize;
        int col = centerX / gp.tileSize;
        int rowBelow = bottomY / gp.tileSize;

        boolean onSolid = gp.tileM.isSolid(rowBelow, col);
        boolean onLadder = gp.tileM.isLadder(rowBelow, col);

        // scala
        if (onLadder) {
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
                animate();
                return;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
                animate();
                return;
            }
        }

        // movimento orizzontale
        boolean moved = false;
        if (keyHandler.leftPressed) {
            int newX = x - speed;
            if (!isCollision(newX, y)) {
                x = newX;
            }
            direction = "left";
            moved = true;
        } else if (keyHandler.rightPressed) {
            int newX = x + speed;
            if (!isCollision(newX, y)) {
                x = newX;
            }
            direction = "right";
            moved = true;
        }
        if (moved) {
            animate();
        }

     // salto/gravità
        if (jumping || !onSolid) {
            yVelocity += gravitySpeed;
            int newY = y + yVelocity;
            
            if (yVelocity > 0 && isCollision(x, newY)) { // se cade sul pavimento
                jumping = false;
                yVelocity = 0;
                y = ((newY + gp.tileSize) / gp.tileSize) * gp.tileSize - gp.tileSize; // snappa il player al suolo
            } else {
                y = newY;
            }
        } else {
            yVelocity = 0;
        }

        // jump trigger
        if (keyHandler.spacePressed && onSolid && !jumping) {
            jumping = true;
            yVelocity = -jumpSpeed;
        }

    }


    private void animate() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;
            BufferedImage[] frames = spriteMap.get(direction);
            int maxFrame = frames != null ? frames.length : 1;
            if (spriteNum > maxFrame) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    // calcola se agli angoli del player ci sono muri/pavimento
    private boolean isCollision(int nextX, int nextY) {
        int leftCol = nextX / gp.tileSize;
        int rightCol = (nextX + gp.tileSize - 1) / gp.tileSize;
        int topRow = nextY / gp.tileSize;
        int botRow = (nextY + gp.tileSize - 1) / gp.tileSize;

        return gp.tileM.isSolid(topRow, leftCol)
            || gp.tileM.isSolid(botRow, leftCol)
            || gp.tileM.isSolid(botRow, rightCol);
    }

    // l ha scritto chattie piu carino
    public void draw(Graphics2D g2) {
    	BufferedImage image;
    	if(jumping) {
            BufferedImage[] jumpFrames = spriteMap.get("jump");
            image = (jumpFrames != null && jumpFrames.length > 0)
                    ? jumpFrames[0]
                    : spriteMap.get(direction)[0];  // backup sicuro
        } else {
	        BufferedImage[] frames = spriteMap.get(direction);
	        image = frames[(spriteNum - 1) % frames.length];
        }
	    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    private void getPlayerImage() {
        try {
            // up/down 7 frames (b1..b7)
            BufferedImage[] ud = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                ud[i] = ImageIO.read(getClass().getResourceAsStream("/player/b" + (i+1) + ".png"));
            }
            spriteMap.put("up", ud);
            spriteMap.put("down", ud);

            // right 4 frames (a1..a4)
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/player/a" + (i+1) + ".png"));
            }
            spriteMap.put("right", right);

            // left 4 frames (m1..m4)
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/player/m" + (i+1) + ".png"));
            }
            spriteMap.put("left", left);
            
            BufferedImage[] jump = new BufferedImage[1];
            jump[0] = ImageIO.read(
                getClass().getResourceAsStream("/player/c1.png")
            );
            spriteMap.put("jump", jump);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}