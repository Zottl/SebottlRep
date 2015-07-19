package model.game;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Abstract class for map tiles in the game
 *
 */
public abstract class Tile {

    private int x;
    private int y;

    private boolean solid;

    private Image texture;

    public Tile(int x, int y, boolean solid, String texturePath) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        try {
            this.texture = ImageIO.read(Tile.class.getResource(texturePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSolid() {
        return solid;
    }

    public Image getTexture() {
        return texture;
    }
}
