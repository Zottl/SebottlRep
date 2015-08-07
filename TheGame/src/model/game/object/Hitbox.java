package model.game.object;

/**
 * Class representing a rectangular hitbox for Objects
 */
public class Hitbox
{

    private int xOffs, yOffs, width, height;

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
     * Checks if a position is inside the hitbox. Coordinates have to be
     * relative to the position of the object containing this hitbox.
     * 
     * @param xOffs
     *            Relative x-coordinate of the position
     * @param yOffs
     *            Relative y-coordinate of the position
     * @return {@code true} if the position is contained in the hitbox
     */
    public boolean contains(double xOffs, double yOffs)
    {
        return (xOffs >= this.xOffs) && (xOffs < this.xOffs + width) && (yOffs >= this.yOffs) && (yOffs < this.yOffs + height);
    }

    /**
     * @return X-Position relative to the object containing this hitbox
     */
    public int getxOffs()
    {
        return xOffs;
    }

    /**
     * @return Y-Position relative to the object containing this hitbox
     */
    public int getyOffs()
    {
        return yOffs;
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
}
