package model.game.object;

import model.game.sprites.Sprite;

/**
 * Abstract class for objects, that are placed on maps.
 */
public abstract class MapObject
{
    protected int x; // X-coordinate of the MapObject
    protected int y; // Y-coordinate of the MapObject

    // How many steps does the animation advance per update
    protected double animationSpeed;

    // Helper variable for the animation
    protected double animationBuildup;

    // Shows which part of the animation is shown right now
    protected int animationState;

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
    public MapObject(int x, int y, double animationSpeed, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.animationSpeed = animationSpeed;
        this.animationBuildup = 0;
        this.animationState = 0;
        this.sprite = sprite;
    }

    /**
     * Animate this MapObject.
     */
    public void animate()
    {
        if (animationBuildup < 1)
        {
            animationBuildup += animationSpeed;
        }
        else
        {
            advanceAnimation();
            animationBuildup--;
        }
    }

    /**
     * Initiate an interaction with the MapObject
     * 
     * @param source
     *            Character that started the interaction
     */
    public abstract void interact(Character source);

    /**
     * Go to the next Sprite in the animation of this MapObject
     */
    protected abstract void advanceAnimation();

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
