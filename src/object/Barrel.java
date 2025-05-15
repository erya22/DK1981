package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import main.GamePnl;

public class Barrel extends SuperObject {

    private BufferedImage[] frames = new BufferedImage[4];
    private int frameIndex = 0;
    private int frameCounter = 0;
    private final int FRAME_DELAY = 10; // cambia frame ogni 10 tick
    private int speedY = 5;
    private int speedX = 3;

    public Barrel(int startX, int startY) {
        name = "Barrel";
        worldX = startX;
        worldY = startY;

        try {
            for (int i = 0; i < 4; i++) {
                frames[i] = ImageIO.read(getClass().getResourceAsStream("/barrel/barrel" + (i + 1) + ".png"));
            }
            image = frames[0]; // inizialmente
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void update(GamePnl gp) {
        // animazione: avanza il frame ogni N tick
        frameCounter++;
        if (frameCounter >= FRAME_DELAY) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % frames.length;
            image = frames[frameIndex];
        }

     // calcolo posizione sotto il barile
        int tileSize = gp.tileSize;
        int col = worldX / tileSize;
        int rowBelow = (worldY + tileSize) / tileSize;

        boolean hasFloorBelow = gp.tileM.isSolid(rowBelow, col) || gp.tileM.isLadder(rowBelow, col);

        if (!hasFloorBelow) {
            // sta cadendo
            speedY = 2;
            worldY += speedY;
            return;
        }

        // Appena tocca il suolo: imposta la direzione di rotolamento per la rampa
        speedY = 0;
        speedX = getRollDirectionForRow(rowBelow);

        // rotolamento
        worldX += speedX;
    }

    // da risistemare quando si rifarà il bg
    private int getRollDirectionForRow(int row) {
        switch (row) {
            case 6:
            case 14:
                return 3;  // rotola a dx
            default:
                return -3;   // default: sx
        }
    }
}
