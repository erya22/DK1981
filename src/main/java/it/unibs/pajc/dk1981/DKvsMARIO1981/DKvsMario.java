package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.GameEngine;
import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.PlayerController;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileMapLoader;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.MapRenderer;

public class DKvsMario extends JPanel implements Runnable {
	
	 private static final int MAP_WIDTH_TILES = 28;
	 private static final int MAP_HEIGHT_TILES = 32;
	 private static final double ASPECT_RATIO = (double) MAP_WIDTH_TILES / MAP_HEIGHT_TILES;

	
	private GameEngine engine = new GameEngine();
	private Universe universe = engine.getUniverse();

    private BufferedImage screen;
    private MapRenderer renderer;
    
 // Tile size variabile in base alla dimensione del pannello
    private int tileSize;
    
    private PlayerController controller;
    private Player player;


    
    public DKvsMario() {
        setDoubleBuffered(true);
        this.tileSize = 32;
        // Inizializza mappa e renderer
        BufferedImage tileset = TileMapLoader.loadTileset();
        renderer = new MapRenderer(tileset, universe.getMap().getTilewidth(), universe.getMap().getTileheight(), tileSize);

        int screenW = tileSize * MAP_WIDTH_TILES;
        int screenH = tileSize * MAP_HEIGHT_TILES;
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
    	System.out.println("Dimensione attuale della tile: " + tileSize);
        int screenW = tileSize * MAP_WIDTH_TILES;
        int screenH = tileSize * MAP_HEIGHT_TILES;
        screen = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_RGB);
        renderer.setTileSize(tileSize);
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
        renderer.setTileSize(tileSize); // se usi un renderer
        screen = new BufferedImage(tileSize * 28, tileSize * 32, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(tileSize * 28, tileSize * 32));
        revalidate();
        repaint();
    }
    
    public PlayerController getController() {
        return controller;
    }
    
    public void start() {
		new Thread(this).start();
	}
}
    
    
	

