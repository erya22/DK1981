package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePnl;

public class TileManager {
    GamePnl gp;
    Tile[] tile;

    public TileManager(GamePnl gp) {
        this.gp = gp;
        tile = new Tile[30];
        getTileImage();
    }

    public void getTileImage() {
    	String[] resources = new String[] {
    			"/background/0background.png"
            	, "/background/1barrel.png"
            	, "/background/2asse.png"
            	, "/background/3asse+1.png"
            	, "/background/4asse+2.png"
            	, "/background/5asse+3.png"
            	, "/background/6asse+4.png"
            	, "/background/7asse+5.png"
            	, "/background/8asse+6.png"
            	, "/background/9asse-1.png"
            	, "/background/10asse-2.png"
            	, "/background/11asse-3.png"
            	, "/background/12asse-4.png"
            	, "/background/13asse-5.png"
            	, "/background/14asse-6.png"
            	, "/background/15asse+scala.png"
            	, "/background/16asse-scala4.png"
            	, "/background/17asse-scala3.png"
            	, "/background/18asse-scala2.png"
            	, "/background/19asse-scala1.png"
            	, "/background/20asse+scala1.png"
            	, "/background/21asse+scala2.png"
            	, "/background/22asse+scala3.png"
            	, "/background/23asse+scala4.png"
            	, "/background/24asse+scala5.png"
            	, "/background/25asse+scala6.png"
            	, "/background/26scala.png"
            	, "/background/27peach.png"
            	, "/background/Trump99-1.png"
            	
    			
    	};
        try {
        	
        	for(int i = 0; i < resources.length; i++) {
        		BufferedImage img = ImageIO.read(getClass().getResourceAsStream(resources[i]));
        		Image scaled = img.getScaledInstance(GamePnl.scaledTileSize, GamePnl.scaledTileSize, Image.SCALE_DEFAULT);
        		BufferedImage tempBuff = new BufferedImage(GamePnl.scaledTileSize, GamePnl.scaledTileSize, img.getType());
        		Graphics2D tempGraph = tempBuff.createGraphics();
        		tempGraph.drawImage(scaled, 0, 0, null);
        		tempGraph.dispose();
        		tile[i] = new Tile(tempBuff);
        	}
        	

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileNumber;
        int[][] levelMap = {
        	    { 0,  0,  0,  0,  0, 26,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0, 26,  0, 26,  0, 27,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0, 26,  0, 26,  2,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0, 26,  0, 26,  0,  0,  0,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 1,  1,  0,  0,  0, 26,  0, 26,  0,  0,  0,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 1,  1,  0,  0,  0, 26,  0, 26,  0,  0,  0,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 2,  2,  2,  2,  2,  2,  2,  2,  2,  9,  9,  9, 19, 10, 10, 11, 11, 11, 12, 12, 12, 13, 13, 13, 14, 14, 14},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  8,  8, 24,  7,  7,  6,  6,  6,  5,  5,  5,  4,  4,  4, 20,  3,  3},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0, 14, 14, 14, 13, 22, 13, 12, 12, 12, 11, 11, 11, 10, 10, 10, 28, 19,  9,  0},
        	    { 0,  0,  0,  0,  0,  2,  2,  2,  3, 16,  3,  4,  4,  4, 17,  5,  5,  6,  6,  6,  7,  7,  7,  8, 21,  8,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0,  0,  0, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0, 25,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0,  0,  0, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  2,  2,  2,  9,  9,  9, 10, 10, 10, 11, 26, 11, 25, 12, 12, 13, 13, 13, 14, 14, 14,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  8,  8,  8,  7,  7,  7,  6, 26,  6,  5,  5,  5,  4,  4,  4,  3,  3,  3,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0, 25,  0,  0,  0,  0, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0, 25,  0,  0,  0,  0, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  2,  2,  2,  9,  9,  9, 10, 10, 10, 11, 17, 16, 12, 12, 13, 13, 13, 14, 14, 14,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  8,  8,  8,  7,  7,  7, 22,  6,  6,  5,  5,  5,  5,  4,  4,  4, 19,  3,  3,  2,  2},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 26,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
        	    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  26, 0, 14, 14, 13, 13, 12, 12, 11, 11, 19, 10, 10,  2,  2},
        	    { 2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7,  7,  0,  0}
        	};


        for (int row = 0; row < levelMap.length; row++) {
            for (int col = 0; col < levelMap[row].length; col++) {
                tileNumber = levelMap[row][col];
                BufferedImage background = tile[tileNumber].image;
                g2.drawImage(background, col * GamePnl.scaledTileSize, row * GamePnl.scaledTileSize, GamePnl.scaledTileSize, GamePnl.scaledTileSize, null);
            }
        }
    }
    
    
}