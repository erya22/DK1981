package it.unibs.pajc.dk1981.DKvsMARIO1981.model;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import it.unibs.pajc.dk1981.DKvsMARIO1981.DKvsMario;

public class TileUtils {
    public static BufferedImage[] loadTiles(BufferedImage tileset, int tileWidth, int tileHeight, int tileSize) {
        int tilePerRow = tileset.getWidth() / tileWidth;
        int tilePerCol = tileset.getHeight() / tileHeight;
        BufferedImage[] tiles = new BufferedImage[tilePerRow * tilePerCol];

        for (int y = 0; y < tilePerCol; y++) {
            for (int x = 0; x < tilePerRow; x++) {
                int index = y * tilePerRow + x;
                BufferedImage tile = tileset.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
//                BufferedImage resizedTile = (BufferedImage) tile.getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_SMOOTH);
                BufferedImage resizedTile = Scalr.resize(tile, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, Universe.TILE_SIZE, tileSize);
                tiles[index] = resizedTile;
            }
        }
        return tiles;
    }
}
