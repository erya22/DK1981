package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("DK VS MARIO 1981");
		
		GamePnl gamePanel = new GamePnl();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null); //the window will be displayed at the center of the screen
		window.setVisible(true);
		
		gamePanel.startGameThread();

	}

}
