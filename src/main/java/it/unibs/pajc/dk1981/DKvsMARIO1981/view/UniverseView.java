package it.unibs.pajc.dk1981.DKvsMARIO1981.view;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;

public class UniverseView extends JPanel implements KeyListener{
	
	private Universe universe = new Universe();
	private Player player;
	
	public UniverseView() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		
		this.addKeyListener(this);
		
		Timer animator = new Timer(10, e -> {
			applyControls();
//			universe.stepNext();
			repaint();
		});
		
		animator.start();

	}
	
	private ArrayList<Integer> currentActiveKeys = new ArrayList<>();

	private void applyControls() {
		Player p = universe.getPlayer();
		if (p == null) {
			return;
		}
		
		for(Integer keycode: currentActiveKeys) {
			switch(keycode) {
				case KeyEvent.VK_UP: 	p.climb("up");			 	break;
				case KeyEvent.VK_DOWN: 	p.climb("down"); 				break;
				case KeyEvent.VK_RIGHT: p.walk("right");				break;
				case KeyEvent.VK_LEFT:	p.walk("left");				break;
				case KeyEvent.VK_SPACE:	p.jump();				break;
			}
		}

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            player.walk("left");
            player.setDirection("left");
        } else if (key == KeyEvent.VK_RIGHT) {
            player.walk("right");
            player.setDirection("right");
        } else if (key == KeyEvent.VK_UP) {
            player.climb("up");
            player.setDirection("up");
        } else if (key == KeyEvent.VK_DOWN) {
            player.climb("down");
            player.setDirection("down");
        } else if (key == KeyEvent.VK_SPACE) {
            player.jump();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentActiveKeys.remove((Integer)e.getKeyCode());
	}

}
