package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameApp {
	private static final Logger log = LoggerFactory.getLogger(GameApp.class);

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log.info("START");
					GameApp window = new GameApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					log.error("Eccezione", e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		DKvsMario gamePanel = new DKvsMario();
		frame = new JFrame("DK VS MARIO ARCADE VERSION 1981");
		try {
			frame.setIconImage(ImageIO.read(getClass().getResource("/PLAYER/a1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); //TODO DA METTERE TRUE
		
		Timer resizeTimer = new Timer(200, e -> {
		    Dimension size = frame.getContentPane().getSize(); // dimensioni effettive dell'area disponibile
		    int tileW = size.width / 28;
		    int tileH = size.height / 32;
//		    int newTileSize = Math.min(tileW, tileH);

//		    int mapWidth = newTileSize * 28;
//		    int mapHeight = newTileSize * 32;

//		    gamePanel.setTileSize(newTileSize);
//		    gamePanel.setPreferredSize(new Dimension(mapWidth, mapHeight));

		    frame.getContentPane().setPreferredSize(new Dimension(size.width, size.height));
		    frame.pack(); // forza il ridimensionamento del frame
		    
		    gamePanel.repaint();
		});
		resizeTimer.setRepeats(false);
		
		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
		    public void componentResized(java.awt.event.ComponentEvent evt) {
		        resizeTimer.restart();
		    }
		});
		
		Rectangle usableBounds = GraphicsEnvironment
		            .getLocalGraphicsEnvironment()
		            .getMaximumWindowBounds();
		 
		int maxHeight = usableBounds.height;
        int tileCols = 28;
        int tileRows = 32;  // es. 32
 
        //Tile size massimo per farci stare tutta la mappa + 50px in fondo
//        int tileSize = (maxHeight - 50) / tileRows;
        int tileSize = 32;
        int panelWidth = tileSize * tileCols;
        int panelHeight = tileSize * tileRows;
     
        gamePanel.setTileSize(tileSize);// Aggiungi un setTileSize(int) nel DKvsMario se necessario
        gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        frame.setContentPane(gamePanel);
        frame.pack();
        
     // Calcola dimensioni minime basate su tileSize = 10
        int minTileSize = 10;
        int minWidth = minTileSize * 28;
        int minHeight = minTileSize * 32;

        Insets insets = frame.getInsets();
        int minFrameWidth = minWidth + insets.left + insets.right;
        int minFrameHeight = minHeight + insets.top + insets.bottom;

        // Imposta dimensione minima
        frame.setMinimumSize(new Dimension(minFrameWidth, minFrameHeight));
		
		
      
        // Imposta dimensione finestra
        frame.setLocationRelativeTo(null); // centra la finestra
        frame.setVisible(true);
        
		gamePanel.start();
	}

}
