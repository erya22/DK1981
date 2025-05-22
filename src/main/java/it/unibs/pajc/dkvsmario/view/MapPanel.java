package it.unibs.pajc.dkvsmario.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import it.unibs.pajc.dkvsmario1981.pojo.Layer;
import it.unibs.pajc.dkvsmario1981.pojo.TileMap;

public class MapPanel extends JPanel {
    private TileMap map;
    private BufferedImage[] tiles; 
    private int tileWidth;
    private int tileHeight;

    public MapPanel(TileMap map, BufferedImage[] tiles) {
        this.map = map;
        this.tiles = tiles;
        
        this.tileWidth = map.getTilewidth();
        this.tileHeight = map.getTileheight();
       
        int width = map.getWidth() * tileWidth;
        int height = map.getHeight() * tileHeight;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int mapPixelWidth = map.getWidth() * tileWidth;
        int mapPixelHeight = map.getHeight() * tileHeight;
        
     // Calcola offset per centrare la mappa nella finestra
        int offsetX = (getWidth() - mapPixelWidth) / 2;
        int offsetY = (getHeight() - mapPixelHeight) / 2;

        if (map == null || map.layers.isEmpty()) return;

        Layer travi = map.layers.get(0); // disegniamo solo il primo layer
        int[] dataTravi = travi.data;

        for (int y = 0; y < travi.height; y++) {
            for (int x = 0; x < travi.width; x++) {
                int tileIndex = dataTravi[y * travi.width + x];
                if (tileIndex > 0) {
                	 g2.drawImage(tiles[tileIndex - 1],
                             offsetX + x * tileWidth,
                             offsetY + y * tileHeight,
                             null);
                }
            }
        }
            
        Layer scale = map.layers.get(1);
        int[] dataScale = scale.data;
        
        for ( int y = 0; y < scale.height; y++) {
                for (int x = 0; x < scale.width; x++) {
                    int tileIndex = dataScale[y * scale.width + x];
                    if (tileIndex > 0) {
                    	 g2.drawImage(tiles[tileIndex - 1],
                                 offsetX + x * tileWidth,
                                 offsetY + y * tileHeight,
                                 null);
                    }
                }
            }
            
    }

	public TileMap getMap() {
		return map;
	}

	public void setMap(TileMap map) {
		this.map = map;
	}

	public BufferedImage[] getTiles() {
		return tiles;
	}

	public void setTiles(BufferedImage[] tiles) {
		this.tiles = tiles;
	}
    
    
}
