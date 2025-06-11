package it.unibs.pajc.dk1981.DKvsMARIO1981.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.DK;

public class DKView {
	private DK model;
	
	public DKView(DK model) {
		this.model = model;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		BufferedImage image = null;
		HashMap<String, BufferedImage[]> spriteMap = model.getSpriteMap();
		
		 BufferedImage[] frames = spriteMap.get(model.getCurrentAction());
		 if (frames != null && frames.length > 0) {
			 image = frames[0];
		 }
		 //TODO: calcolare quella da disegnare proporzionata gamewindow
		 if (image != null) {
			 int height = image.getHeight();
			 int width = image.getWidth();
			 int drawWidth = 0;
			 int drawHeight = 0;
			 //WIDTH E HEIGHT DEVONO ESSERE IN ENTITY?
			 g2.drawImage(image, model.getX(), model.getY(), width, height, null); 
		 }
		 
		 animate();
		
	}
	
	 public void animate() {
		 
	        model.setSpriteCounter(model.getSpriteCounter()+1);
	        if (model.getSpriteCounter() > 10) {
	            model.setSpriteNum(model.getSpriteNum());
	            BufferedImage[] frames = model.getSpriteMap().get(model.getCurrentAction());
	            int maxFrame = frames != null ? frames.length : 1;
	            if (model.getSpriteNum() > maxFrame) {
	                model.setSpriteNum(1); 
	            }
	            model.setSpriteCounter(0);
	        }
	    }
}
