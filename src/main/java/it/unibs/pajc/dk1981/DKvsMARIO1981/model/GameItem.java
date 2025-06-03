package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameItem {
    protected int x, y;
    protected int width, height;
    protected BufferedImage[] sprite;

    public GameItem(int x, int y, int width, int height, BufferedImage[] sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
