package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hammer extends GameItem{
	private String name;
	public enum ObjState {INACTIVE, ACTIVE};
	private BufferedImage[] images;
	private int x, y;
	private ObjState state;
	
	public Hammer(Universe universe) {
		
		super(universe);
		setDefaultValues();
		getItemImage();
	}

	public void getItemImage() {
		try {
			images[0] = ImageIO.read(getClass().getResourceAsStream("/OBJECTS/f1.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		}
	}

	public void setDefaultValues() {
		name = "Hammer";
		this.setState(ObjState.INACTIVE);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ObjState getState() {
		return state;
	}

	public void setState(ObjState state) {
		this.state = state;
	}
	
	

}
