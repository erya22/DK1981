package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
	public static final int WIDTH = 28;
	public static final int HEIGHT = 32;
	
	private Layer ladders;
	private Layer platform;
	private List<GameItem> items = new ArrayList<GameItem>();
	
	private Pauline pauline = new Pauline(0, 0);
	private DK DK;
	
	private Player player;

	public GameMap(Player player) {
		super();
		this.player = player;
		//AGGIUNGI MAPPA
	}

	public Layer getLadders() {
		return ladders;
	}

	public void setLadders(Layer ladders) {
		this.ladders = ladders;
	}

	public Layer getPlatform() {
		return platform;
	}

	public void setPlatform(Layer platform) {
		this.platform = platform;
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

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}
	
	
	
	
	
	

}
