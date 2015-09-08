package controller.ai;

import model.GameData;
import model.game.object.MapObject;
import model.game.sprites.Sprite;
import controller.CollisionHandler.CollisionStatus;

public class ProjectileAI extends MapObjectAI
{
    public ProjectileAI(double animationSpeed)
    {
        super(animationSpeed);
    }

    @Override
    public void advance()
    {
        animate();
    }

    @Override
    protected void animate()
    {
        animationState += animationSpeed;
        int stateNumber = (int) animationState;
        switch (stateNumber)
        {
            case 1:
                parent.setSprite(Sprite.testSpell02);
                break;
            case 2:
                parent.setSprite(Sprite.testSpell03);
                break;
            case 3:
                parent.setSprite(Sprite.testSpell02);
                break;
            case 4:
                parent.setSprite(Sprite.testSpell01);
                animationState = 0;
        }
    }

    @Override
    public void collisionWith(CollisionStatus cs)
    {
        if (cs == CollisionStatus.OOB || cs == CollisionStatus.SOLID || cs == CollisionStatus.ENEMY_BODY)
        {
            GameData.getInstance().getMap().removeMapObject(parent);
        }
    }

    @Override
    public void registerParent(MapObject mo)
    {
        super.registerParent(mo);
    }

    @Override
    public CollisionStatus[] getRelevantCollisionStatuses()
    {
        CollisionStatus[] css = { CollisionStatus.OOB, CollisionStatus.SOLID, CollisionStatus.ENEMY_BODY };
        return css;
    }
}
