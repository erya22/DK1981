package it.unibs.pajc.dk1981.DKvsMARIO1981.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.MovementState;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.State;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Terrain;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.PlayerView;

public class PlayerController implements KeyListener, MouseListener, MouseMotionListener {
	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	private Player player;
    private PlayerView view;

    public PlayerController(Player player, PlayerView view) {
        this.player = player;
        this.view = view;
        view.addMouseListener(this);

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
                player.climb(MovementState.UPCLIMB);
                break;
            case KeyEvent.VK_DOWN:
                player.climb(MovementState.DOWNCLIMB);
                break;
            case KeyEvent.VK_SPACE:
            	if (player.getMovement() != MovementState.JUMPING) {
                    player.startJump(); // nuova funzione che imposta yVelocity
                }
                break;
        }
    }
    
    public void update(float deltaTime) {
    	if (player.getState() == State.DEAD || player.getState() == State.HIT) return;
    	
    	player.updatePhysics();
    	
    	if (player.getState() == State.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getInvincibleTime();
            if (elapsed >= player.getImmunity()) {
                player.setState(State.ALIVE); 
            }
    	}
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


	public PlayerView getView() {
		return view;
	}


	public void setView(PlayerView view) {
		this.view = view;
	}


	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
	    int x = e.getX();
	    int y = e.getY();
	    log.info("Mouse clicked at: x={}, y={}, mappa{}", x, y, this.getPlayer().getUniverse().coo(x, y, (byte) 0xf ));
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

    
    
  
    
}
