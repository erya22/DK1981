package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DKvsMario extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	public void start() {
		new Thread(this).start();
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		
		setSize(500, 500);
		
		BufferedImage screen = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics g = screen.getGraphics();
		
		Graphics panelGraphics = getGraphics();
		
		long delta = 0l;
		
		while (true) {
			long lastTime = System.nanoTime();
			
			g.setColor(Color.black);
			g.fillRect(0, 0, 500, 500);
			
			panelGraphics.drawImage(screen, 0, 0, null);
			
			delta = System.nanoTime() - lastTime;
			   if (delta < 20000000L) {
			    try {
			     Thread.sleep((20000000L - delta) / 1000000L);
			    } catch (Exception e) {
			     // It's an interrupted exception, and nobody cares
			    }
			   }
//			   if (!isActive()) {
//			    return;
//			   }
//			
		}
		
		 
	}
	
	//TODO: handle events

}
