package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hammer {
	private String name;
	private BufferedImage image;
	private int x, y;
	
	public Hammer(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		setDefaultValues();
		getHammerImage();
	}

	private void getHammerImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/hammer/f1.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		}
	}

	private void setDefaultValues() {
		name = "Hammer";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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
	
	

}
