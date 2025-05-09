package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int speed;
	
	public BufferedImage updown1, updown2, updown3, updown4, updown5, updown6, updown7,
	withHammer1, withHammer2,
	tumbleR1, tumbleR2, tumbleR3, tumbleR4, tumbleR5,
	tumbleL1, tumbleL2, tumbleL3, tumbleL4, tumbleL5,
	left1, left2, left3, left4,
	right1, right2, right3, right4,
	breakPL, breakPR;
	
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

}
