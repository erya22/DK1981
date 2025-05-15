package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePnl;
import main.KeyHandler;
import object.Barrel;

public class Player extends Entity {
	
	// GENERAL SETTINGS
    private GamePnl gp;
    private KeyHandler keyHandler;
    
    // GRAVITY/SPEED
    private int gravitySpeed;
    private boolean jumping = false;
    private int jumpSpeed;        // forza iniziale del salto
    private int yVelocity;         // velocità verticale corrente
    
    // GESTIONE VITE
    private boolean invincible = false;
    private int vite = 3; 
    private long invincibleStartTime = 0;
    private final int IMMUNITY = 2000; // 2 sec di immunità dopo essere colpito
    private BufferedImage[] hitFrames = new BufferedImage[5]; // unico messo qua pk serve delay

    // MAPPA
    private Map<String, BufferedImage[]> spriteMap = new HashMap<>();

    public Player(GamePnl gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
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
        // Se game over o in hit animation, salta tutto
        if (gp.gameOver || gp.isHitAnim) return;

        // Aggiorna immunità
        if (invincible) {
            long elapsed = System.currentTimeMillis() - invincibleStartTime;
            if (elapsed >= IMMUNITY) {
                invincible = false;
            }
        }

        // Gestione input e movimento
        handleMovement();

        // Controllo collisione con barili
        handleBarrelCollision();
    }

    private void handleMovement() {
        int centerX = x + gp.tileSize/2;
        int bottomY = y + gp.tileSize;
        int col = centerX / gp.tileSize;
        int rowBelow = bottomY / gp.tileSize;
        boolean onSolid = gp.tileM.isSolid(rowBelow, col);
        boolean onLadder = gp.tileM.isLadder(rowBelow, col);

        // SCALA
        if (onLadder) {
            if (keyHandler.upPressed) { direction = "up"; y -= speed; animate(); return; }
            if (keyHandler.downPressed) { direction = "down"; y += speed; animate(); return; }
        }

        // MOVIMENTO ORIZZONTALE
        boolean moved = false;
        if (keyHandler.leftPressed) {
            int nx = x - speed;
            if (!isCollision(nx, y)) x = nx;
            direction = "left"; moved = true;
        } else if (keyHandler.rightPressed) {
            int nx = x + speed;
            if (!isCollision(nx, y)) x = nx;
            direction = "right"; moved = true;
        }
        if (moved) animate();

        // JUMP
        if (keyHandler.spacePressed && onSolid && !jumping) {
            jumping = true;
            yVelocity = -jumpSpeed;
        }

        // GRAVITY
        if ((jumping || !onSolid) && !onLadder) {
            yVelocity += gravitySpeed;
            int ny = y + yVelocity;
            if (yVelocity > 0 && isCollision(x, ny)) {
                jumping = false;
                yVelocity = 0;
                y = ((ny + gp.tileSize)/gp.tileSize)*gp.tileSize - gp.tileSize;
            } else {
                y = ny;
            }
        } else {
            yVelocity = 0;
        }
    }

    private void handleBarrelCollision() {
        if (invincible) return;
        Rectangle playerRect = new Rectangle(x, y, gp.tileSize, gp.tileSize);
        for (Barrel b : gp.barrels) {
            Rectangle barrelRect = new Rectangle(b.worldX, b.worldY, gp.tileSize, gp.tileSize);
            if (playerRect.intersects(barrelRect)) {
                vite--;
                invincible = true;
                invincibleStartTime = System.currentTimeMillis();
                if (vite <= 0) {
                    gp.gameOver = true;
                } else {
                    gp.isHitAnim = true;
                    gp.hitStartTime = System.currentTimeMillis();
                }
                break;
            }
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
//        int topRow = nextY / gp.tileSize;
        int botRow = (nextY + gp.tileSize - 1) / gp.tileSize;

        return gp.tileM.isSolid(botRow, leftCol)
            || gp.tileM.isSolid(botRow, rightCol);
    }

    public void draw(Graphics2D g2) {
    	BufferedImage image;

    	if (gp.isHitAnim) {
            int frameIndex = (int) ((System.currentTimeMillis() - gp.hitStartTime) / (gp.HIT_ANIMATION_DURATION / 5));
            frameIndex = Math.min(frameIndex, 4); // evita IndexOutOfBounds
            image = hitFrames[frameIndex];
        } else if (jumping) {
            String jumpKey = direction.equals("right") ? "jumpR" : "jumpL";
            BufferedImage[] jumpFrames = spriteMap.get(jumpKey);
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

            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/e" + (i + 1) + ".png"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}