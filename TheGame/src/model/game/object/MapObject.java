package model.game.object;

import model.game.sprites.Sprite;

/**
 * Abstract class for objects, that are placed on maps.
 */
public abstract class MapObject
{
    protected int x; // X-coordinate of the MapObject
    protected int y; // Y-coordinate of the MapObject

    protected Sprite sprite; // Sprite of the MapObject

    /**
     * Abstract class for objects, that are placed on maps.
     * 
     * @param x
     *            X-coordinate of the MapObject
     * @param y
     *            Y-coordinate of the MapObject
     * @param sprite
     *            Sprite of the MapObject
     */
    public MapObject(int x, int y, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
     * Initiate an interaction with the MapObject
     * 
     * @param source
     *            Character that started the interaction
     */
    public abstract void interact(Character source);

    /**
     * @return X-coordinate of the MapObject
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return Y-coordinate of the MapObject
     */
    public int getY()
    {
        return y;
    }

    /**
     * @return Sprite of the MapObject
     */
    public Sprite getSprite()
    {
        return sprite;
    }
}
