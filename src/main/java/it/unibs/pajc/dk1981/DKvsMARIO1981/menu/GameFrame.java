package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("DONKEY KONG VS MARIO - Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        GameWindow gameWindow = new GameWindow();
        add(gameWindow);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gameWindow.startGame(); // Avvia il loop del gioco
    }
}