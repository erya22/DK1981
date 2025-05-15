package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import entity.DK;
import entity.Pauline;
import entity.Player;
import object.Barrel;
import object.SuperObject;
import tile.TileManager;

public class GamePnl extends JPanel implements Runnable{
	//This works as a game screen: SCREEN SETTINGS
	
	public static final int scaledTileSize = 32; //, it's a standard default dimension
	public static final int scale = 1; //have to change it so that it adapts to the screen settings of the pc
	
	public final int tileSize = scaledTileSize * scale; //dimension of tiles on the screen
	public final int maxScreenCol = 27;
	public final int maxScreenRow = 27;
	public  int screenWidth = tileSize * maxScreenCol;
	public  int screenHeight = tileSize * maxScreenRow;
	
	//FPS
	public final int FPS = 60;
	
	public KeyHandler keyHandler = new KeyHandler();
	
	public Thread gameThread; //To help control time in game
	
	public Player player = new Player(this, keyHandler);
	public DK dk = new DK(this);
	public Pauline pauline = new Pauline(this);
	
	public TileManager tileM = new TileManager(this);
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	public SuperObject obj[] = new SuperObject[10]; //we can display 10 objects at the same time.
	
	public List<Barrel> barrels = new ArrayList<>();

    public void addBarrel(Barrel b) {
        barrels.add(b);
    }
    
	public GamePnl() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //betters game rendering performance
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
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
		dk.update();
	
		for (Barrel b : barrels) {
            b.update(this);
        }
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//TILE
		tileM.draw(g2);
		
		//OBJECT
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//PAULINE
		pauline.draw(g2);
		
		//DK
		dk.draw(g2);
		
		//PLAYER
		player.draw(g2);
		
		//BARRELS
		for (Barrel b : barrels) {
            b.draw(g2, this);
        }
		
		g2.dispose();
		
		
	}
}
