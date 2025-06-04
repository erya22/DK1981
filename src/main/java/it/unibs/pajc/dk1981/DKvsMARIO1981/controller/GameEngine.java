package it.unibs.pajc.dk1981.DKvsMARIO1981.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.GameMap;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;

public class GameEngine implements KeyListener {
	Player mario;
	GameMap universe = new GameMap(mario);

	public GameEngine() {
		 mario = new Player(0, 0);
		 
		 // position droid in the middle
		 mario.setX(universe.getTilecol() / 2);
		 mario.setY(universe.getTileRows() / 2);
		 
		 //add the npcs
		 
		 
		 universe = new GameMap(mario);
		}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private ArrayList<Integer> currentActiveKeys = new ArrayList<>();
	
	public void applyControls() {
		if (mario == null) {
			return;
		}
		
		//TODO: COONTROLLER CHE GESTISTE LA MODIFICA DELLO STATO
		
		for(Integer keycode : currentActiveKeys) {
			switch(keycode) {
				
//			case KeyEvent.VK_UP: 	mario.accelerate(0.2f); 	break;
//			case KeyEvent.VK_DOWN: 	mario.accelerate(-0.2f); 	break;
//			case KeyEvent.VK_RIGHT:	mario.turn(-0.1f);			break;
//			case KeyEvent.VK_LEFT:	mario.turn(0.1f);			break;
//			case KeyEvent.VK_SPACE:	mario.fire();				break;

				
			}
		}
		
	}
	
	public void update(float deltaTime) {
		//empty
	}
	
	public void render(Graphics g) {
		
	}

	public Player getMario() {
		return mario;
	}

	public void setMario(Player mario) {
		this.mario = mario;
	}

	public GameMap getUniverse() {
		return universe;
	}

	public void setUniverse(GameMap universe) {
		this.universe = universe;
	}

	public ArrayList<Integer> getCurrentActiveKeys() {
		return currentActiveKeys;
	}

	public void setCurrentActiveKeys(ArrayList<Integer> currentActiveKeys) {
		this.currentActiveKeys = currentActiveKeys;
	}
	
	
	

}
