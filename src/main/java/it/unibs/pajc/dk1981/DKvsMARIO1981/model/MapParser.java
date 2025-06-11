package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapParser {
	private static final Logger log = LoggerFactory.getLogger(MapParser.class);

//    public static boolean[][] calcolaTravi(TileMap map) {
//        
//        boolean[][] travi = new boolean[Universe.U_TILE_ROWS*Universe.U_TILE_SIZE][Universe.U_TILE_COLS*Universe.U_TILE_SIZE];
//        int[] tiletravi = map.getLayers().get(0).getData();
//        
//        for (int i = 0; i < Universe.U_TILE_ROWS * Universe.U_TILE_SIZE; i++) {
//        	for (int j = 0; j < Universe.U_TILE_ROWS * Universe.U_TILE_SIZE; j++) {
//        		int k = i * Universe.U_TILE_SIZE + j;
//        		travi[i][j] = tiletravi[k] != 0;
//        	}
//        }
//        return travi;
//    }
//    public static boolean[][] calcolaScale(TileMap map) {
//        boolean[][] scale = new boolean[28*32][32*32];
//        int[] tilescale = map.getLayers().get(1).getData();
//        
//        
//        for (int i = 0; i < Universe.U_TILE_ROWS * Universe.U_TILE_SIZE; i++) {
//        	for (int j = 0; j < Universe.U_TILE_ROWS * Universe.U_TILE_SIZE; j++) {
//        		int k = i * Universe.U_TILE_SIZE + j;
//        		scale[i][j] = tilescale[k] != 0;
//        	}
//        }
//        return scale;
//    }
    
    public static boolean[][] calcolaPixelTrave(int[] data) {
    	boolean[][] travi = new boolean[Universe.U_TILE_COLS * Universe.U_TILE_SIZE][Universe.U_TILE_ROWS * Universe.U_TILE_SIZE];
    	for (int d = 0; d < data.length; d++) {
    		int riga = d / Universe.U_TILE_ROWS;
    		int colonna = d % Universe.U_TILE_ROWS;
    		Trave trave = Trave.byID(data[d]);
    		if (trave != null) {
    			log.info("trave {}", trave.id);
    			int y = riga * Universe.U_TILE_SIZE + trave.posizione;
    			int x = colonna * Universe.U_TILE_SIZE;
    			for (int i = 0; i < Universe.U_TILE_SIZE; i++) {
    				travi[y][x+i] = true;
    			}
    		} else {
    			log.info("trave  {} non trovata", data[d]);
    		}
    	}
    	
    	return travi;
    	
    }
    
    public static boolean[][] calcolaPixelScala(int[] data) {
    	boolean[][] scale = new boolean[Universe.U_TILE_COLS * Universe.U_TILE_SIZE][Universe.U_TILE_ROWS * Universe.U_TILE_SIZE];
    	for (int d = 0; d < data.length; d++) {
    		int riga = d / Universe.U_TILE_ROWS;
    		int colonna = d % Universe.U_TILE_ROWS;
    		Scala scala = Scala.byID(data[d]);
    		if (scala != null) {
    			int y0 = riga * Universe.U_TILE_SIZE + scala.inizio;
    			int y1 = riga * Universe.U_TILE_SIZE + scala.fine;
    			int x = colonna * Universe.U_TILE_SIZE;
    			for (int y = y0; y < y1; y++) {
    				for (int i = 0; i < Universe.U_TILE_COLS; i++) {
        				scale[y][x+i] = true;
        			}
    			}
    			
    		}
    	}
    	
    	return scale;    	
    }
    
}
