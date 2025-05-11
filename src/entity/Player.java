package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePnl;
import main.KeyHandler;


/**
 * TODO: in case I need to create different animations, I can use GIMP or piskel.
 */
public class Player extends Entity {
	
	GamePnl gp;
	KeyHandler keyHandler;
	
	public Player(GamePnl gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		
		setDefaultValues();
		getplayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void update() {
		
		if(keyHandler.upPressed == true || keyHandler.downPressed == true 
				|| keyHandler.rightPressed == true 
				|| keyHandler.leftPressed == true) {
			
			if(keyHandler.upPressed == true) {
				direction = "up";
				y -= speed;
			} else if(keyHandler.downPressed == true) {
				direction = "down";
				y += speed;
			} else if(keyHandler.leftPressed == true) {
				direction = "left";
				x -= speed;
			} else if(keyHandler.rightPressed == true) {
				direction = "right";
				x += speed;
			}
			
			spriteCounter++;
			
			if(spriteCounter > 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
		}
		
		
	
	/**
	 * TODO: sistema l'if statement per rendere il codice più leggibile
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		switch(direction) {
			case "up":
				if(spriteNum == 1)
					image = updown1;
				if(spriteNum == 2)
					image = updown2;
				if(spriteNum == 3)
					image = updown3;
				if(spriteNum == 4)
					image = updown4;
				if(spriteNum == 5)
					image = updown5;
				if(spriteNum == 6)
					image = updown6;
				if(spriteNum == 7)
					image = updown7;
				break;
			case "down":
				if(spriteNum == 1)
					image = updown1;
				if(spriteNum == 2)
					image = updown2;
				if(spriteNum == 3)
					image = updown3;
				if(spriteNum == 4)
					image = updown4;
				if(spriteNum == 5)
					image = updown5;
				if(spriteNum == 6)
					image = updown6;
				if(spriteNum == 7)
					image = updown7;
				break;
			case "right":
				if(spriteNum == 1)
					image = right1;
				if(spriteNum == 2)
					image = right2;
				if(spriteNum == 3)
					image = right3;
				if(spriteNum == 4)
					image = right4;
				break;
			case "left":
				if(spriteNum == 1)
					image = left1;
				if(spriteNum == 2)
					image = left2;
				if(spriteNum == 3)
					image = left3;
				if(spriteNum == 4)
					image = left4;
				break;
		}
		
		g2.drawImage(image, x, y, (int)gp.tileSize, (int)gp.tileSize, null);
	}
	
	public void getplayerImage() {
		try {
			
			updown1 = ImageIO.read(getClass().getResourceAsStream("/player/b1.png"));
			updown2 = ImageIO.read(getClass().getResourceAsStream("/player/b2.png"));
			updown3 = ImageIO.read(getClass().getResourceAsStream("/player/b3.png"));
			updown4 = ImageIO.read(getClass().getResourceAsStream("/player/b4.png"));
			updown5 = ImageIO.read(getClass().getResourceAsStream("/player/b5.png"));
			updown6 = ImageIO.read(getClass().getResourceAsStream("/player/b6.png"));
			updown7 = ImageIO.read(getClass().getResourceAsStream("/player/b7.png"));
			
			withHammer1 = ImageIO.read(getClass().getResourceAsStream("/player/c1.png"));
			withHammer2= ImageIO.read(getClass().getResourceAsStream("/player/c2.png"));
			
			tumbleR1 = ImageIO.read(getClass().getResourceAsStream("/player/e1.png"));
			tumbleR2 = ImageIO.read(getClass().getResourceAsStream("/player/e2.png"));
			tumbleR3 = ImageIO.read(getClass().getResourceAsStream("/player/e3.png"));
			tumbleR4 = ImageIO.read(getClass().getResourceAsStream("/player/e4.png"));
			tumbleR5 = ImageIO.read(getClass().getResourceAsStream("/player/e5.png"));
			
			tumbleL1 = ImageIO.read(getClass().getResourceAsStream("/player/d1.png"));
			tumbleL2 = ImageIO.read(getClass().getResourceAsStream("/player/d2.png"));
			tumbleL3 = ImageIO.read(getClass().getResourceAsStream("/player/d3.png"));
			tumbleL4 = ImageIO.read(getClass().getResourceAsStream("/player/d4.png"));
			tumbleL5 = ImageIO.read(getClass().getResourceAsStream("/player/d5.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/m1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/m2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/m3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/m4.png"));
			breakPL = ImageIO.read(getClass().getResourceAsStream("/player/m5.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/a1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/a2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/a3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/a4.png"));
			breakPR = ImageIO.read(getClass().getResourceAsStream("/player/a5.png"));
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
