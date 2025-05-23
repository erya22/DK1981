package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePnl extends JPanel implements Runnable{
	//This works as a game screen: SCREEN SETTINGS
	
	public static final int scaledTileSize = 32; //, it's a standard default dimension
	public static final int scale = 1; //have to change it so that it adapts to the screen settings of the pc
	
	public static final int tileSize = scaledTileSize * scale; //dimension of tiles on the screen
	public static final int maxScreenCol = 27;
	public static final int maxScreenRow = 27;
	public static final int screenWidth = tileSize * maxScreenCol;
	public static final int screenHeight = tileSize * maxScreenRow;
	
	//FPS
	public final int FPS = 60;
	
	public KeyHandler keyHandler = new KeyHandler();
	
	public Thread gameThread; //To help control time in game
	
	public Player player = new Player(this, keyHandler);
	
	public TileManager tileM = new TileManager(this);
	
	public GamePnl() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //betters game rendering performance
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/**
	 * Creating a game loop
	 * TODO:1 UPDATE the information such as character positions
	 * 		2 DRAW: animation of objects/characters, draw the screen with the updated information
	 */
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void update() {
		
		player.update();
	
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
		
		
	}
}
