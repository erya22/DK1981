package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import java.awt.Toolkit;

import javax.swing.SwingUtilities;

import it.unibs.pajc.dk1981.DKvsMARIO1981.audio.AudioManager;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;

public class GameLauncher {
    public static void main(String[] args) {
    	Universe.TILE_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height / Universe.U_TILE_ROWS;
        SwingUtilities.invokeLater(() -> {
            new ModeSelectionMenu();  // Mostra il menu iniziale
            AudioManager.playBackgroundMusic("/MUSIC/bacmusic.wav");
        });
    }
}