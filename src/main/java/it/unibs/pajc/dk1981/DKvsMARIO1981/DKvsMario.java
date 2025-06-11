package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.GameEngine;
import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.PlayerController;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileMapLoader;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.MapRenderer;

public class DKvsMario extends JPanel implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(DKvsMario.class);
	public static final double ASPECT_RATIO = (double) Universe.U_TILE_COLS / Universe.U_TILE_ROWS;
	
	private GameEngine engine = new GameEngine();
	private Universe universe = engine.getUniverse();

    private BufferedImage screen;
    private MapRenderer renderer;
    
 // Tile size variabile in base alla dimensione del pannello
    private int tileSize;
    private int screenTile;
    
    private PlayerController controller;
    private Player player;

    
    public DKvsMario() {
        setDoubleBuffered(true);
         
        // Inizializza mappa e renderer
        BufferedImage tileset = TileMapLoader.loadTileset();
        
        int screenW = Universe.U_TILE_SIZE * Universe.U_TILE_COLS;
        int screenH = Universe.U_TILE_SIZE * Universe.U_TILE_ROWS;
        
        this.screenTile = screenH / Universe.U_TILE_ROWS;
        renderer = new MapRenderer(tileset, universe.getMap().getTilewidth(), universe.getMap().getTileheight(), screenTile);

        screen = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(screenW, screenH));

        // 🔽 Inizializza il player
        this.player = new Player(universe);
        universe.setPlayer(player); // <- Associa il player all'universo, se hai un metodo del genere

        // 🔽 Inizializza il controller con player e universo
        this.controller = engine.getController();
        this.addKeyListener(controller);
        this.addMouseListener(controller);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

	
	
    /**
     * Metodo da chiamare quando cambia la dimensione del pannello per aggiornare tileSize e buffer
     */
    public void updateSize(int tileSize) {
    	this.tileSize = tileSize;
        int screenW = tileSize * Universe.U_TILE_COLS;
        int screenH = tileSize * Universe.U_TILE_ROWS;
        screen = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(screenW, screenH));
        revalidate();
        repaint();
    }

    @Override
    public void run() {
        long delta = 0;

        while (true) {
            long lastTime = System.nanoTime();
            Graphics g = screen.getGraphics();

            g.setColor(Color.black);
            g.fillRect(0, 0, screen.getWidth(), screen.getHeight());

            engine.update((float)(delta / 1_000_000_000.0f));
            renderer.render(g, engine.getUniverse().getMap());
            engine.render(g);

            g.dispose();

            repaint();

            delta = System.nanoTime() - lastTime;
            if (delta < 20_000_000L) {
                try {
                    Thread.sleep((20_000_000L - delta) / 1_000_000L);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(screen, 0, 0, this);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
    	this.tileSize = tileSize;
//    	log.info("Nuova tileSize {} nel setTileSize", tileSize);
        screen = new BufferedImage(tileSize * Universe.U_TILE_COLS, tileSize * Universe.U_TILE_ROWS, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(tileSize * Universe.U_TILE_COLS, tileSize * Universe.U_TILE_ROWS));
        revalidate();
        repaint();
    }
    public void updateTileSize() {
    	Universe.TILE_SIZE = getHeight() /getMapHeightTiles();
    	}
    
    public PlayerController getController() {
        return controller;
    }
    
    public void start() {
		new Thread(this).start();
	}



	public GameEngine getEngine() {
		return engine;
	}



	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}



	public Universe getUniverse() {
		return universe;
	}



	public void setUniverse(Universe universe) {
		this.universe = universe;
	}



	public BufferedImage getScreen() {
		return screen;
	}



	public void setScreen(BufferedImage screen) {
		this.screen = screen;
	}



	public MapRenderer getRenderer() {
		return renderer;
	}



	public void setRenderer(MapRenderer renderer) {
		this.renderer = renderer;
	}



	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player player) {
		this.player = player;
	}



	public static int getMapWidthTiles() {
		return Universe.U_TILE_COLS;
	}



	public static int getMapHeightTiles() {
		return Universe.U_TILE_ROWS;
	}



	public static double getAspectRatio() {
		return ASPECT_RATIO;
	}



	public void setController(PlayerController controller) {
		this.controller = controller;
	}
	
	
    
    
}
    
    
	

