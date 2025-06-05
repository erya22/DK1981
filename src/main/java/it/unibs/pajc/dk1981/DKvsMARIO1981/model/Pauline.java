package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Pauline extends Entity{
	 
	 public Pauline(Universe universe) {
		super(universe);
		setDefaultValues();
		getEntityImage();
	}

	public void setDefaultValues() {
			spriteCounter = 0;
			spriteNum = 1;
			spriteMap = new HashMap<>();
	 }
	 
	 public void getEntityImage() {
	        try {
	            BufferedImage[] rest = new BufferedImage[1];
	            rest[0] = ImageIO.read(getClass().getResourceAsStream("/NPCS/27peach.png"));
	            spriteMap.put("rest", rest);


	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }

	

	
}
