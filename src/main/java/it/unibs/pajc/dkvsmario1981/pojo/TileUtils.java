package it.unibs.pajc.dkvsmario1981.pojo;
import java.awt.image.BufferedImage;

public class TileUtils {
    public static BufferedImage[] loadTiles(BufferedImage tileset, int tileWidth, int tileHeight) {
        int columns = tileset.getWidth() / tileWidth;
        int rows = tileset.getHeight() / tileHeight;
        BufferedImage[] tiles = new BufferedImage[columns * rows];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                int index = y * columns + x;
                tiles[index] = tileset.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }
        return tiles;
    }
}
