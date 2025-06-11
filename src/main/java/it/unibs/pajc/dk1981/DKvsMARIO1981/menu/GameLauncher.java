package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import javax.swing.SwingUtilities;

public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModeSelectionMenu();  // Mostra il menu iniziale
        });
    }
}