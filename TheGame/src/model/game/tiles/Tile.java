package model.game.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.game.sprites.Sprite;

/**
 * 
 * Abstract class for map tiles in the game
 *
 */
public abstract class Tile
{

    private int x;
    private int y;

    private boolean solid;

    private Sprite sprite;

    public Tile(int x, int y, boolean solid, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.sprite = sprite;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isSolid()
    {
        return solid;
    }

    public Sprite getSprite()
    {
        return sprite;
    }
}
