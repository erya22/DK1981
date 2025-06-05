package it.unibs.pajc.dk1981.DKvsMARIO1981.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.MovementState;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.State;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.PlayerView;

public class PlayerController implements KeyListener {
    private Player player;
    private Universe universe;

    public PlayerController(Player player, Universe universe) {
        this.player = player;
        this.universe = universe;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.setDirection("left");
                player.walk("left");
                break;
            case KeyEvent.VK_RIGHT:
                player.setDirection("right");
                player.walk("right");
                break;
            case KeyEvent.VK_UP:
                player.climb("up");
                break;
            case KeyEvent.VK_DOWN:
                player.climb("down");
                break;
            case KeyEvent.VK_SPACE:
                player.jump();
                break;
        }
    }
    
    public void update(float deltaTime) {
    	if (player.getState() == State.DEAD || player.getState() == State.HIT) return;
    	
    	if (player.getState() == State.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getInvincibleTime();
            if (elapsed >= player.getImmunity()) {
                player.setState(State.ALIVE); 
            }
    	}
    }
    
    private void handleMovement() {
    	
    }
    
    private void handleBarrelCollision() {
    	
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Rilascia movimento se necessario
        player.setMovement(MovementState.IDLE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non usato
    }

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    
    
  
    
}
