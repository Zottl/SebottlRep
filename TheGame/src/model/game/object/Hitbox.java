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
     * @param isHurtbox
     *            Whether the hitbox is used for damaging objects or not
     */
    public Hitbox(int xOffs, int yOffs, int width, int height)
    {
        this.xOffs = xOffs;
        this.yOffs = yOffs;
        this.width = width;
        this.height = height;
        // this.isHurtbox = isHurtbox;
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

        // TASK Made this Method on a whim and didn't use it. Can probably be
        // removed.
    }

    /**
     * Checks if the hitboxe overlaps with another hitbox.
     * 
     * @param other
     *            The other hitbox
     * @return {@code true} if the hitboxes overlap.
     */
    public boolean overlap(Hitbox rect)
    {
        double tx = this.getX();
        double ty = this.getY();
        int tw = this.getWidth();
        int th = this.getHeight();

        double ox = rect.getX();
        double oy = rect.getY();
        int ow = rect.getWidth();
        int oh = rect.getHeight();

        return ox < tx + tw && tx < ox + ow && oy < ty + th && ty < oy + oh;

        // TASK Made this Method on a whim and didn't use it. Can probably be
        // removed.
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
