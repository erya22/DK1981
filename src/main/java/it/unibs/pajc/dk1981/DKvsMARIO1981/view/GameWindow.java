package it.unibs.pajc.dk1981.DKvsMARIO1981.view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibs.pajc.dk1981.DKvsMARIO1981.DKvsMario;

public class GameWindow extends JFrame {

    public static final int MAP_WIDTH_TILES = 28;
    public static final int MAP_HEIGHT_TILES = 32;
    public static final double ASPECT_RATIO = (double) MAP_WIDTH_TILES / MAP_HEIGHT_TILES;

    private DKvsMario gamePanel;

    public GameWindow() {
        super("DK vs Mario");

        gamePanel = new DKvsMario();

        add(gamePanel);

        int initialTileSize = getContentPane().getWidth() / MAP_WIDTH_TILES;
        gamePanel.updateSize(initialTileSize);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Limiti dimensioni minime
        int minTileSize = 8; // es.
        int minWidth = MAP_WIDTH_TILES * minTileSize;
        int minHeight = MAP_HEIGHT_TILES * minTileSize;

        setMinimumSize(new Dimension(minWidth, minHeight));

        // Limite massimo altezza: altezza schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxHeight = screenSize.height;
        int maxWidth = (int) (maxHeight * ASPECT_RATIO);
        setMaximumSize(new Dimension(maxWidth, maxHeight));

        // Listener per ridimensionamento finestra
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getContentPane().getSize();

                // Calcola dimensione mantenendo aspect ratio
                int newHeight = size.height;
                int newWidth = (int) (newHeight * ASPECT_RATIO);
                
                // Se la larghezza calcolata supera quella disponibile, ridimensiona in base a larghezza
                if (newWidth > size.width) {
                    newWidth = size.width;
                    newHeight = (int) (newWidth / ASPECT_RATIO);
                }

                gamePanel.updateSize(newWidth);

                // Setta la dimensione preferita del pannello per farlo adattare al ridimensionamento
                gamePanel.setPreferredSize(new Dimension(newWidth, newHeight));
                gamePanel.revalidate();
            }
        });
    }

    
}

