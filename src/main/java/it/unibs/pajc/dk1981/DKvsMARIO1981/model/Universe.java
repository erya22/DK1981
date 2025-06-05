package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Universe {
	//SCREEN SETTINGS
	private final int TILECOL = 28;
	private final int TILE_ROWS = 32;
	
	private int tileSize = 32;
	
	//ARCHIVIO IMMAGINI MAPPA
	private TileMap map;
	private BufferedImage tileset;
	private BufferedImage[] tiles;
	
	//ARCHIVIO OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	private Pauline pauline = new Pauline(this);
	private DK DK;
	
	private Player player;

	public Universe() {
		super();
		this.player = new Player(this);
		
		
		map = TileMapLoader.loadMap();
		tileset = TileMapLoader.loadTileset();
		tiles = TileUtils.loadTiles(tileset, map.getTilewidth(), map.getTileheight(), 16);
		
		
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

	
	
	
	
	

}
