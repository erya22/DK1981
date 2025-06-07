package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Universe {
	private static final Logger log = LoggerFactory.getLogger(Universe.class);
	
	//SCREEN SETTINGS
	private final int TILECOL = 28;
	private final int TILE_ROWS = 32;
	
	private int tileSize = 32;
	
	//ARCHIVIO IMMAGINI MAPPA
	private TileMap map;
	private BufferedImage tileset;
	private BufferedImage[] tiles;
	
	//ARRAY BIDIMENSIONALE
	byte[][] cooMap;
	
	public static byte LADDER = (byte) 0x2;
	public static byte BEAM = (byte) 0x1;
	
	//ARCHIVIO OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	private Pauline pauline = new Pauline(this);
	private DK DK;
	
	private Player player;

	public Universe() {
		super();
		this.player = new Player(this);
		this.cooMap = createCooMap();
		
//		for (int x = 0; x < this.cooMap.length; x++) {
//			for (int y = 0; y < this.cooMap[0].length; y++) {
//				if (this.cooMap[x][y] != 0)
//					log.info("COOMAP {} {} = {}", x, y, this.cooMap[x][y]);
//			}
//		}
		
		this.map = TileMapLoader.loadMap();
		this.tileset = TileMapLoader.loadTileset();
		this.tiles = TileUtils.loadTiles(tileset, map.getTilewidth(), map.getTileheight(), 16);
		
		
	}

	private byte[][] createCooMap() {
		byte[][] mappa = new byte[TILE_ROWS*tileSize][TILECOL*tileSize];
		int[][] beams = {
	            {0, 744, 669, 725},
	            {622, 648, 2, 628},
	            {48, 552, 671, 532},
	            {623, 456, 1, 437},
	            {46, 359, 671, 341},
	            {620, 263, 1, 257},
	            {407, 168, 240, 165}
	        };
		int[][] ladders = {
					    {288, 737, 308, 720},
					    {287, 672, 312, 660},
					    {552, 727, 575, 647},
					    {335, 637, 358, 540},
					    {95, 629, 119, 548},
					    {239, 544, 263, 524},
					    {239, 480, 263, 469},
					    {383, 539, 408, 473},
					    {552, 534, 575, 476},
					    {527, 451, 551, 432},
					    {527, 380, 551, 364},
					    {287, 444, 311, 348},
					    {94, 440, 118, 355},
					    {240, 356, 264, 342},
					    {240, 310, 264, 280},
					    {552, 343, 577, 262}
		};
		
		 for (int[] c : beams) {
            bresenhamLine(mappa, BEAM, c[0], c[1], c[2], c[3]);
		 }
		 
		 for (int[] c : ladders) {
			 verticalLine(mappa, LADDER, c[0], c[1], c[2], c[3]);
		 }
		 
		return mappa;
	}
	
	private static void bresenhamLine(byte[][] mappa, byte val, int x0, int y0, int x1, int y1) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            mappa[x0][y0] += val;

            if (x0 == x1 && y0 == y1)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }

        return;
    }
	
	public static void verticalLine(byte[][] mappa, byte val, int x0, int y0, int x1, int y1) {
	
		for (int x = x0; x <= x1; x++){
			for (int y = y0; y >= y1; y--) {
				mappa[x][y] |= val;
			}
		}
		
	}
	
	
	public TileMap getMap() {
		return map;
	}




	public void setMap(TileMap map) {
		this.map = map;
	}




	public List<GameItem> getItems() {
		return items;
	}

	public void setItems(List<GameItem> items) {
		this.items = items;
	}

	public Pauline getPauline() {
		return pauline;
	}

	public void setPauline(Pauline pauline) {
		this.pauline = pauline;
	}

	public DK getDK() {
		return DK;
	}

	public void setDK(DK dK) {
		DK = dK;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}




	public int getTileSize() {
		return tileSize;
	}


	

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}




	public BufferedImage getTileset() {
		return tileset;
	}




	public void setTileset(BufferedImage tileset) {
		this.tileset = tileset;
	}




	public BufferedImage[] getTiles() {
		return tiles;
	}




	public void setTiles(BufferedImage[] tiles) {
		this.tiles = tiles;
	}




	public int getTilecol() {
		return TILECOL;
	}




	public int getTileRows() {
		return TILE_ROWS;
	}

	public int coo(int x, int y, byte mask) {
			for (int yi = this.cooMap[0].length-1; yi >0; yi--) {
				if (this.cooMap[x][yi] != 0)
					log.info("x{} y{} val{}", x, yi, this.cooMap[x][yi]);
			}
		
		try {
			return this.cooMap[x][y+30] & mask;
		} catch (Exception e) {
			return 0;
		}
	}

	public int findBeam(int x, int y) {
		try {
			for (int i = y - 10; i < this.cooMap[0].length; i++) {
				if (this.coo(x, i, BEAM) != 0) return i;
			}
		} catch (Exception e) {
			log.info("findBeam: {}", e.getMessage(), e);
		}
		log.info("beam not found x{} y{}-{}", x, y-10, this.cooMap[0].length);
		return y;
	}
	
	public int findLadderUp(int x, int y) {
			for (int i = y; i < y + 30; i++) {
				if (this.coo(x, i, LADDER)!= 0) return i;
			} 

			return -1;
	}
	
	public int findLadderDown(int x, int y) {
		for (int i = y; i > y - 30; i--) {
			if (this.coo(x, i, LADDER)!= 0) return i;
		} 

		return -1;
}
	
	
	
	

}
