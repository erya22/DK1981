package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePnl;

public class TileManager {
	GamePnl gp;
	Tile[] tile;
	
	public TileManager(GamePnl gp) {
		this.gp = gp;
		tile = new Tile[10];
		getTileImage();
	}

	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/background/Asse.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/background/Basic_background.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/background/Ladder.png"));
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int[][] levelMap = {
			    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 4, 1, 1, 1, 1, 0, 0, 0, 0}, // Pauline in alto a sinistra
			    {0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0}, // scale
			    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // piattaforma
			    {0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0},
			    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			    {0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0},
			    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			    {0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0},
			    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // Mario in basso a sinistra
			};

	}
}
