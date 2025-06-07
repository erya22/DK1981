package it.unibs.pajc.dk1981.DKvsMARIO1981.view;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;

public class UniverseView extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	private static final Logger log = LoggerFactory.getLogger(UniverseView.class);
	private Universe universe = new Universe();
	
	public UniverseView() {
		log.info("UniverseView created");

		this.setFocusable(true);
		this.requestFocusInWindow();
		
		
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

		
		Timer animator = new Timer(10, e -> {
			applyControls();
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

		if (currentActiveKeys.isEmpty()) {
			p.idle();
			return;
		}

		
		for(Integer keycode: currentActiveKeys) {
			switch(keycode) {
				case KeyEvent.VK_UP: 	p.climb(p.getMovement());			 	break;
				case KeyEvent.VK_DOWN: 	p.climb(p.getMovement()); 				break;
				case KeyEvent.VK_RIGHT: p.walk("right");				break;
				case KeyEvent.VK_LEFT:	p.walk("left");				break;
				case KeyEvent.VK_SPACE:	p.startJump();				break;
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
		
		if (!currentActiveKeys.contains(key)) {
	        currentActiveKeys.add(key);
	    }

	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentActiveKeys.remove((Integer)e.getKeyCode());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		log.info("Mouse moved to: x={}, y={}", x, y);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
	    int x = e.getX();
	    int y = e.getY();
	    log.info("Mouse clicked at: x={}, y={}", x, y);
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

	

}
