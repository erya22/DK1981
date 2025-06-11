package it.unibs.pajc.dk1981.DKvsMARIO1981.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Layer;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileMap;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileUtils;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;

public class MapRenderer extends JPanel {
	
	 private static final Logger log = LoggerFactory.getLogger(MapRenderer.class);
	 private BufferedImage[] tiles;
	 private BufferedImage tileset;

    public MapRenderer(BufferedImage tileset, int tileWidth, int tileHeight, int tileSize) {
        this.tileset = tileset;
        this.tiles = TileUtils.loadTiles(tileset, tileWidth, tileHeight, tileSize);
    }

//    public void setTileSize(int newTileSize) {
//        if (newTileSize != this.tileSize) {
//            this.tileSize = newTileSize;
//            this.tiles = TileUtils.loadTiles(tileset, tileWidth, tileHeight, tileSize);
//        }
//    }

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
//                    	int tileSize = tileImage.getWidth(); // poiché l'immagine è stata ridimensionata a tileSize x tileSize
                    	g.drawImage(tileImage, x * Universe.TILE_SIZE, y * Universe.TILE_SIZE, Universe.TILE_SIZE, Universe.TILE_SIZE, null);
//                    	log.info("tile size nel renderer: {}", tileSize);
                    }
                }
            }
        }
    }
    
   
    
}
