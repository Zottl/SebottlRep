package model.game.object;

import java.util.Observable;

import model.game.sprites.Sprite;
import controller.ai.MapObjectAI;
import controller.collision.CollisionHandler.CollisionStatus;

/**
 * Abstract class for objects, that are placed on maps.
 */
public abstract class MapObject extends Observable
{
    protected double x; // X-coordinate of the MapObject
    protected double y; // Y-coordinate of the MapObject

    // Movement speed of the MapObject in pixels per update
    protected double movementSpeed;
    // Movement direction of the MapObject in degrees (or -1 if unmoving)
    protected int direction;

    // How many steps does the animation advance per update
    protected double animationSpeed;

    // Shows which part of the animation is shown right now
    protected double animationState;

    protected Sprite sprite; // Sprite of the MapObject
    protected Hitbox hitbox; // Hitbox of the MapObject

    // The AI that controls the behavior of this MapObject
    protected MapObjectAI ai;

    /**
     * Abstract class for objects, that are placed on maps.
     * 
     * @param x
     *            X-coordinate of the MapObject
     * @param y
     *            Y-coordinate of the MapObject
     * @param movementSpeed
     *            Movement speed of the MapObject
     * @param animationSpeed
     *            Animation speed of the MapObject
     * @param sprite
     *            Sprite of the MapObject
     * @param hitbox
     *            Hitbox of the MapObject
     */
    public MapObject(int x, int y, double movementSpeed, double animationSpeed, Sprite sprite, Hitbox hitbox, MapObjectAI ai)
    {
        // Let the object be at the center of the pixel it is standing on
        this.x = x + 0.5;
        this.y = y + 0.5;
        this.movementSpeed = movementSpeed;
        this.direction = -1;
        this.animationSpeed = animationSpeed;
        this.animationState = 0;
        this.sprite = sprite;
        this.hitbox = hitbox;
        this.ai = ai;

        if (ai != null) ai.registerTarget(this);
    }

    /**
     * Go to the next Sprite in the animation of this MapObject
     */
    public abstract void advanceAnimation();

    /**
     * @return {@code true} if the object can be inside solid objects
     */
    public abstract boolean isGhost();

    /**
     * Move the MapObject to the given position
     * 
     * @param xPos
     *            X coordinate of the position
     * @param yPos
     *            Y coordinate of the position
     */
    public void moveTo(double xPos, double yPos)
    {
        notifyObservers(false);
        x = xPos;
        y = yPos;
        notifyObservers(true);
    }

    /**
     * Checks if the position is inside the hitbox of this MapObject
     * 
     * @param x
     *            X coordinate of the position
     * @param y
     *            Y coordinate of the position
     * @return {@code true} if the position is contained in the hitbox of this
     *         MapObject
     */
    public boolean containedInHitbox(int x, int y)
    {
        return hitbox.contains(x - this.x, y - this.y);
    }

    /**
     * @return X-coordinate of the MapObject
     */
    public double getX()
    {
        return x;
    }

    /**
     * @return Y-coordinate of the MapObject
     */
    public double getY()
    {
        return y;
    }

    /**
     * @return The x-coordinate of the center of this MapObject
     */
    public double getCenterX()
    {
        return x + sprite.WIDTH / 2.0;
    }

    /**
     * @return The y-coordinate of the center of this MapObject
     */
    public double getCenterY()
    {
        return y + sprite.HEIGHT / 2.0;
    }

    /**
     * @return Sprite of the MapObject
     */
    public Sprite getSprite()
    {
        return sprite;
    }

    /**
     * @param sprite
     *            Sprite of the MapObject
     */
    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    /**
     * @return The {@link CollisionStatus} associated with this MapObject
     */
    public CollisionStatus getCollisionStatus()
    {
        return CollisionStatus.EMPTY;
    }

    /**
     * @return Movement speed of the MapObject in pixels per update
     */
    public double getMovementSpeed()
    {
        return movementSpeed;
    }

    /**
     * @param movementSpeed
     *            Movement speed of the MapObject in pixels per update
     */
    public void setMovementSpeed(double movementSpeed)
    {
        this.movementSpeed = movementSpeed;
    }

    /**
     * @param Direction
     *            of the MapObject in degrees
     */
    public int getDirection()
    {
        return direction;
    }

    /**
     * @param direction
     *            Direction of the MapObject in degrees
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    /**
     * @return X-Position of the hitbox of this MapObject
     */
    public int getHitboxX()
    {
        return (int) x + hitbox.getxOffs();
    }

    /**
     * @return Y-Position of the hitbox of this MapObject
     */
    public int getHitboxY()
    {
        return (int) y + hitbox.getyOffs();
    }

    /**
     * @return Width of the hitbox of this MapObject
     */
    public int getHitboxWidth()
    {
        return hitbox.getWidth();
    }

    /**
     * @return Height of the hitbox of this MapObject
     */
    public int getHitboxHeight()
    {
        return hitbox.getHeight();
    }

    /**
     * @return The AI that controls the behavior of this MapObject
     */
    public MapObjectAI getAI()
    {
        return ai;
    }
}
