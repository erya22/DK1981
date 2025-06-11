package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;

public class GameApp {
	private static final Logger log = LoggerFactory.getLogger(GameApp.class);
	
	
//	private static int minTileSize = 10;
//    private static int minWidth = minTileSize * DKvsMario.getMapWidthTiles();
//    private static int minHeight = minTileSize * DKvsMario.getMapHeightTiles();
    int maxHeight;
    int tileSize = (maxHeight - 50) / DKvsMario.getMapHeightTiles();
    int panelWidth = tileSize * DKvsMario.getMapWidthTiles();
    int panelHeight = tileSize * DKvsMario.getMapHeightTiles();

	private JFrame frame;
	DKvsMario gamePanel;

	/**
	 * Initialize the contents of the frame singleplayer.
	 */
	public void initializesinglePlayer() {
		this.gamePanel = new DKvsMario();
		this.frame = new JFrame("DK VS MARIO ARCADE VERSION 1981");
		try {
			frame.setIconImage(ImageIO.read(getClass().getResource("/PLAYER/a1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		frame.setUndecorated(true); // Rimuove bordi e titolo
		device.setFullScreenWindow(frame);
		
		Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int screenHeight = screenBounds.height - 50; // Altezza disponibile meno 50 pixel
//		Universe.TILE_SIZE = screenHeight / Universe.U_TILE_ROWS;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); //TODO DA METTERE TRUE
		
		 Rectangle usableBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		 int maxHeight = usableBounds.height;
		 
		 log.debug("maxHeight: {}", maxHeight);
//		 int tileSize = frame.getContentPane().getHeight() / DKvsMario.getMapHeightTiles(); 
//		 Universe.TILE_SIZE = tileSize;

		
//		Timer resizeTimer = new Timer(200, e -> {
//		    Dimension size = frame.getContentPane().getSize(); // dimensioni effettive dell'area disponibile
//		    int tileW = size.width / DKvsMario.getMapWidthTiles();
//		    int tileH = size.height / DKvsMario.getMapHeightTiles();
//		    int newTileSize = Math.min(tileW, tileH);
//
//		    int mapWidth = newTileSize * DKvsMario.getMapWidthTiles();
//		    int mapHeight = newTileSize * DKvsMario.getMapHeightTiles();
//
//		    gamePanel.updateSize(newTileSize);
//		    gamePanel.setPreferredSize(new Dimension(mapWidth, mapHeight));
//
//		    frame.getContentPane().setPreferredSize(new Dimension(size.width, size.height));
//		    frame.pack(); // forza il ridimensionamento del frame
//		    
//		    
//		    gamePanel.repaint();
//		});
//		resizeTimer.setRepeats(false);
		
		 frame.addComponentListener(new ComponentAdapter() {
			    public void componentResized(ComponentEvent evt) {
			    	gamePanel.setBackground(Color.black);
			        gamePanel.setPreferredSize(frame.getSize());
			        gamePanel.repaint();
			    }
			});
		 
//		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
//		    public void componentResized(java.awt.event.ComponentEvent evt) {
//		    	Universe.TILE_SIZE = frame.getContentPane().getHeight() / DKvsMario.getMapHeightTiles();
//		    	gamePanel.updateSize(Universe.TILE_SIZE);
//		    	resizeTimer.restart();
//		    }
//		});
//		
//		int maxWidth = usableBounds.width;
		
        //Tile size massimo per farci stare tutta la mappa + 50px in fondo
//        Universe.TILE_SIZE = (maxHeight - 50) / DKvsMario.getMapHeightTiles();
        this.tileSize = Universe.TILE_SIZE;
        setPanelWidth(this.tileSize * DKvsMario.getMapWidthTiles());
        setPanelHeight(this.tileSize * DKvsMario.getMapHeightTiles());
        gamePanel.updateSize(this.tileSize);
        
//        log.info("tileSize {}, panelWidth {}, panelHeight {}", tileSize, panelWidth, panelHeight);
        
        gamePanel.setPreferredSize(frame.getSize());
        frame.getContentPane().add(gamePanel);
        frame.pack();
        
        
     // Calcola dimensioni minime basate su tileSize = 10
//        Insets insets = frame.getInsets();
//        int minFrameWidth = minWidth + insets.left + insets.right;
//        int minFrameHeight = minHeight + insets.top + insets.bottom;

        
        // Imposta dimensione minima
//       frame.setMinimumSize(new Dimension(minFrameWidth, minFrameHeight));
      
        // Imposta dimensione finestra
        frame.setLocationRelativeTo(null); // centra la finestra
        frame.setVisible(true);

        gamePanel.updateSize(this.tileSize);
        
		gamePanel.start();
	}

	/**
	 * Initialize the contents of the frame multiplayer.
	 */
	public void initializemultiPlayer(String player) {
		this.gamePanel = new DKvsMario("localhost", 5555, player);
		this.frame = new JFrame(player);
		try {
			frame.setIconImage(ImageIO.read(getClass().getResource("/PLAYER/a1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true); //TODO DA METTERE TRUE
		
		 Rectangle usableBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		 int maxHeight = usableBounds.height;
		 
//		 int tileSize = frame.getContentPane().getHeight() / DKvsMario.getMapHeightTiles(); 
//		 Universe.TILE_SIZE = tileSize;

		
		Timer resizeTimer = new Timer(200, e -> {
		    Dimension size = frame.getContentPane().getSize(); // dimensioni effettive dell'area disponibile
		    int tileW = size.width / DKvsMario.getMapWidthTiles();
		    int tileH = size.height / DKvsMario.getMapHeightTiles();
		    int newTileSize = Math.min(tileW, tileH);

		    int mapWidth = newTileSize * DKvsMario.getMapWidthTiles();
		    int mapHeight = newTileSize * DKvsMario.getMapHeightTiles();

		    gamePanel.updateSize(newTileSize);
		    gamePanel.setPreferredSize(new Dimension(mapWidth, mapHeight));

		    frame.getContentPane().setPreferredSize(new Dimension(size.width, size.height));
		    frame.pack(); // forza il ridimensionamento del frame
		    
		    
		    gamePanel.repaint();
		});
		resizeTimer.setRepeats(false);
		
		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
		    public void componentResized(java.awt.event.ComponentEvent evt) {
//		    	Universe.TILE_SIZE = frame.getContentPane().getHeight() / DKvsMario.getMapHeightTiles();
		    	gamePanel.updateSize(Universe.TILE_SIZE);
		    	resizeTimer.restart();
		    }
		});
		
//		int maxWidth = usableBounds.width;
		
        //Tile size massimo per farci stare tutta la mappa + 50px in fondo
        this.tileSize = (maxHeight - 50) / DKvsMario.getMapHeightTiles();
        setPanelWidth(tileSize * DKvsMario.getMapWidthTiles());
        setPanelHeight(tileSize * DKvsMario.getMapHeightTiles());
        gamePanel.updateSize(this.tileSize);
        
//        log.info("tileSize {}, panelWidth {}, panelHeight {}", tileSize, panelWidth, panelHeight);
        
        gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        frame.setContentPane(gamePanel);
        frame.pack();
        
     // Calcola dimensioni minime basate su tileSize = 10
        Insets insets = frame.getInsets();
//        int minFrameWidth = minWidth + insets.left + insets.right;
//        int minFrameHeight = minHeight + insets.top + insets.bottom;

        
        // Imposta dimensione minima
//        frame.setMinimumSize(new Dimension(minFrameWidth, minFrameHeight));
      
        // Imposta dimensione finestra
        frame.setLocationRelativeTo(null); // centra la finestra
        frame.setVisible(true);

        gamePanel.updateSize(this.tileSize);
        
		gamePanel.start();
	}

//	public static int getMinTileSize() {
//		return minTileSize;
//	}
//
//	public static int getMinWidth() {
//		return minWidth;
//	}
//
//	public static int getMinHeight() {
//		return minHeight;
//	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

	public int getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}	
	
	public int calculateTileSize() {
		return getPanelHeight() / DKvsMario.getMapHeightTiles();
	}
}