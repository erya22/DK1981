package it.unibs.pajc.dk1981.DKvsMARIO1981.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.PlayerView;

public class GameEngine {
	private Universe universe;
	private PlayerController controller;
	private Player mario;
	private PlayerView playerView;

	public GameEngine() {
		universe = new Universe();
		mario = new Player(universe);
		universe.setPlayer(mario); // Assicurati che Universe abbia questo metodo

		// Inizializza controller e vista
		playerView = new PlayerView(mario);
		controller = new PlayerController(mario, playerView);
		
	}

	public void update(float deltaTime) {
		controller.update(deltaTime);
		mario.updatePhysics();
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		playerView.draw(g2);
	}

	public Universe getUniverse() {
		return universe;
	}

	public PlayerController getController() {
		return controller;
	}

	public Player getPlayer() {
		return mario;
	}
}
