package controller.ai;

import model.game.object.MapObject;
import model.game.object.Projectile;
import controller.CollisionHandler.CollisionStatus;

public class ProjectileAI extends MapObjectAI
{
    private Projectile parent;

    public ProjectileAI()
    {
        super();
    }

    @Override
    public void advance()
    {
        // TODO animation
    }

    @Override
    public void collisionWith(CollisionStatus cs)
    {
        if (cs == CollisionStatus.OOB || cs == CollisionStatus.SOLID || cs == CollisionStatus.ENEMY_BODY)
        {
            data.getMap().removeMapObject(parent);
        }
    }

    @Override
    public void registerParent(MapObject mo)
    {
        super.registerParent(mo);

        this.parent = (Projectile) super.parent;
    }

    @Override
    public CollisionStatus[] getReleventCollisionStatuses()
    {
        CollisionStatus[] css = { CollisionStatus.OOB, CollisionStatus.SOLID, CollisionStatus.ENEMY_BODY };
        return css;
    }
}
