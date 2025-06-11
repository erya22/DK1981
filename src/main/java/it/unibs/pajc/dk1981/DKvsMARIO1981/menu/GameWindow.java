package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import it.unibs.pajc.dk1981.DKvsMARIO1981.DKvsMario;

public class GameWindow extends JFrame {

    private static final int MAP_WIDTH_TILES = 28;
    private static final int MAP_HEIGHT_TILES = 32;
    private static final double ASPECT_RATIO = (double) MAP_WIDTH_TILES / MAP_HEIGHT_TILES;

    private DKvsMario gamePanel;

    public GameWindow() {
        super("DK vs Mario");

        // Usa il costruttore con host/porta/tag invece di quello senza argomenti:
        // qui "localhost", porta 5555, tag "Player1"
        gamePanel = new DKvsMario("localhost", 5555, "Player1");

        add(gamePanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Limiti dimensioni minime
        int minTileSize = 8; // ad es. 8 pixel per tile
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

                // Chiama updateSize con la larghezza in pixel; 
                // all’interno di DKvsMario questo viene tradotto in tileSize
                gamePanel.updateSize(newWidth);

                // Aggiorna preferenze dimensione del pannello
                gamePanel.setPreferredSize(new Dimension(newWidth, newHeight));
                gamePanel.revalidate();
            }
        });
    }

    /**
     * Metodo da chiamare dopo il menu per avviare il gioco.
     */
    public void startGame() {
        setVisible(true);
        gamePanel.requestFocusInWindow();
        // In modalità “due mappe separate”, il game loop verrà avviato internamente
        // non appena arriva “START” dal server. Se vuoi forzare l’avvio offline:
        // gamePanel.start();
    }
}