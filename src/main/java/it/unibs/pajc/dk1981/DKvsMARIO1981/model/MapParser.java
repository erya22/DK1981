package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

public class MapParser {
    private static final int TILE_SIZE = 32;

    public static boolean[][] calcolaTravi(TileMap map) {
        
        boolean[][] travi = new boolean[28*32][32*32];
        int[] tiletravi = map.getLayers().get(0).getData();
        
        for (int i = 0; i < 28*32; i++) {
        	for (int j = 0; j < 32*32; j++) {
        		int k = i * 32 + j;
        		travi[i][j] = tiletravi[k] != 0;
        	}
        }
        return travi;
    }
    public static boolean[][] calcolaScale(TileMap map) {
        boolean[][] scale = new boolean[28*32][32*32];
        int[] tilescale = map.getLayers().get(1).getData();
        
        
        for (int i = 0; i < 28 * 32; i++) {
        	for (int j = 0; j < 32 * 32; j++) {
        		int k = i * 32 + j;
        		scale[i][j] = tilescale[k] != 0;
        	}
        }
        return scale;
    }
    
    public static boolean[][] calcolaPixelTrave(int[] data) {
    	boolean[][] travi = new boolean[32*32][28*32];
    	for (int d = 0; d < data.length; d++) {
    		int riga = d / 28;
    		int colonna = d % 28;
    		Trave trave = Trave.byID(data[d]);
    		if (trave != null) {
    			int y = riga * 32 + trave.posizione;
    			int x = colonna * 32;
    			for (int i = 0; i < 32; i++) {
    				travi[y][x+i] = true;
    			}
    		}
    	}
    	
    	return travi;
    	
    }
    
    public static boolean[][] calcolaPixelScala(int[] data) {
    	boolean[][] scale = new boolean[32*32][28*32];
    	for (int d = 0; d < data.length; d++) {
    		int riga = d / 28;
    		int colonna = d % 28;
    		Scala scala = Scala.byID(data[d]);
    		if (scala != null) {
    			int y0 = riga * 32 + scala.inizio;
    			int y1 = riga * 32 + scala.fine;
    			int x = colonna * 32;
    			for (int y = y0; y < y1; y++) {
    				for (int i = 0; i < 32; i++) {
        				scale[y][x+i] = true;
        			}
    			}
    			
    		}
    	}
    	
    	return scale;
    	
    }
    
	public static int getTileSize() {
		return TILE_SIZE;
	}
    
    
    
    
}
