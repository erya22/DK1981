package it.unibs.pajc.dk1981.DKvsMARIO1981.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Layer;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileMap;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileUtils;

public class MapRenderer extends JPanel{

	 private BufferedImage[] tiles;
	    private BufferedImage tileset;
	    private int tileWidth;
	    private int tileHeight;
	    private int tileSize;

    public MapRenderer(BufferedImage tileset, int tileWidth, int tileHeight, int tileSize) {
        this.tileset = tileset;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileSize = tileSize;
        this.tiles = TileUtils.loadTiles(tileset, tileWidth, tileHeight, tileSize);
    }

    public void setTileSize(int newTileSize) {
        if (newTileSize != this.tileSize) {
            this.tileSize = newTileSize;
            this.tiles = TileUtils.loadTiles(tileset, tileWidth, tileHeight, tileSize);
        }
    }

    public void render(Graphics g, TileMap map) {
    	
        for (Layer layer : map.getLayers()) {
            if (!"tilelayer".equals(layer.getType())) continue;

            int[] data = layer.getData();
            int width = map.getWidth();
            int height = map.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int tileId = data[y * width + x];
                    if (tileId > 0) {
                    	BufferedImage tileImage = tiles[tileId - 1]; // Tiled uses 1-based index
                    	int tileSize = tileImage.getWidth(); // poiché l'immagine è stata ridimensionata a tileSize x tileSize
                    	g.drawImage(tileImage, x * tileSize, y * tileSize, null);
                    }
                }
            }
        }
    }
    
   
    
}
