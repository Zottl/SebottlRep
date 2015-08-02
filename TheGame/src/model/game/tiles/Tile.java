package model.game.tiles;

import controller.collision.CollisionHandler.CollisionStatus;
import model.game.characters.GameCharacter;
import model.game.object.MapObject;
import model.game.sprites.Sprite;

/**
 * 
 * Abstract class for map tiles in the game
 *
 */
public abstract class Tile extends MapObject
{
    /**
     * The size of a map tile (both width and height)
     */
    public static final int TILESIZE = 16;

    private int x;
    private int y;

    private boolean solid;

    private Sprite sprite;

    /**
     * A Tile that may be part of a GameMap
     * 
     * @param x
     *            The x coordinate of the Tile
     * @param y
     *            The y coordinate of the Tile
     * @param solid
     *            {@code true} if this tile is impassable
     * @param sprite
     *            The Sprite object that is used to display this Tile
     */
    public Tile(int x, int y, boolean solid, Sprite sprite)
    {
        super(x, y, 0, sprite);
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.sprite = sprite;
    }

    /**
     * @return The x coordinate of the Tile
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate of the Tile
     */
    public int getY()
    {
        return y;
    }

    /**
     * @return {@code true} if this tile is impassable
     */
    public boolean isSolid()
    {
        return solid;
    }

    /**
     * @return The Sprite object that is used to display this Tile
     */
    public Sprite getSprite()
    {
        return sprite;
    }

    @Override
    public void interact(GameCharacter source)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void advanceAnimation()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CollisionStatus getCollisionStatus()
    {
        if (solid)
            return CollisionStatus.SOLID;
        else
            return super.getCollisionStatus();
    }

}
