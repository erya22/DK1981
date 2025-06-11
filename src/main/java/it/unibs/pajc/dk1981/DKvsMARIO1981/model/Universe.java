package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibs.pajc.dk1981.DKvsMARIO1981.GameApp;

public class Universe {
	private static final Logger log = LoggerFactory.getLogger(Universe.class);
	
	public static int TILE_SIZE;
	public static int LARGHEZZA_SCHERMO;
	public static int LUNGHEZZA_SCHERMO;
	
	//SCREEN SETTINGS
	//TODO DIMENSIONE DELLO SCHERMO.
	public static final int U_TILE_COLS = 28;
	public static final int U_TILE_ROWS = 32;
	public static final int U_TILE_SIZE = 32;

	public static double SCALE_FACTOR = (double) TILE_SIZE / U_TILE_SIZE;
	
	//ARCHIVIO IMMAGINI MAPPA
	private TileMap map;
	private BufferedImage tileset;
	private BufferedImage[] tiles;
	
	//ARRAY BIDIMENSIONALE
	boolean[][] ladders;
	boolean[][] beams;
	
	//ARCHIVIO OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	private Pauline pauline = new Pauline(this);
	private DK DK;
	
	private Player player;

	public Universe() {
		super();
		Universe.TILE_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height / Universe.U_TILE_ROWS;
		this.player = new Player(this);
		
		this.map = TileMapLoader.loadMap();
		this.ladders = MapParser.calcolaPixelScala(map.getLayers().get(1).getData());
		this.beams = MapParser.calcolaPixelTrave(map.getLayers().get(0).getData());
		this.tileset = TileMapLoader.loadTileset();
		//TODO: TILE
		this.tiles = TileUtils.loadTiles(tileset, map.getTilewidth(), map.getTileheight(), U_TILE_SIZE);
		
		debugTablesCompact(this.ladders, this.beams);
	}
	
	private static void bresenhamLine(byte[][] mappa, byte val, int x0, int y0, int x1, int y1) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            mappa[x0][y0] += val;

            if (x0 == x1 && y0 == y1)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }

        return;
    }
	
	public static void verticalLine(byte[][] mappa, byte val, int x0, int y0, int x1, int y1) {
	
		for (int x = x0; x <= x1; x++){
			for (int y = y0; y >= y1; y--) {
				mappa[x][y] |= val;
			}
		}
		
	}
	
	public TileMap getMap() {
		return map;
	}

	public void setMap(TileMap map) {
		this.map = map;
	}

	public List<GameItem> getItems() {
		return items;
	}

	public void setItems(List<GameItem> items) {
		this.items = items;
	}

	public Pauline getPauline() {
		return pauline;
	}

	public void setPauline(Pauline pauline) {
		this.pauline = pauline;
	}

	public DK getDK() {
		return DK;
	}

	public void setDK(DK dK) {
		DK = dK;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BufferedImage getTileset() {
		return tileset;
	}

	public void setTileset(BufferedImage tileset) {
		this.tileset = tileset;
	}

	public BufferedImage[] getTiles() {
		return tiles;
	}

	public void setTiles(BufferedImage[] tiles) {
		this.tiles = tiles;
	}

	public int getTilecol() {
		return U_TILE_COLS;
	}

	public int getTileRows() {
		return U_TILE_ROWS;
	}
	
	
	public int findBeam(int xU, int yU, int tileSize) {
		debugTablesPart("beams: \n{}", xU, yU);

		for (int x = xU - 10; x < this.beams.length; x++) {
			try { if (this.beams[x][yU]) return x; } catch (Exception e) {}
		}

		return xU;
	}

	public int findLadder(int xU, int yU, int tileSize) {
		debugTablesPart("ladders: \n{}", xU, yU);
		
		for (int deltaX = 0; deltaX < 20; deltaX++) {
			for (int deltaY = 0; deltaY < 20; deltaY++) {
				try { if (this.ladders[xU+deltaX][yU+deltaY]) return xU+deltaX; } catch (Exception e) {}
				try { if (this.ladders[xU+deltaX][yU-deltaY]) return xU+deltaX; } catch (Exception e) {}
				try { if (this.ladders[xU-deltaX][yU+deltaY]) return xU-deltaX; } catch (Exception e) {}
				try { if (this.ladders[xU-deltaX][yU-deltaY]) return xU-deltaX; } catch (Exception e) {}
			}
		}
		return -1;
	}
	
	public static void debugTablesCompact(boolean[][] scale, boolean[][] travi) {
		final int blockSize = 16;
		int l = scale.length;
	    int w = scale[0].length;

	    int newL = (l + blockSize - 1) / blockSize; // numero righe dopo riduzione
	    int newW = (w + blockSize - 1) / blockSize; // numero colonne dopo riduzione

	    StringBuilder s = new StringBuilder();

	    s.append("\n@");
	    for (int c = 0; c < newW; c++) {
	        s.append("_");
	    }

	    for (int r = 0; r < newL; r++) {
	        s.append("|");
	        for (int c = 0; c < newW; c++) {
	            boolean scalePresent = false;
	            boolean traviPresent = false;

	            // Controllo il blocco blockSize x blockSize
	            for (int rr = r * blockSize; rr < (r + 1) * blockSize && rr < l; rr++) {
	                for (int cc = c * blockSize; cc < (c + 1) * blockSize && cc < w; cc++) {
	                    if (scale[rr][cc]) scalePresent = true;
	                    if (travi[rr][cc]) traviPresent = true;
	                    if (scalePresent && traviPresent) break;
	                }
	                if (scalePresent && traviPresent) break;
	            }

	            if (scalePresent && traviPresent) {
	                s.append("#");
	            } else if (scalePresent) {
	                s.append("=");
	            } else if (traviPresent) {
	                s.append("-");
	            } else {
	                s.append(" ");
	            }
	        }
	        s.append("|\n");
	    }

	    log.info("{}", s);
	}

	public void debugTablesPart(String fmt, int xU, int yU) {
		StringBuilder s = new StringBuilder("\n     ");
		for (int r = xU - 7; r < xU + 7; r++) {
			for (int c = yU - 7; c < yU + 7; c++) {
				try {
					if (this.ladders[r][c] && this.beams[r][c]) {
						s.append("#");
					} else if (this.ladders[r][c]) {
						s.append("=");
					} else if (this.beams[r][c]) {
						s.append("_");
					} else {
						s.append(" ");
					}
				} catch (Exception e) {
					s.append("!");					
				}
			}
			s.append("\n     ");
		}
		log.info(fmt, s);
	}
}
