package it.unibs.pajc.dk1981.DKvsMARIO1981.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.MovementState;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.State;

public class PlayerView {
	private Player model;
	
	public PlayerView(Player model) {
		this.model = model;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		BufferedImage image;

    	if (model.getState() == State.HIT) {
            int frameIndex = (int) ((System.currentTimeMillis() - model.getHitStartTime()) / (model.getHitDuration() / 5));
            frameIndex = Math.min(frameIndex, 4); // evita IndexOutOfBounds
            BufferedImage[] hitFrames = model.getSpriteMap().get("hit");
            image = hitFrames[frameIndex];
        } else if (model.getMovement() == MovementState.JUMPING) {
            String jumpKey = model.getDirection().equals("right") ? "jumpR" : "jumpL";
            BufferedImage[] jumpFrames = model.getSpriteMap().get(jumpKey);
            image = (jumpFrames != null && jumpFrames.length > 0)
                    ? jumpFrames[0]
                    : model.getSpriteMap().get(model.getDirection())[0];  // backup sicuro
        } else {
            BufferedImage[] frames = model.getSpriteMap().get(model.getDirection());
            image = frames[(model.getSpriteNum() - 1) % frames.length];
        }

        g2.drawImage(image, model.getX(), model.getY(), model.getTileSize(), model.getTileSize(), null);
	}
	
	 private void animate() {
		 int spriteCounter = model.getSpriteCounter() + 1;
		 int spriteNum = model.getSpriteNum();
		 
	        model.setSpriteCounter(spriteCounter);
	        if (spriteCounter > 10) {
	            spriteNum++;
	            BufferedImage[] frames = model.getSpriteMap().get(model.getDirection());
	            int maxFrame = frames != null ? frames.length : 1;
	            if (spriteNum > maxFrame) {
	                spriteNum = 1;
	            }
	            spriteCounter = 0;
	        }
	    }

}
