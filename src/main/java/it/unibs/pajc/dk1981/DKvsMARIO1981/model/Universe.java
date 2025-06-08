package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

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
	boolean[][] ladders;
	boolean[][] beams;
	
	//ARCHIVIO OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	private Pauline pauline = new Pauline(this);
	private DK DK;
	
	private Player player;

	public Universe() {
		super();
		this.player = new Player(this);
		this.map = TileMapLoader.loadMap();
		this.ladders = MapParser.calcolaPixelScala(map.getLayers().get(0).getData());
		this.beams = MapParser.calcolaPixelTrave(map.getLayers().get(1).getData());
		this.tileset = TileMapLoader.loadTileset();
		this.tiles = TileUtils.loadTiles(tileset, map.getTilewidth(), map.getTileheight(), 16);
		
		
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

	public int findBeam(int x, int y, int tileSize) {
		int xU = x * 32 / tileSize; 
		int yU = y* 32 / tileSize;
		for (int i = 0; i < this.beams.length; i++) {
			for (int j = 0; j < this.beams[0].length; j++) {
				if (this.beams[xU+16][yU+32]) return 1;
			}
		}
		return -1;
	}
	
	public int findLadder(int x, int y, int tileSize) {
		int xU = x * 32 / tileSize; 
		int yU = y* 32 / tileSize;
		
		for (int i = 0; i < this.ladders.length; i++) {
			for (int j = 0; j < this.ladders[0].length; j++) {
				if (this.ladders[xU+16][yU+32]) return 1;
			}
		}
		return -1;
	}
	

}
