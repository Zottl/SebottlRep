package controller.ai;

import model.game.object.MapObject;
import controller.CollisionHandler.CollisionStatus;

/**
 * Class to control the behavior of MapObjects
 */
public abstract class MapObjectAI
{
    protected MapObject parent;
    protected int state;

    protected double animationState;
    protected double animationSpeed;

    public MapObjectAI(double animationSpeed)
    {
        this.state = 0;
        this.animationState = 0;
        this.animationSpeed = animationSpeed;
    }

    /**
     * Assign the parent of the AI, so that the AI knows who it is supposed to
     * control.
     * 
     * @param mo
     *            The MapObject that contains this AI
     */
    public void registerParent(MapObject mo)
    {
        parent = mo;
    }

    /**
     * The AI reaction whenever a game update happens.
     */
    public void advance()
    {
        return;
    }

    /**
     * Changes the sprite of the parent object for the animation process
     */
    protected void animate()
    {
        return;
    }

    /**
     * The AI reaction whenever the parent collides with an Object of a certain
     * CollisionStatus
     * 
     * @param cs
     *            The CollisionStatus of the MapObject the parent has collided
     *            with
     */
    public void collisionWith(CollisionStatus cs)
    {
        return;
    }

    /**
     * @return An array of collision statuses this AI reacts to. Any other
     *         statuses shouldn't even be checked, since in case of a collision,
     *         the AI will not do anything anyway.
     */
    public CollisionStatus[] getRelevantCollisionStatuses()
    {
        return new CollisionStatus[0];
    }
}
