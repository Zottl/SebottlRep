package controller.collision;

import model.game.object.MapObject;
import controller.collision.CollisionHandler.CollisionStatus;

/**
 * Class representing an entry of the object layer, with the collision status
 * and the
 */
public class ObjectLayerEntry
{
    public MapObject mo;
    public CollisionStatus cs;

    public ObjectLayerEntry(MapObject mo)
    {
        this.mo = mo;
        this.cs = mo.getCollisionStatus();
    }
}
