package model.game.object;

/**
 * Class representing a rectangular hitbox for MapObjects
 */
public class Hitbox
{

    private int xOffs, yOffs, width, height;

    // The MapObject that contains this hitbox
    private MapObject parent;

    /**
     * Class representing a rectangular hitbox for Objects
     * 
     * @param xOffs
     *            X-Position relative to the object containing this hitbox
     * @param yOffs
     *            Y-Position relative to the object containing this hitbox
     * @param width
     *            Width of this hitbox
     * @param height
     *            Height of this hitbox
     */
    public Hitbox(int xOffs, int yOffs, int width, int height)
    {
        this.xOffs = xOffs;
        this.yOffs = yOffs;
        this.width = width;
        this.height = height;
    }

    /**
     * @return X-Position relative to the object containing this hitbox
     */
    public int getXOffs()
    {
        return xOffs;
    }

    /**
     * @return Y-Position relative to the object containing this hitbox
     */
    public int getYOffs()
    {
        return yOffs;
    }

    /**
     * @return The absolute X-Position of this hitbox
     */
    public double getX()
    {
        return xOffs + parent.getX();
    }

    /**
     * @return The absolute Y-Position of this hitbox
     */
    public double getY()
    {
        return yOffs + parent.getY();
    }

    /**
     * @return Width of this hitbox
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return Height of this hitbox
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @return The MapObject that contains this hitbox
     */
    public MapObject getParent()
    {
        return parent;
    }

    /**
     * @param parent
     *            The MapObject that contains this hitbox
     */
    public void setParent(MapObject parent)
    {
        this.parent = parent;
    }
}
