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
				case KeyEvent.VK_SPACE:	p.jump("jump");				break;
			}
		}

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!currentActiveKeys.contains(e.getKeyCode()))
			currentActiveKeys.add(e.getKeyCode());

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentActiveKeys.remove((Integer)e.getKeyCode());
	}

}
