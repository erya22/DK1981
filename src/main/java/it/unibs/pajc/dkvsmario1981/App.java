package it.unibs.pajc.dkvsmario1981;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import it.unibs.pajc.dkvsmario.view.MapPanel;
import it.unibs.pajc.dkvsmario1981.pojo.TileMap;
import it.unibs.pajc.dkvsmario1981.pojo.TileMapLoader;
import it.unibs.pajc.dkvsmario1981.pojo.TileUtils;

/**
 * Hello world!
 */
public class App {
	
	private JFrame frame;
	
    public static void main(String[] args) {
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
					window.frame.setResizable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public App() {
    	initialize();
    }
    
    private void initialize() {
    	frame = new JFrame("DK vs Mario");
    	
    	TileMap map = TileMapLoader.loadMap();
    	
    	BufferedImage tileset = TileMapLoader.loadTileset();
        
        BufferedImage[] tiles = TileUtils.loadTiles(tileset, map.tilewidth, map.tileheight);
    	
        frame.setPreferredSize(new Dimension(map.tilewidth, map.tileheight));
        
        MapPanel mapPanel = new MapPanel(map, tiles);
        
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.add(mapPanel, BorderLayout.CENTER);
		
		frame.pack();
		
		int mapWidth = map.getWidth() * map.getTilewidth();
	    int mapHeight = map.getHeight() * map.getTileheight();
	    
	    int decorationWidth = frame.getWidth() - frame.getContentPane().getWidth();
	    int decorationHeight = frame.getHeight() - frame.getContentPane().getHeight();
	    
	    frame.setSize(mapWidth + decorationWidth, mapHeight + decorationHeight);
	    
	    frame.setLocationRelativeTo(null);
    
		
    	
    }
}
