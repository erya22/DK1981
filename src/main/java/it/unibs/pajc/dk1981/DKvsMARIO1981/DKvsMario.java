package it.unibs.pajc.dk1981.DKvsMARIO1981;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.GameEngine;
import it.unibs.pajc.dk1981.DKvsMARIO1981.controller.PlayerController;
import it.unibs.pajc.dk1981.DKvsMARIO1981.menu.GameMenu;
import it.unibs.pajc.dk1981.DKvsMARIO1981.menu.GameResultDialog;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Player;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.TileMapLoader;
import it.unibs.pajc.dk1981.DKvsMARIO1981.model.Universe;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.GUIUtils;
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

 // Variabili per il game loop
    private Thread gameThread;
    private boolean running = false;
    private boolean gameOverDialogShown = false;
    
 // modalità multiplayer
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread netThread;
    private String playerTag; // es. "Player1" o "Player2"
    
    public DKvsMario() {
    	setDoubleBuffered(true);
        
        // Inizializza mappa e renderer
        BufferedImage tileset = TileMapLoader.loadTileset();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        
        int screenH = screenSize.height;
        tileSize = screenH / Universe.U_TILE_ROWS;
        int screenW = tileSize * Universe.U_TILE_COLS;
        log.info("Screen size: {} x {}, tile size: {}", screenH, screenW, tileSize);

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
        
        // Inizia il gioco in single player
        start(); 
    }

    /**
     * Costruttore multiplayer: crea il pannello di gioco e apre la connessione al server.
     *
     * @param serverHost indirizzo del server (es. "localhost" o IP)
     * @param serverPort porta del server (es. 5555)
     * @param playerTag  "Player1" o "Player2", per identificare chi sei sul server
     */
    public DKvsMario(String serverHost, int serverPort, String playerTag) {
        this.playerTag = playerTag;
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
        
     // costruttore uguale ma con questa aggiunta, apertura connessione server
        serverConnection(serverHost, serverPort, playerTag);
        // Se siamo Player1, avviamo automaticamente Player2 in un'altra finestra
        if (playerTag.equals("Player1")) {
            new Thread(() -> {
                try {
                    // Aspetta un attimo prima di far partire Player2 (per dare tempo al server di registrare Player1)
                    Thread.sleep(2000);
                    SwingUtilities.invokeLater(() -> {
                        new GameMenu(() -> {
                            JFrame frame2 = new JFrame("Giocatore 2");
                            GUIUtils.applyIcon(frame2);
                            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            DKvsMario player2Game = new DKvsMario(serverHost, serverPort, "Player2");
                            frame2.setContentPane(player2Game);
                            frame2.pack();
                            frame2.setLocationRelativeTo(null);
                            frame2.setVisible(true);
                        });
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    
    private void serverConnection(String serverHost, int serverPort, String playerTag) {
		try {
            socket = new Socket(serverHost, serverPort);
            in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out    = new PrintWriter(socket.getOutputStream(), true);

            // Invio JOIN#PlayerTag
            out.println("JOIN#" + playerTag);

            // Avvio thread per gestire START/SCORE/RESULT
            netThread = new Thread(() -> {
                try {
                    // 1) Attendo messaggio "START" dal server
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.equals("START")) {
                            // Quando arriva "START", avvio il game loop
                            SwingUtilities.invokeLater(() -> start());
                            break;
                        }
                    }

                    // 2) Attendo che la partita finisca ("running" diventa false nel run())
                    while (running) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ignored) {}
                    }

                    // 3) Partita terminata localmente: invio SCORE#<valore>
                    int finalScore = computeFinalScore();
                    out.println("SCORE#" + finalScore);

                    // 4) Attendo "RESULT#<winner>" dal server
                    String resultLine = in.readLine(); // es: "RESULT#Player2"
                    if (resultLine != null && resultLine.startsWith("RESULT#")) {
                        String winner = resultLine.substring(7);

                        // Mostra il dialog SOLO se sei Player1
                        if (playerTag.equals("Player1")) {
                            SwingUtilities.invokeLater(() -> {
                                int player1Score = 0; // getPlayerScore("Player1");
                                int player2Score = 0; // getPlayerScore("Player2");
                                Window player1Window = SwingUtilities.getWindowAncestor(this);

	                             // Cerca la seconda finestra visibile diversa da questa (Player2)
	                             Window player2Window = null;
	                             for (Window w : Window.getWindows()) {
	                                 if (w != player1Window && w.isVisible() && w.isDisplayable()) {
	                                     player2Window = w;
	                                     break;  // prendi la prima che trovi diversa da player1Window
	                                 }
	                             }

                                GameResultDialog dialog = new GameResultDialog(player1Window, player2Window, player1Score, player2Score, winner);
                                dialog.setVisible(true);
                            });
                        }
                    }

                    // 5) Chiudo risorse
                    socket.close();
                    in.close();
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    // Se la connessione si chiude inaspettatamente, forza la fine del loop di gioco
                    running = false;
                }
            });
            netThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Impossibile connettersi al server",
                "Errore di rete",
                JOptionPane.ERROR_MESSAGE
            );
        }
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

        while (running) {
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
         // Controllo condizione game over
            if (checkGameOverCondition()) {
                running = false;
            }
        }

        // Dopo il ciclo, mostra dialog una sola volta se single player
        if (socket == null && !gameOverDialogShown) {
            gameOverDialogShown = true;
            SwingUtilities.invokeLater(() -> {
                Window window = SwingUtilities.getWindowAncestor(this);
                int playerScore = 0; // implemnta con player.getScore(); 
                GameResultDialog dialog = new GameResultDialog(window, playerScore);
                dialog.setVisible(true);
            });
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
    
    /** Avvia il thread di rendering solo quando il server invia “START” */
    public void start() {
        if (gameThread == null || !running) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
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
	
	// ─── Metodi di utilità per multiplayer “due mappe separate” ───

    /** 
     * Verifica se la partita è terminata:
     * es. quando il player è morto o ha completato il livello.
     * Modifica questa logica in base a come definisci “fine partita”.
     */
    private boolean checkGameOverCondition() {
        // Esempio: se player ha zero vite o livello completato
        return true; //player.isDead() || gameMap.isLevelComplete(); per ora metto true per testare
    }

    /** Ritorna il punteggio finale del giocatore (adatta a come memorizzi il punteggio) */
    private int computeFinalScore() {
        return 0;//player.getScore();
    }

    /** In multiplayer “mappe separate” non serve inviare ogni input al server */
    public void sendInputToServer(String command) {
        // Vuoto. Nel protocollo “mappe separate” non trasmettiamo gli input.
    }
}
    
    
	

