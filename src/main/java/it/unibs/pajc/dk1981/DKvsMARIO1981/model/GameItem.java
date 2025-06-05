package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameItem {
    protected int x, y;
    protected int width, height;
    protected BufferedImage[] sprite;
    protected Universe universe;

    public GameItem(Universe universe) {
        this.universe = universe;
    }
    
    public abstract void getItemImage();
    public abstract void setDefaultValues();

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage[] getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage[] sprite) {
		this.sprite = sprite;
	}
    
    
}
